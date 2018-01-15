package com.fbb.jjz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMSUtil {
    public static String appKey = "23765545";
    public static String secret = "5c03b64cc3fde46f93b2e873b63621e6";
    private static Logger logger = LoggerFactory.getLogger(SMSUtil.class);
    
//    public static void main(String[] args) {
//        new SMSUtil().sendSms();
//    }
    
    public boolean sendSms(String phoneNums) {
        logger.info("start to send sms: "+phoneNums);
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23765545", secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend( "" );
        req.setSmsType( "normal" );
        req.setSmsFreeSignName( "冯贝" );
        req.setSmsParamString( "{name:'进京证办理功能',time:'已开放'}");
        req.setRecNum(phoneNums);
        req.setSmsTemplateCode( "SMS_62825243" );
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            logger.debug("[send result]"+rsp.getBody());
            return true;
        } catch (ApiException e) {
            e.printStackTrace();
            logger.error("[EXCEPTION] SMSUtil ", e);
        }
        return false;
    }

}
