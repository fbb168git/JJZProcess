package com.fbb.jjz.test;

import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.util.JsonUtil;
import com.google.gson.Gson;

public class A4_CheckEnvGrade {
    public static String URL = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/checkenvgrade";
    public static String PARAMS = "appsource=bjjj&userid=0719afcd79e847c8abfb4c6d0cf8b02b&carid=643359&licenseno=%E8%B1%ABHFB768&carmodel=GTM7150AE&carregtime=2015-02-15";
    public static String REFER = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/addcartype";
//{"rescode":"200","envGrade":3}
    public class CheckEnvResult{
        public String rescode;
        public String envGrade;
    }
    /**
     * 
     * @param userId 用户id
     * @param carid 车辆id
     * @param licenseno 车牌号 有中文
     * @param carmodel 车辆品牌型号 无中文
     * @param carregtime 车辆注册日期 yyyy-MM-dd
     */
    public String run(String userId, String carid, String licenseno, String carmodel, String carregtime) {
        System.out.println("start CheckEnvGrade ......");
        String appsource ="bjjj";
        String params = "appsource="+appsource+"&userid="+userId+"&carid="+carid+"&licenseno="+licenseno+"&carmodel="+carmodel+"&carregtime="+carregtime;
        String result = HttpUtil.sendHtpps2(URL, params, REFER);
        System.out.println(result);
        if(result != null && !result.equalsIgnoreCase("")){
            CheckEnvResult envResult = JsonUtil.fromJson(result, CheckEnvResult.class);
            if(envResult!= null && "200".equalsIgnoreCase(envResult.rescode)){
                return envResult.envGrade;
            }
        }
        return null;
    }
    
    public String checkEnvGrade(String userId, String carid, String licenseno, String carmodel, String carregtime) {
        return run(userId, carid, licenseno, carmodel, carregtime);
    }

    public static void main(String[] args) {
        new A4_CheckEnvGrade().run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852","JNJ7153","2013-05-15");
//        run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852","JNJ7153","2013-05-15");
//        run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852","JNJ7153","2015-01-15");
    }

}
