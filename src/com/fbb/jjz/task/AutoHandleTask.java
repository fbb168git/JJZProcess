package com.fbb.jjz.task;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.bean.Account;
import com.fbb.jjz.bean.ApplyCarInfo;
import com.fbb.jjz.bean.ApplyEnterCar;
import com.fbb.jjz.bean.ApplyEnterCar.ApplyInfo;
import com.fbb.jjz.bean.ApplyParams;
import com.fbb.jjz.bean.ApplyStatus;
import com.fbb.jjz.bean.Car;
import com.fbb.jjz.bean.EnterPerson;
import com.fbb.jjz.dao.AccountDao;
import com.fbb.jjz.dao.ApplyStatusDao;
import com.fbb.jjz.dao.CarDao;
import com.fbb.jjz.dao.EnterPersonDao;
import com.fbb.jjz.test.A1_EnterCarList;
import com.fbb.jjz.test.A3_Curtime_03;
import com.fbb.jjz.test.A4_CheckEnvGrade;
import com.fbb.jjz.test.A6_SubmitPaper_03;

public class AutoHandleTask {
    private static Logger logger = LoggerFactory.getLogger(AutoHandleTask.class);

    // public static final String userid = "0719afcd79e847c8abfb4c6d0cf8b02b";
//    public static final String userid = "0E0CDBD816C846A9A9BF71AA57C94851";
//    public static final String token = "1737AC6161394B730726E54941BF9BF3";
//    public static final String sign = "aaNVCC001011a2898a41a1ca0770990930ff290fbd4fe39d8a";
//    public static String timestamp = "2017-11-05+23%3A09%3A05";


    public static void main(String[] args) {
        new AutoHandleTask().start();
    }

