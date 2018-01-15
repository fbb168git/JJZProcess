package com.fbb.jjz.util;

import java.util.HashMap;
import java.util.Map;

import com.fbb.jjz.net.HttpUtil;

public class SignUtil {
    public String createToken(String userId, long currentTimeStamp) {
        // long currentTimeStamp = System.currentTimeMillis();
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userId);
        map.put("appkey", "kkk");
        map.put("deviceid", "ddd");
        map.put("timestamp", currentTimeStamp + "");
        String formatUrlMap = Utils.formatUrlMap(map, false, false);
        String strwithsecret = currentTimeStamp + formatUrlMap + currentTimeStamp;
        String token = Utils.MD5(strwithsecret).toUpperCase();
        return token;
    }

//    private static String getValueFromAndroid(String msg) {
//        String sign = "";
//        try {
//            Socket socket = new Socket("127.0.0.1", 8000);
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            dos.writeUTF(msg);
//            sign = dis.readUTF();
//            System.out.println("sign:" + sign);
//            socket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sign;
//    }

    public String createCommitSign(String text) {
        return sign(text);
    }
    
    public String sign(String text) {
        return HttpUtil.sendPost("http://19b5v18147.51mypc.cn/SignServer/sign", "c="+text);
    }


}
