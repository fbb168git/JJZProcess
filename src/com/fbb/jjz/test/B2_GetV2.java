package com.fbb.jjz.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.util.SignUtil;

public class B2_GetV2 {
    public static String URL = "https://bjjj.zhongchebaolian.com/industryguild_mobile_standard_self2.1.2/mobile/standard/neteaseImageValidate/v2";
    public static String PARAMS = "%7B%22phone%22%3A%2217600960986%22%2C%22smsflag%22%3A%2201%22%2C%22deviceId%22%3A%22526bdde9-3bda-314d-9743-305c70f92451%22%2C%22sign%22%3A%22aaNVCC001045efa9e63ab1c4f634af2261bdec9d3a20582b5c%22%2C%22timestamp%22%3A%222017-12-27+14%3A59%3A04%22%2C%22platform%22%3A%2202%22%2C%22NECaptchaValidate%22%3A%22xV%5C%5C%5C%5CpCai2NZ1Eo9vtc1Hv7xI08m9FC%5C%5C76CSgmwd1Dlv1z9ufGHC8Vcka4HRlsGHoxp2yVXBpZRNHIvufa086KNE8H7QK9If8U2bEGZJ1IPXNSawY2HhcHa%5C%2FQdeANHmmcitbyCnp9f9vKm7ljiIJlnbQGXg6BD%2BXbMTfFHizDBQz9EBfMTP6MCM1ZUaIbQO6d5oYBHyGnBQDGBIpdXV0PASkRHreOqRBXE7BQ8rLGVyI0S58%5C%5CykYKuZLZVc6FbB5u%5C%2FdmNat9qgKerXqZm5xXDIIrGUeFNi78bx1t4VSN9o%2B7uEwDb29x1ijV0NnEPiCJUsdyzAplIophQCqA7eU92j%5C%5CVaJMaxUHltQkZcawq96ofdfuOeQHJ%2B0%5C%5CpCVz7H0K2bYLDLycK2JyIoo1WjDwojTrq%5C%2F8n8g2MT1PI8swU2fDhasszdaKKDtNuFAjw%5C%2FXAIh6%5C%5C%5C%2Fn8xxaNunnYyuah7jTDCx%5C%5Ci%5C%5C6dMgzKauowxG9qi5TtIfnKanu%2ByrUU%5C%2FdHF3%22%7D";
    public static String REFER = "";
    
//   result {"ct":2,"b":"https://necaptcha.nosdn.127.net/336dd15ffd4e4f2db4a856524bff4b01.jpeg","c":2,"t":"5b0a9a795f4d4dada9745eacac946b10","e":0,"f":"https://necaptcha.nosdn.127.net/c368d83413d641f0a6bc1a3100e2fe4b.png","m":"OK"}
    public static void run() {
        String result = HttpUtil.sendHtpps2(URL, PARAMS, REFER);
        System.out.println(result);
    }
    
    private static String timestamp;
    public static String sortSignParameters(Map<String, String> paramMap) {
        timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE).format(new Date());
        timestamp = "2017-12-27 14:59:04";
        paramMap.put("timestamp", timestamp);
        StringBuilder localStringBuilder = new StringBuilder();
        String[] arrayOfString = (String[]) paramMap.keySet().toArray(new String[0]);
        Arrays.sort(arrayOfString);
        for(int i =0;i<arrayOfString.length;i++) {
            String str1 = arrayOfString[i];
            String str2 = (String) paramMap.get(str1);
            if ((str1.trim().equals("")) || (str2 == null) || (str2.trim().equals(""))) {
            } else {
                localStringBuilder.append(str1).append(str2);
            }
        }
        return new SignUtil().createCommitSign(localStringBuilder.toString());
    }

    public static JSONObject addCommonParameters(JSONObject paramJSONObject) {
        try {
            paramJSONObject.put("timestamp", timestamp);
            paramJSONObject.put("platform", "02");
//            Log.i("ALIVERIFY", paramJSONObject.toString());
            return paramJSONObject;
        } catch (JSONException localJSONException) {
            localJSONException.printStackTrace();
        }
        return null;
    }
    
    private static Map<String, String> map = new HashMap();
    public static String createYDCodeParams(String phone, String NECaptchaValidate){
        map.clear();
        String deviceId = "526bdde9-3bda-314d-9743-305c70f92451";
        Object localObject1 = null;
        JSONObject localJSONObject = new JSONObject();

        try {
            localJSONObject.put("phone", phone);
            localJSONObject.put("smsflag", "01");
            localJSONObject.put("deviceId", deviceId);
            map.put("phone", phone);
            map.put("smsflag", "01");
            map.put("deviceId", deviceId);
            localJSONObject.put("sign", sortSignParameters(map));
            JSONObject commJSONObject = addCommonParameters(localJSONObject);
            commJSONObject.put("NECaptchaValidate", NECaptchaValidate);
            System.out.println("params:"+commJSONObject.toString());
            String result = URLEncoder.encode(commJSONObject.toString(), "UTF-8");
            System.out.println("params:"+commJSONObject.toString());
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }
    
    public static void main(String[] args) {
//        run();
//        String validate = "xV\\\\pCai2NZ1Eo9vtc1Hv7xI08m9FC\\76CSgmwd1Dlv1z9ufGHC8Vcka4HRlsGHoxp2yVXBpZRNHIvufa086KNE8H7QK9If8U2bEGZJ1IPXNSawY2HhcHa\/QdeANHmmcitbyCnp9f9vKm7ljiIJlnbQGXg6BD+XbMTfFHizDBQz9EBfMTP6MCM1ZUaIbQO6d5oYBHyGnBQDGBIpdXV0PASkRHreOqRBXE7BQ8rLGVyI0S58\\ykYKuZLZVc6FbB5u\/dmNat9qgKerXqZm5xXDIIrGUeFNi78bx1t4VSN9o+7uEwDb29x1ijV0NnEPiCJUsdyzAplIophQCqA7eU92j\\VaJMaxUHltQkZcawq96ofdfuOeQHJ+0\\pCVz7H0K2bYLDLycK2JyIoo1WjDwojTrq\/8n8g2MT1PI8swU2fDhasszdaKKDtNuFAjw\/XAIh6\\\/n8xxaNunnYyuah7jTDCx\\i\\6dMgzKauowxG9qi5TtIfnKanu+yrUU\/dHF3";
        createYDCodeParams("17600960986","");
    }
}