    Timer mTimer = null;
    public void start() {
        Calendar cal = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        int DAY_OF_MONTH = cal.get(Calendar.DAY_OF_MONTH);
        int HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
        int MINUTE = cal.get(Calendar.MINUTE);
        cal.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 2);
        cal.set(Calendar.SECOND, 0);
        logger.info("服务启动事件:" + DAY_OF_MONTH + "号 " + HOUR_OF_DAY + ":" + MINUTE);
        Date firstTime = cal.getTime();
        logger.info("MorningTask首次执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.SIMPLIFIED_CHINESE).format(firstTime));

        stop();
        mTimer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                logger.info(".............schedule task.............");
                new AutoHandleTask().mainTask();
            }
        };
        mTimer.scheduleAtFixedRate(task, firstTime, 24 * 60 * 60 * 1000);
        
        new AutoHandleTask().mainTask();
    }
    
    public void stop() {
        if(mTimer != null) {
            try {
                mTimer.cancel();
                mTimer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void mainTask() {
        while (!new A3_Curtime_03().isServerAvaiable()) {
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("start handle task......");
        commitLogic();
        verifyLogic();
        logger.info("--------------handle over--------------");
    }

    private void verifyLogic() {
        ApplyStatusDao statusDao = new ApplyStatusDao();
        ArrayList<ApplyStatus> queryByStatus = statusDao.queryByStatus(ApplyStatus.APPLY_STAUS_REQ_FAIL);
        
    }

    private void commitLogic() {
        AccountDao accountDao = new AccountDao();
        CarDao carDao = new CarDao();
        EnterPersonDao personDao = new EnterPersonDao();
        A1_EnterCarList enterCarHelper = new A1_EnterCarList();

        ArrayList<Account> accountList = accountDao.queryAll();
        if (accountList != null && accountList.size() > 0) {
            for (Account account : accountList) {
                ArrayList<Car> carList = carDao.queryByUserid(account.getUserid());
                ApplyStatusDao statusDao = new ApplyStatusDao();
                for (Car car : carList) {
                    
                    ApplyStatus status = statusDao.queryByCarid(car.carid);
                    if (status == null) {
                        ApplyEnterCar enterCar = enterCarHelper.getEnterCar(car.userid, car.carid);
                        if(enterCar == null) {
                            continue;
                        }
                        if(enterCar.getCarapplyarr() != null && enterCar.getCarapplyarr().size() > 0) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
                            ApplyInfo applyInfo = enterCar.getCarapplyarr().get(0);
                            try {
                                Date enterbjstart = format.parse(applyInfo.enterbjstart);
                                Date enterbjend = format.parse(applyInfo.enterbjend);
                                status = new ApplyStatus(car.userid, car.carid, ApplyStatus.APPLY_STAUS_UNDO,
                                    enterbjstart, enterbjend, new Date());
                                boolean success = statusDao.addOrUpdate(status);
                            } catch (ParseException e) {
                                logger.error("", e);
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                logger.error("", e);
                            }
                        }
                    } else if (status.enterbjend.getTime() > new Date().getTime()) {
                        continue;
                    }
                    
                    if (enterCarHelper.canApply(car.userid, car.carid)) {
                        if (!car.hasEmptyValue()) {
                            EnterPerson person = personDao.queryByCarid(car.carid);
                            if (person != null) {
                                ApplyParams applyParams = new ApplyParams();
                                applyParams.fillCarInfo(car);
                                applyParams.fillPersonInfo(person);
                                applyParams.format();
                                boolean success = new A6_SubmitPaper_03().submit(applyParams);
                                
                                Date date = new Date();
                                Calendar cal = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
                                int today = cal.get(Calendar.DAY_OF_MONTH);
                                cal.set(Calendar.DAY_OF_MONTH,today + 1);
                                cal.set(Calendar.HOUR_OF_DAY, 0);
                                cal.set(Calendar.MINUTE, 0);
                                cal.set(Calendar.SECOND, 0);
                                Date enterbjstart = cal.getTime();
                                cal.set(Calendar.DAY_OF_MONTH,today + A6_SubmitPaper_03.INBJDURATION);
                                Date enterbjend = cal.getTime();
                                if(success) {
                                    status = new ApplyStatus(car.userid, car.carid, ApplyStatus.APPLY_STAUS_VERIFY_ING, enterbjstart, enterbjend, date);
                                } else {
                                    status = new ApplyStatus(car.userid, car.carid, ApplyStatus.APPLY_STAUS_REQ_FAIL, enterbjstart, enterbjend, date);
                                }
                                try {
                                    statusDao.addOrUpdate(status);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    
                }
            }
        }
    }

    private static void testSubmit() {
        String userId = "d131c66355654e9b83bdc4f0db55c01d";// lk
        userId = "0719afcd79e847c8abfb4c6d0cf8b02b";// fbb
        ApplyCarInfo carInfo = new ApplyCarInfo("GTM7150AE", "2015-02-15", "Q042607", "03", "冯贝贝", "410882198710125512");
        // userId = "0E0CDBD816C846A9A9BF71AA57C94851";//zhc
        // ApplyCarInfo carInfo = new ApplyCarInfo("JNJ7153", "2013-05-15", "DL0234", "03", "周海超",
        // "410882198910205533");

        // userId = "F867CCD9C7DE499681AD24BD9491648A";//ljw
        // ApplyCarInfo carInfo = new ApplyCarInfo("CAF7202A53", "2017-08-04", "HA11696", "03",
        // "李佳伟", "130481198911032114");

        ArrayList<ApplyEnterCar> carList = new A1_EnterCarList().getEnterCarList(userId);
        if (carList != null && carList.size() > 0) {
            for (int i = 0; i < carList.size(); i++) {
                ApplyEnterCar car = carList.get(i);
                ApplyParams params = new ApplyParams(carInfo);
                params.userid = userId;
                params.carid = car.getCarid();
                params.cartypecode = car.getCartype();
                params.licenseno = car.getLicenseno();
                String envGrade = new A4_CheckEnvGrade().run(userId, params.carid, params.licenseno, params.carmodel, params.carregtime);
                params.envGrade = envGrade;
                params.format();
                new A6_SubmitPaper_03().run(userId, params.carid, params.licenseno, params.cartypecode, params.vehicletype, params.envGrade, params.carmodel, params.carregtime, params.engineno, params.drivingphoto, params.carphoto, params.driverphoto, params.personphoto, params.drivername,
                        params.driverlicenseno);
            }
        } else {

        }
    }

}
