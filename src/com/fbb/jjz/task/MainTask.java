package com.fbb.jjz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.exception.ServerDisableException;
import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.util.SMSUtil;

public class MainTask {
    private static Logger logger = LoggerFactory.getLogger(MainTask.class);

    // private static final String myUserId = "0719afcd79e847c8abfb4c6d0cf8b02b";
    // private static final String falseUserId = "0856uijkpl9i78hninxc98fcplmkawsd";
    private static final String userId2 = "0E0CDBD816C846A9A9BF71AA57C94851";
    private static final String mainPager = "https://bjjj.zhongchebaolian.com/app_web/jsp/homepage.jsp";

    public static final String JJZBLMainPager = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/maintPage.jsp";

    public static final String enterCarList = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/entercarlist";
    public static final String enterCarListParms = "userid=0719afcd79e847c8abfb4c6d0cf8b02b&appkey=kkk&deviceid=ddd&timestamp=2017-11-06+00%3A02%3A20&token=20CE2633DF5FF796763EE300582B6A62&sign=aaNVCC001096590c26a62cd2a821040449ad1e8e21a311ca1b&platform=02&appsource=";

    public static final String test = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/curtime_03";
    public static final String testReferer = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/toVehicleType";

    public static final String test2 = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/addcartype";
    public static final String testparams2 =
            "appsource=bjjj&hiddentime=2017-11-06+00%3A52%3A56&applyid=&userid=0719afcd79e847c8abfb4c6d0cf8b02b&applystatus=&carid=643359&gpslon=116.697797&gpslat=40.289327&imei=ea56f140-afc1-3bf0-bfce-2b0a280d0f1c&imsi=460027015268128&cartype=02&licenseno=%E8%B1%ABHFB768&vehicletype=03";
    public static final String testReferer2 = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/toVehicleType";

    private static boolean isAviable = false;
    private static int unavailableCount = 0;
    private static final int ENSURE_UNAVAILABLE_COUNT = 3;// 连续3次不可用确认jjz服务关闭
    private static final long LISTEN_GAP = 1000 * 60;
    boolean taskTag = false;
    Thread mainThread = null;
    String url = test + "?userid=" + userId2;

    Runnable task = new Runnable() {

        @Override
        public void run() {
            while (taskTag) {
                try {
                    logger.info("request url " + url);
                    String result = HttpUtil.sendHtpps(url, "", testReferer);
                    logger.info("result:" + result);
                    if (!isAviable) {
                        logger.error("***************JJZService was started***************");
                        new SMSUtil().sendSms("18701501627,15001083657");
                    }
                    isAviable = true;
                    unavailableCount = 0;
                } catch (ServerDisableException e) {
                    unavailableCount++;
                    if (e.getResponseCode() == 302) {
                        logger.info("ResponseCode " + e.getResponseCode());
                    } else {
                        logger.error("ResponseCode " + e.getResponseCode());
                    }
                    if (isAviable && unavailableCount >= ENSURE_UNAVAILABLE_COUNT) {
                        logger.error("***************JJZService was stopped***************");
                        isAviable = false;
                    }
                } catch (Exception e) {
                    logger.error("[EXCEPTION] task ", e);
                }
                try {
                    Thread.sleep(LISTEN_GAP);
                } catch (InterruptedException e) {
                    logger.error("[EXCEPTION] thread sleep ", e);
                }
            }
        }
    };

    private void startTask() {
        logger.info("-------------------Server Start-------------------");
        taskTag = true;
        try {
            mainThread = new Thread(task);
            mainThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[EXCEPTION] startTask ", e);
        }
    }

    public static void main(String[] args) {
//      try {
//      String result = HttpUtil.sendHtpps("https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/checkenvgrade", 
//          "appsource=bjjj&userid=0719afcd79e847c8abfb4c6d0cf8b02b&carid=643359&licenseno=%E8%B1%ABHFB768&carmodel=GTM7150AE&carregtime=2015-02-15", 
//          "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/addcartype");
//      logger.info("result " + result);
//  } catch (ServerDisableException e) {
//      e.printStackTrace();
//  }
        
        
        // String result = HttpUtil.sendHtpps(JJZBLMainPager, "");
        // String result = HttpUtil.sendHtpps2(test2, "");
        // try {
        // String result = HttpUtil.sendHtpps(test+"?userid="+myUserId, "", testReferer);
        // logger.info("result:"+result);
        // } catch (ServerDisableException e) {
        // e.printStackTrace();
        // if(e.getResponseCode() == 302) {
        // logger.info("ResponseCode " + e.getResponseCode());
        // } else {
        // logger.error("ResponseCode " + e.getResponseCode());
        // }
        // }

        // logger.info("测试短信通道");
        // new SMSUtil().sendSms("18701501627");
        
//         new MainTask().startTask();
    }

}
