package com.fbb.jjz.servlet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.bean.Account;
import com.fbb.jjz.bean.ApplyParams;
import com.fbb.jjz.bean.Car;
import com.fbb.jjz.bean.Device;
import com.fbb.jjz.bean.EnterPerson;
import com.fbb.jjz.bean.ResultLogin;
import com.fbb.jjz.bean.ResultPhoneCode;
import com.fbb.jjz.dao.AccountDao;
import com.fbb.jjz.dao.CarDao;
import com.fbb.jjz.dao.DeviceDao;
import com.fbb.jjz.dao.EnterPersonDao;
import com.fbb.jjz.servlet.base.BaseServlet;
import com.fbb.jjz.task.ApplyInfoHelper;
import com.fbb.jjz.task.LoginHelper;
import com.fbb.jjz.util.JsonUtil;
import com.fbb.jjz.util.TextUtil;

public class LoginServlet extends BaseServlet {
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected String process(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        String type = request.getParameter("type");
        if(type == null || type.equalsIgnoreCase("")) {
            ResultPhoneCode phoneCodeResult = new ResultPhoneCode();
            phoneCodeResult.rescode = "80001";
            phoneCodeResult.resdes = "参数不完整";
            return JsonUtil.toJson(phoneCodeResult);
        }
        String result = null;
        if(type.equalsIgnoreCase("0")) {//验证码
            result = processPhoneCode(request);
        } else if(type.equalsIgnoreCase("1")){//登陆
            result = processLogin(request);
        } else {
            ResultPhoneCode phoneCodeResult = new ResultPhoneCode();
            phoneCodeResult.rescode = "80002";
            phoneCodeResult.resdes = "参数值未定义";
            return JsonUtil.toJson(phoneCodeResult);
        }
        
        return result;
    }

    private String processPhoneCode(HttpServletRequest request) throws SQLException{
        String imei = request.getParameter("imei");
        String phone = request.getParameter("phone");
        String validate = request.getParameter("validate");
        String imsi = request.getParameter("imsi");
        String gpslon = request.getParameter("gpslon");
        String gpslat = request.getParameter("gpslat");
        if(imei == null || imei.equalsIgnoreCase("") || phone == null || phone.equalsIgnoreCase("") || validate == null || validate.equalsIgnoreCase("")) {
            ResultPhoneCode phoneCodeResult = new ResultPhoneCode();
            phoneCodeResult.rescode = "80001";
            phoneCodeResult.resdes = "参数不完整";
            return JsonUtil.toJson(phoneCodeResult);
        }
        LoginHelper loginHelper = new LoginHelper();
//        imei = UUID.randomUUID().toString();
//        System.out.println("imei:"+imei);
        String responseText = loginHelper.requestPhoneCode(phone, imei, validate);
        if(responseText != null && !responseText.equalsIgnoreCase("")) {
            ResultPhoneCode phoneCodeResult = JsonUtil.fromJson(responseText, ResultPhoneCode.class);
            if(phoneCodeResult.rescode .equalsIgnoreCase("200")) {
            } else if(phoneCodeResult.rescode .equalsIgnoreCase("4001")) {
                fillUserInfo(phoneCodeResult.userid, phone, imei, imsi, gpslon, gpslat);
            } else {
            }
        }
        return responseText;
    }
    
    private String processLogin(HttpServletRequest request) throws SQLException{
        String phone = request.getParameter("phone");
        String logincode = request.getParameter("logincode");
        String imei = request.getParameter("imei");
        String imsi = request.getParameter("imsi");
        String gpslon = request.getParameter("gpslon");
        String gpslat = request.getParameter("gpslat");
        if(imei == null || imei.equalsIgnoreCase("") || phone == null || phone.equalsIgnoreCase("") || logincode == null || logincode.equalsIgnoreCase("")) {
            ResultPhoneCode phoneCodeResult = new ResultPhoneCode();
            phoneCodeResult.rescode = "80001";
            phoneCodeResult.resdes = "参数不完整";
            return JsonUtil.toJson(phoneCodeResult);
        }
        LoginHelper loginHelper = new LoginHelper();
        String responseText = loginHelper.requestLogin(phone, logincode, imei);
        if(responseText != null && !responseText.equalsIgnoreCase("")) {
            ResultLogin resultLogin = JsonUtil.fromJson(responseText, ResultLogin.class);
            if(resultLogin != null) {
                if(resultLogin.rescode.equalsIgnoreCase("200")) {
                    String userId = resultLogin.userid;
                    fillUserInfo(userId, phone, imei, imsi, gpslon, gpslat);
                }
            }
        }
        return responseText;
    }
    
