package com.fbb.jjz.test;

import com.fbb.jjz.net.HttpUtil;

public class B1_Mobile_Pager {
    public static String URL = "https://c.dun.163yun.com/api/v1/mobile.html?captchaId=5622fb225dcd429c95808563b059cf21&deviceId=353627073803879&os=android&osVer=7.1.1&sdkVer=1.0.0&title=&debug=false&width=329";
    public static String PARAMS = "";
    public static String REFER = "";
    
    public static void run() {
        String result = HttpUtil.sendGet(URL, PARAMS);
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        run();
        //vehicletype
        //03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车
    }
}
