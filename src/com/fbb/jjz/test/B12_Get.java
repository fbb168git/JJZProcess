package com.fbb.jjz.test;

import com.fbb.jjz.net.HttpUtil;

public class B12_Get {
    public static String URL = "https://c.dun.163yun.com/api/v1/get";
    public static String PARAMS = "id=5622fb225dcd429c95808563b059cf21&f=DNK0bJBiLpY4c4Q%2BpaPUCMEb956RflE4Kf4ntvOZYJLS0%2B%5CD81JsmqJoM3n3G3MAU2aiOC7mqHyv9SZ8xbrygp82vpoLlyhEIPc5QxyPotii6pVGq%2F78HuMUOma2GQzP%2BYvTMBvmPaPCwb4TdrgpeQPW6G%5C4Vm9uYyMQ0u3a4%2BI3GuXB%3A1514529565748&h=true&v=1.1.1";
    public static String REFER = "https://c.dun.163yun.com/api/v1/mobile.html?captchaId=5622fb225dcd429c95808563b059cf21&deviceId=353627073803879&os=android&osVer=7.1.1&sdkVer=1.0.0&title=&debug=false&width=329";
    
//   result {"ct":2,"b":"https://necaptcha.nosdn.127.net/336dd15ffd4e4f2db4a856524bff4b01.jpeg","c":2,"t":"5b0a9a795f4d4dada9745eacac946b10","e":0,"f":"https://necaptcha.nosdn.127.net/c368d83413d641f0a6bc1a3100e2fe4b.png","m":"OK"}
    public static void run() {
        String result = HttpUtil.sendHtpps2(URL, PARAMS, REFER);
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        run();
    }
}
