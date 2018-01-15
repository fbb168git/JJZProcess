package com.fbb.jjz.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.fbb.jjz.exception.ServerDisableException;
import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.util.CipherUtil;

public class LoginHelper {
    private static Map<String, String> map = new HashMap<String, String>();

    private String createPhoneCodeParams(String phone, String deviceId, String NECaptchaValidate) {
        map.clear();
        JSONObject localJSONObject = new JSONObject();
        try {
            localJSONObject.put("phone", phone);
            localJSONObject.put("smsflag", "01");
            localJSONObject.put("deviceId", deviceId);
            map.put("phone", phone);
            map.put("smsflag", "01");
            map.put("deviceId", deviceId);
            CipherUtil cipherUtil = new CipherUtil();
            localJSONObject.put("sign", cipherUtil.sortSignParameters(map));
            JSONObject commJSONObject = cipherUtil.addCommonParameters(localJSONObject);
            commJSONObject.put("NECaptchaValidate", NECaptchaValidate);
            String result = URLEncoder.encode((commJSONObject).toString(), "UTF-8");
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String createLoginParams(String phone, String logincode, String deviceid) {
        map.clear();
        JSONObject localJSONObject = new JSONObject();
        String token = "190e35f7e07a742cf1e";
        token = "";
        try {
            localJSONObject.put("phone", phone);
            localJSONObject.put("valicode", logincode);
            // localJSONObject.put("token", JPushInterface.getRegistrationID(this));
            localJSONObject.put("token", token);
            localJSONObject.put("devicetype", "1");
            localJSONObject.put("vertype", "1");
            localJSONObject.put("deviceid", deviceid);
            localJSONObject.put("source", "0");
            map.put("phone", phone);
            map.put("valicode", logincode);
            // map.put("token", JPushInterface.getRegistrationID(this));
            map.put("token", token);
            map.put("devicetype", "1");
            map.put("vertype", "1");
            map.put("deviceid", deviceid);
            map.put("source", "0");
            CipherUtil cipherUtil = new CipherUtil();
            localJSONObject.put("sign", cipherUtil.sortSignParameters(map));
            String result = URLEncoder.encode(cipherUtil.addCommonParameters(localJSONObject).toString(), "UTF-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    final String phoneCodeUrl = "https://bjjj.zhongchebaolian.com/industryguild_mobile_standard_self2.1.2/mobile/standard/neteaseImageValidate/v2";

    public String requestPhoneCode(String phone, String deviceId, String validate) {
        String params = createPhoneCodeParams(phone, deviceId, validate);
        String response = null;
        try {
            response = HttpUtil.sendHtpps(phoneCodeUrl, params);
        } catch (ServerDisableException e) {
            e.printStackTrace();
        }
//        {"ssidcode":null,"rescode":"200","resdes":"短信发送成功","userid":null}
        return response;
    }

    final String loginUrl = "https://bjjj.zhongchebaolian.com/industryguild_mobile_standard_self2.1.2/mobile/standard/login";

    public String requestLogin(String phone, String logincode, String deviceid) {
        String params = createLoginParams(phone, logincode, deviceid);
        String response = null;
        try {
            response = HttpUtil.sendHtpps(loginUrl, params);
        } catch (ServerDisableException e) {
            e.printStackTrace();
        }
//        res:{"policeno":"","ssid":"JVSXZ49513812415515319562656","provincetiny":null,"userid":"0E0CDBD816C846A9A9BF71AA57C94851","provincecode":null,"citycode":"","userType":"1","accesstoken":null,"rescode":"200","resdes":"登陆成功"}
        return response;
    }
    
    public static void main(String[] args) {
        LoginHelper helper = new LoginHelper();
        String res = helper.requestLogin("17600960986", "266988", "fff80239-d6c8-4af1-8607-f66d3f246b6c");
        System.out.println("res:"+res);
    }

}
