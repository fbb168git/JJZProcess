package com.fbb.jjz.test;

import com.fbb.jjz.net.HttpUtil;

public class A43_EchoImg {
    public static String URL = "https://enterbj.zhongchebaolian.com/enterbj-img/platform/enterbjImg/echoImg";
    public static String PARAMS = "applyId=&userId=0E0CDBD816C846A9A9BF71AA57C94851&carId=19422944&appsource=bjjj";
    public static String REFER = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/applyBjMessage";
   
    /**
     * 获取回显照片
     */
    public static void run() {
        String result = HttpUtil.sendHtpps2(URL, PARAMS, REFER);
        System.out.println(result);
    }

    public static void main(String[] args) {
        run();
//        run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852","JNJ7153","2013-05-15");
    }

}
