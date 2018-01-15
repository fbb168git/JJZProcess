package com.fbb.jjz.test;

import com.fbb.jjz.net.HttpUtil;

public class A2_GetCurPaperCount {
    public static final String URL = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/paperCount";
    public static String PARAMS = "userId=0719afcd79e847c8abfb4c6d0cf8b02b&appsource=";
    public static final String REFER = "https://enterbj.zhongchebaolian.com/enterbj/jsp/enterbj/index.html";

    private static void run(String userId) {
        PARAMS = "userId="+userId+"&appsource=";
        String result = HttpUtil.sendHtpps2(URL, PARAMS, REFER);
        System.out.println(result);

    }

    public static void main(String[] args) {
       run("0719afcd79e847c8abfb4c6d0cf8b02b");

    }

}
