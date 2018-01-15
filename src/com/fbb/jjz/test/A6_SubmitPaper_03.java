package com.fbb.jjz.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.bean.ApplyParams;
import com.fbb.jjz.net.BaseResp;
import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.util.JsonUtil;
import com.fbb.jjz.util.SignUtil;
import com.fbb.jjz.util.Utils;


public class A6_SubmitPaper_03 {
    private static Logger logger = LoggerFactory.getLogger(A6_SubmitPaper_03.class);
    
    public static String URL = "https://enterbj.zhongchebaolian.com/enterbj-img/platform/enterbj/submitpaper_03";
    public static String PARAMS = "appsource=bjjj&hiddentime=2017-12-27+21%3A42%3A21&inbjentrancecode1=05&inbjentrancecode=19&inbjduration=2&inbjtime=2017-12-29&appkey=&deviceid=&token=&timestamp=2017-12-28+14%3A13%3A14&userid=0E0CDBD816C846A9A9BF71AA57C94851&licenseno=%E8%B1%ABUK9852&engineno=DL0234&cartypecode=02&vehicletype=03&drivingphoto=&carphoto=&drivername=%E5%91%A8%E6%B5%B7%E8%B6%85&driverlicenseno=410882198910205533&driverphoto=&personphoto=&gpslon=4.9E-324&gpslat=4.9E-324&phoneno=&imei=526bdde9-3bda-314d-9743-305c70f92451&imsi=&carid=19422944&carmodel=JNJ7153&carregtime=2013-05-15&envGrade=3&imageId=1922017-12-290E0CDBD816C846A9A9BF71AA57C94851DL023402410882198910205533194229442017-12-28+14%3A13%3A14&code=&sign=aaNVCC0010b6602e23651245aac24faff73f8da407aa1ad342&platform=02";
    public static String PARAMS2 = "appsource=bjjj&hiddentime=2017-12-19+01%3A37%3A13&inbjentrancecode1=05&inbjentrancecode=19&inbjduration=7&inbjtime=2017-12-20&appkey=&deviceid=&token=&timestamp=2017-12-19+01%3A46%3A02&userid=0719afcd79e847c8abfb4c6d0cf8b02b&licenseno=%E8%B1%ABHFB768&engineno=Q042607&cartypecode=02&vehicletype=03&drivingphoto=&carphoto=&drivername=%E5%86%AF%E8%B4%9D%E8%B4%9D&driverlicenseno=410882198710125512&driverphoto=&personphoto=&gpslon=116.697685&gpslat=40.289178&phoneno=&imei=ea56f140-afc1-3bf0-bfce-2b0a280d0f1c&imsi=460010960101602&carid=643359&carmodel=GTM7150AE&carregtime=2015-02-15&envGrade=3&imageId=1972017-12-200719afcd79e847c8abfb4c6d0cf8b02bQ042607024108821987101255126433592017-12-19+01%3A46%3A02&code=&sign=aaNVCC001056c3bcc0750621312209a1aa10fe25f911d33711&platform=02";
    public static String REFER = "https://enterbj.zhongchebaolian.com/enterbj-img/platform/enterbj/loadotherdrivers";
    
    public static final int INBJDURATION = 2;
    /**
     * 提交申请
     * * @param userId 用户id
     * @param carid 汽车id
     * @param licenseno 车牌号URLEcode编码
     * 
     * @param cartypecode 
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
     * @param personphoto 本人持身份证照
     *  @param drivername 驾驶员姓名
     * @param driverlicenseno 驾驶证号
     */
    public String run(String userid,String carid,String licenseno,
                           String cartypecode,String vehicletype,String envGrade,
                           String carmodel,String carregtime,String engineno,
                           String drivingphoto,String carphoto,String driverphoto,String personphoto,
                           String drivername,String driverlicenseno) {
        System.out.println("start commit ......");
        Date date = new Date();
        long time = date.getTime();
        
        final String appsource = "bjjj";//TODO jjzx时不需要 sign timestamp platform？                        
//        String hiddentime = "2017-12-27+20%3A36%3A30";
        String hiddentime =  Utils.formatDateText(time - (60 + new Random().nextInt(120))*1000 - new Random().nextInt(1000));//TODO US服务器有误差？
//        String gpslon = "4.9E-324";//TODO 
//        String gpslat = "4.9E-324";//TODO
        String gpslon = "116.697682";//TODO 
        String gpslat = "40.28918";//TODO 
//        String imei = "526bdde9-3bda-314d-9743-305c70f92451";//TODO random
        String imei = "82e22736-ce3f-3a03-a790-56c0634abd40";//TODO random
//        String imsi = "";
        String imsi = "460025013937347";
        String inbjentrancecode1 = "05";//进京地区
        String inbjentrancecode = "19";//进京入口
//        String[] randomArea = JinJingAreaUtil.randomArea();
//        String inbjentrancecode1 = randomArea[0];
//        String inbjentrancecode = randomArea[1];
        String inbjduration = INBJDURATION+"";//进京天数
//        String inbjtime = "2017-12-29";//进京日期 yyyy-MM-dd
//        String inbjtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(time+24*60*60*1000));//TODO US服务器有误差？
        String inbjtime = new SimpleDateFormat("yyyy-M-d",Locale.SIMPLIFIED_CHINESE).format(new Date(time+24*60*60*1000));//TODO US服务器有误差？
        
        String appkey = "";
        String deviceid = "82e22736-ce3f-3a03-a790-56c0634abd40";
        String token = "";
        
//        String timestamp = "2017-12-28+14%3A13%3A14";
        String timestamp = Utils.formatDateText(time);
        
        String phoneno = "";
//        String imageId = "1922017-12-290E0CDBD816C846A9A9BF71AA57C94851DL023402410882198910205533194229442017-12-28+14%3A13%3A14";
//        String sign = "aaNVCC0010b6602e23651245aac24faff73f8da407aa1ad342";
        String imageId = inbjentrancecode+inbjduration+inbjtime+userid+engineno+cartypecode+driverlicenseno+carid;
        String localAppKey = "0791682354";//TODO 不同账号值不同？
        String sign = new SignUtil().createCommitSign(localAppKey+imageId+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))+localAppKey);
        imageId = imageId + timestamp;