    private void fillUserInfo(String userid, String phone, String imei, String imsi,String gpslon, String gpslat) throws SQLException{
        AccountDao dao = new AccountDao();
        Account account = dao.queryById(userid);
        if(account == null) {
            account = new Account();
            account.setUserid(userid);
            account.setPhone(phone);
            boolean success = dao.add(account);
        }
        DeviceDao deviceDao = new DeviceDao();
        ArrayList<Device> devices = deviceDao.queryByUserid(userid);
        if(devices == null || devices.size() <= 0) {
            Device device = new Device();
            device.setDeviceid(UUID.randomUUID().toString());
            device.setUserid(userid);
            device.setImei(imei);
            device.setImsi(imsi);
            device.setGpslon(gpslon);
            device.setGpslat(gpslat);
            boolean success = deviceDao.add(device);
        }
        ApplyInfoHelper applyHelper = new ApplyInfoHelper();
        ArrayList<ApplyParams> applyParamsList = applyHelper.getApplyParamsInfo(userid);
        
        if(applyParamsList != null && applyParamsList.size() > 0) {
            
            for(ApplyParams params : applyParamsList) {
                if(params.carid != null && !params.carid.equalsIgnoreCase("")) {
                    CarDao carDao = new CarDao();
                    Car car = carDao.queryByCarid(params.carid);
                    if(car == null) {
                        car = new Car(params.carid, userid, params.licenseno, params.engineno, params.carmodel, params.carregtime, params.vehicletype, params.cartypecode,params.envGrade);
                        carDao.addOrUpdate(car);
                    } else {
                        if(car.hasEmptyValue()){
                            if(TextUtil.isEmpty(car.licenseno) && TextUtil.isEmpty(params.licenseno)) car.licenseno = params.licenseno;
                            if(TextUtil.isEmpty(car.engineno) && TextUtil.isEmpty(params.engineno)) car.engineno = params.engineno;
                            if(TextUtil.isEmpty(car.carmodel) && TextUtil.isEmpty(params.carmodel)) car.carmodel = params.carmodel;
                            if(TextUtil.isEmpty(car.carregtime) && TextUtil.isEmpty(params.carregtime)) car.carregtime = params.carregtime;
                            if(TextUtil.isEmpty(car.vehicletype) && TextUtil.isEmpty(params.vehicletype)) car.vehicletype = params.vehicletype;
                            if(TextUtil.isEmpty(car.cartype) && TextUtil.isEmpty(params.cartypecode)) car.cartype = params.cartypecode;
                            carDao.addOrUpdate(car);
                        }
                    }
                }
                if(!TextUtil.isEmpty(params.driverlicenseno)) {
                    EnterPersonDao personDao = new EnterPersonDao();
                    EnterPerson person = personDao.queryByLicenseno(params.driverlicenseno);
                    if(person == null) {
                        person = new EnterPerson(userid, params.carid, params.driverlicenseno, params.drivername, "", "", "", "");
                        personDao.addOrUpdate(person);
                    }
                }
            }
        } else {
            logger.info("获取车辆信息失败");
        }
        
    }

    public static void main(String[] args) {
        try {
            //fbb
//            new LoginServlet().fillUserInfo("0719afcd79e847c8abfb4c6d0cf8b02b", "18701501627", "", "", "", "");
            //zhc
            new LoginServlet().fillUserInfo("0E0CDBD816C846A9A9BF71AA57C94851", "17600960986", "526bdde9-3bda-314d-9743-305c70f92451", "", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
