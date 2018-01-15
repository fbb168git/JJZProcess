package com.fbb.jjz.test;

import com.fbb.jjz.net.HttpUtil;

public class A5_LoadOtherDrivers_Pager {
    public static String URL = "https://enterbj.zhongchebaolian.com/enterbj-img/platform/enterbj/loadotherdrivers";
    public static String PARAMS = "userid=0E0CDBD816C846A9A9BF71AA57C94851&appsource=bjjj&hiddentime=2017-12-27+18%3A51%3A22&applystatus=&carid=19422944&gpslon=4.9E-324&gpslat=4.9E-324&imei=526bdde9-3bda-314d-9743-305c70f92451&imsi=&carmodel=JNJ7153&carregtime=2013-05-15&envGrade=3&vehicletype=03&cartype=02&licenseno=%E8%B1%ABUK9852&engineno=DL0234&drivingphoto=&carphoto=&enterdrivername=%E5%91%A8%E6%B5%B7%E8%B6%85&enterdriverno=410882198910205533&driverphoto=&driveridcardphoto=";
    public static String REFER = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/applyBjMessage";
    
    /**
     * 请求进京证在线申领页
     * 
     * @param userId 用户id
     * @param carid 汽车id
     * @param licenseno 车牌号URLEcode编码
     * 
     * @param cartype 
     * @param vehicletype 03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车
     * @param envGrade 环保等级 3
     * 
     * @param carmodel 车辆品牌型号 无中文
     * @param carregtime 车辆注册日期 yyyy-MM-dd
     * @param engineno 发动机号
     * 
     * @param drivingphoto 行驶证正面照片
     * @param carphoto 车辆正面照片
     * @param driverphoto  驾驶证正面照片
     * @param driveridcardphoto 本人持身份证照
     *  @param enterdrivername 驾驶员姓名
     * @param enterdriverno 驾驶证号
     */
    public static void run(String userid, String carid, String licenseno, 
                           String cartype, String vehicletype, String envGrade, 
                           String carmodel, String carregtime,String engineno,
                           String drivingphoto,String carphoto,String driverphoto,String driveridcardphoto,
                           String enterdrivername,String enterdriverno) {
        String appsource = "bjjj";
        String hiddentime = "2017-12-27+20%3A36%3A30";
        String gpslon = "4.9E-324";
        String gpslat = "4.9E-324";
        String imei = "526bdde9-3bda-314d-9743-305c70f92451";
        String imsi = "";
        String applystatus = "";
        String params = "userid="+userid+"&appsource="+appsource+"&hiddentime="+hiddentime+"&applystatus="+applystatus+"&carid="+carid+"&gpslon="+gpslon+"&gpslat="+gpslat+"&imei="+imei+"&imsi="+imsi+"&carmodel="+carmodel+"&carregtime="+carregtime+"&envGrade="+envGrade+"&vehicletype="+vehicletype+"&cartype="+cartype+"&licenseno="+licenseno+"&engineno="+engineno
                +"&drivingphoto="+drivingphoto+"&carphoto="+carphoto+"&enterdrivername="+enterdrivername+"&enterdriverno="+enterdriverno+"&driverphoto="+driverphoto+"&driveridcardphoto="+driveridcardphoto;
        System.out.println("params:\n"+params);
        String result = HttpUtil.sendHtpps2(URL, params, REFER);
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852",
            "02","03","3",
            "JNJ7153","2013-05-15","DL0234",
            "","","","",
            "%E5%91%A8%E6%B5%B7%E8%B6%85","410882198910205533");

    }

}