//        System.out.println("imageId:"+imageId);
//        System.out.println("sign:"+sign);
        
        String code = "";
        String platform = "02";
        String params = "appsource="+appsource+"&hiddentime="+hiddentime+
                "&inbjentrancecode1="+inbjentrancecode1+"&inbjentrancecode="+inbjentrancecode+"&inbjduration="+inbjduration+"&inbjtime="+inbjtime+
                "&appkey="+appkey+"&deviceid="+deviceid+"&token="+token+"&timestamp="+timestamp+
                "&userid="+userid+"&licenseno="+licenseno+"&engineno="+engineno+"&cartypecode="+cartypecode+"&vehicletype="+vehicletype+
                "&drivingphoto="+drivingphoto+"&carphoto="+carphoto+"&drivername="+drivername+"&driverlicenseno="+driverlicenseno+"&driverphoto="+driverphoto+"&personphoto="+personphoto+
                "&gpslon="+gpslon+"&gpslat="+gpslat+"&phoneno="+phoneno+"&imei="+imei+"&imsi="+imsi+"&carid="+carid+"&carmodel="+carmodel+"&carregtime="+carregtime+
                "&envGrade="+envGrade+"&imageId="+imageId+"&code="+code+"&sign="+sign+"&platform="+platform;
        logger.info(params);
        String result = HttpUtil.sendHtpps2(URL, params, REFER);
        logger.info(result);
        return result;
    }
    
    public boolean submit(ApplyParams params) {
        String result = run(params.userid, params.carid, params.licenseno, params.cartypecode, params.vehicletype, params.envGrade, params.carmodel, params.carregtime, params.engineno, params.drivingphoto, params.carphoto, params.driverphoto, params.personphoto, params.drivername, params.driverlicenseno);
        BaseResp resBean = JsonUtil.fromJson(result, BaseResp.class);
        if(resBean != null && "200".equalsIgnoreCase(resBean.rescode) ) {
            return true;
        } else {
            logger.error(result);
            return false;
        }
    }
    
    public static void main(String[] args) {
//        run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852",
//            "02","03","3",
//            "JNJ7153","2013-05-15","DL0234",
//            "","","","",
//            "%E5%91%A8%E6%B5%B7%E8%B6%85","410882198910205533");
        
        testCreateSign();
    }

    private static void testCreateSign() {
        //        aaNVCC001056c3bcc0750621312209a1aa10fe25f911d33711
        //        String imageId = "1972017-12-200719afcd79e847c8abfb4c6d0cf8b02bQ042607024108821987101255126433592017-12-19 01:46:02";
//        String imageId = "1972017-12-31F867CCD9C7DE499681AD24BD9491648AHA1169602130481198911032114171428602017-12-30 01:46:51";
//        String sign = "aaNVCC0010f5135e1dc03f4598aa05c59fbb77db7d0d64db19";
        String imageId = "1922018-1-20E0CDBD816C846A9A9BF71AA57C94851DL023402410882198910205533194229442018-01-01 02:12:50";
        String sign = "aaNVCC0010f71e4eaf5e301f08fe6d1bcd66df08f413f09751";
        String localAppKey = "0791682354";//TODO 不同账号值不同？
        sign = new SignUtil().sign(localAppKey+imageId+localAppKey);
        System.out.println("imageId:"+imageId);
        System.out.println("sign:"+sign);
    }

}
