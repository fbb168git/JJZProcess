package com.fbb.jjz.test;

import com.fbb.jjz.net.HttpUtil;

public class A22_toVehicleType_Pager {
    public static String URL = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/toVehicleType";
    public static String PARAMS = "applyid=&carid=19422944&userid=0E0CDBD816C846A9A9BF71AA57C94851&gpslon=4.9E-324&gpslat=4.9E-324&imei=526bdde9-3bda-314d-9743-305c70f92451&imsi=&licenseno=%E8%B1%ABUK9852&cartype=02&appsource=";
    public static String REFER = "https://enterbj.zhongchebaolian.com/enterbj/jsp/enterbj/index.html";
    
    /**
     * 
     * @param userId 用户id
     * @param carid 汽车id
     * @param licenseno 车牌号URLEcode编码
     * @param cartype 
     */
    public void run(String userId, String carid, String licenseno,String cartype) {
        String gpslon = "4.9E-324";
        String gpslat = "4.9E-324";
        String imei = "526bdde9-3bda-314d-9743-305c70f92451";
        String imsi = "";
        String appsource = "";
        String params = "applyid=&carid="+carid+"&userid="+userId+"&gpslon="+gpslon+"&gpslat="+gpslat+"&imei="+imei+"&imsi="+imsi+"&licenseno="+licenseno+"&cartype="+cartype+"&appsource="+appsource;
        String result = HttpUtil.sendHtpps2(URL, params, REFER);
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        new A22_toVehicleType_Pager().run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852","02");
        //vehicletype
        //03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车
    }

}
