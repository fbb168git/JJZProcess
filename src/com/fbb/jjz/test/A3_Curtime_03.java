package com.fbb.jjz.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.net.BaseResp;
import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.util.JsonUtil;
import com.fbb.jjz.util.TextUtil;

public class A3_Curtime_03 {
    private static Logger logger = LoggerFactory.getLogger(A3_Curtime_03.class);
    
    public static final String URL = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/curtime_03";
    public static final String PARAMS ="";
    public static final String REFER = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/toVehicleType";
    
    public boolean run(String userId) {
        String url = URL + "?userid=" + userId;
        String result = HttpUtil.sendHtpps2(url, PARAMS, REFER);
        System.out.println("result:" + result);
        return true;
    }
    
    String textUserid = "0E0CDBD816C846A9A9BF71AA57C94851";
    
    public boolean isServerAvaiable() {
        String url = URL + "?userid=" + textUserid;
        String result = HttpUtil.sendHtpps2(url, PARAMS, REFER);
        logger.info("isServerAvaiable:" + result);
        if(!TextUtil.isEmpty(result)) {
            BaseResp json = JsonUtil.fromJson(result, BaseResp.class);
            if("200".equalsIgnoreCase(json.rescode)){
                return true;
            }
        }
        return false;
    }
    
    
    public static void main(String[] args) {
//        new A3_Curtime_03().run("0E0CDBD816C846A9A9BF71AA57C94851");
        new A3_Curtime_03().isServerAvaiable();

    }

}
