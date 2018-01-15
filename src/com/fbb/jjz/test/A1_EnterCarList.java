package com.fbb.jjz.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.bean.ApplyEnterCar;
import com.fbb.jjz.bean.EnterCarList;
import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.task.AutoHandleTask;
import com.fbb.jjz.util.Utils;
import com.fbb.jjz.util.SignUtil;

public class A1_EnterCarList {
    private static Logger logger = LoggerFactory.getLogger(AutoHandleTask.class);
    public static final String URL = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/entercarlist";
    public static String PARAMS = "userid=0719afcd79e847c8abfb4c6d0cf8b02b&appkey=kkk&deviceid=ddd&timestamp=2017-12-19+02%3A01%3A58&token=E98B0B2301614D097C6CFC3A775D63D1&sign=aaNVCC0010d8867d43ad5b2f5fe871ffbd72c7f513ccb038dc&platform=02&appsource=";
    public static final String REFER = "https://enterbj.zhongchebaolian.com/enterbj/jsp/enterbj/index.html";

    private ArrayList<ApplyEnterCar> run(String userId) {
        System.out.println("start get car list ......");
        Date date = new Date();
        String dateText = Utils.formatDateText(date);
        String token2 = new SignUtil().createToken(userId, date.getTime());

        String timeText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        String sign = new SignUtil().sign(userId + timeText);
        if (sign == null || "".equalsIgnoreCase(sign)) return null;

        String params = "userid=" + userId + "&appkey=kkk&deviceid=ddd&timestamp=" + dateText + "&token=" + token2 + "&sign=" + sign + "&platform=02&appsource=";
        System.out.println("params:" + params);

        String result = HttpUtil.sendHtpps2(URL, params, REFER);
        if (result != null && !"".equalsIgnoreCase(result)) {
            EnterCarList carList = EnterCarList.fromJson(result);
            if (carList != null && carList.getRescode().equalsIgnoreCase("200")) {
                logger.info(result);
                return carList.getDatalist();
            }
        }
        logger.error(result);
        return null;
    }

    public ArrayList<ApplyEnterCar> getEnterCarList(String userid) {
        return run(userid);
    }
    
    public ApplyEnterCar getEnterCar(String userId, String carId) {
        ArrayList<ApplyEnterCar> carList = getEnterCarList(userId);
        if (carList != null && carList.size() > 0) {
            for (ApplyEnterCar car : carList) {
                if (car.getCarid().equalsIgnoreCase(carId)) {
                    return car;
                }
            }
        }
        return null;
    }

    public boolean canApply(String userId, String carId) {
        boolean result = false;
        ApplyEnterCar car = getEnterCar(userId, carId);
        if (car != null) {
            if ("1".equalsIgnoreCase(car.getApplyflag())) {
                result = true;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // run("0719afcd79e847c8abfb4c6d0cf8b02b");
        new A1_EnterCarList().run("0E0CDBD816C846A9A9BF71AA57C94851");// zhc
        // run("d131c66355654e9b83bdc4f0db55c01d");//lk
        // {"datalist":[{"carid":"254698","userid":"d131c66355654e9b83bdc4f0db55c01d","licenseno":"豫AL2Z53","cartype":"02","applyflag":"1","applyid":"","carapplyarr":[]}],"rescode":"200","resdes":"获取申请信息列表成功"}
        // | (A1_EnterCarList^run:35)

    }

}
