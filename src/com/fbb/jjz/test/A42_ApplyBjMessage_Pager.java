package com.fbb.jjz.test;

import java.util.Date;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fbb.jjz.net.HttpUtil;
import com.fbb.jjz.util.Utils;

public class A42_ApplyBjMessage_Pager {
    public static String URL = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/applyBjMessage";
    public static String PARAMS =
            "appsource=bjjj&hiddentime=2017-12-27+20%3A36%3A30&applyid=&userid=0E0CDBD816C846A9A9BF71AA57C94851&applystatus=&carid=19422944&gpslon=4.9E-324&gpslat=4.9E-324&imei=526bdde9-3bda-314d-9743-305c70f92451&imsi=&vehicletype=03&licenseno=%E8%B1%ABUK9852&envGrade=3&carmodel=JNJ7153&carregtime=2013-05-15";
    public static String REFER = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/addcartype";
//    appsource=bjjj&hiddentime=2018-01-03+17%3A07%3A31&applyid=039201801010212503428269&userid=0E0CDBD816C846A9A9BF71AA57C94851&applystatus=&carid=19422944&gpslon=116.471818&gpslat=40.026413&imei=82e22736-ce3f-3a03-a790-56c0634abd40&imsi=460025013937347&vehicletype=03&licenseno=%E8%B1%ABUK9852&envGrade=3&carmodel=JNJ7153&carregtime=2013-05-15
    /**
     * 获取填写申请信息页
     * 
     * @param userId 用户id
     * @param carid 汽车id
     * @param licenseno 车牌号URLEcode编码
     * @param vehicletype 03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车
     * @param envGrade 环保等级 3
     * @param carmodel 车辆品牌型号 无中文
     * @param carregtime 车辆注册日期 yyyy-MM-dd
     */
    public String run(String userid, String carid, String applyid, String licenseno, String vehicletype, String envGrade, String carmodel, String carregtime) {
        String appsource = "bjjj";
//        String hiddentime = "2017-12-27+20%3A36%3A30";
        long time = new Date().getTime();
        String hiddentime = Utils.formatDateText(time - (60 + new Random().nextInt(120))*1000 - new Random().nextInt(1000));
//        String applyid = "039201801010212503428269";
        String gpslon = "4.9E-324";
        String gpslat = "4.9E-324";
//        String imei = "526bdde9-3bda-314d-9743-305c70f92451";
        String imei = "82e22736-ce3f-3a03-a790-56c0634abd40";
        String imsi = "";
        String applystatus = "";

        String params =
                "appsource=" + appsource + "&hiddentime=" + hiddentime + "&applyid=" + applyid + "&userid=" + userid + "&applystatus=" + applystatus + "&carid=" + carid + "&gpslon=" + gpslon + "&gpslat=" + gpslat + "&imei=" + imei + "&imsi=" + imsi + "&vehicletype=" + vehicletype + "&licenseno="
                        + licenseno + "&envGrade=" + envGrade + "&carmodel=" + carmodel + "&carregtime=" + carregtime;
        String result = HttpUtil.sendHtpps2(URL, params, REFER);
        System.out.println(result);
        return result;
    }
    
    public String[] getEnterPersonInfo(String userid, String carid, String applyid, String licenseno, String vehicletype, String envGrade, String carmodel, String carregtime){
        String response = run(userid, carid, applyid, licenseno, vehicletype, envGrade, carmodel, carregtime);
        String[] result = new String[2];
        if(response != null && !response.equalsIgnoreCase("")) {
            Document doc = Jsoup.parse(response);
            Elements inputs = doc.select("input#carusername");
            if(inputs.size() > 0) {
                result[0] = inputs.get(0).attr("value");
            }
            inputs = doc.select("input#carusernum");
            if(inputs.size() > 0) {
                result[1] = inputs.get(0).attr("value");
            }
        }
        return result;
    }
    
    public String getCarEngineNumber(String userid, String carid, String applyid, String licenseno, String vehicletype, String envGrade, String carmodel, String carregtime){
        String response = run(userid, carid, applyid, licenseno, vehicletype, envGrade, carmodel, carregtime);
        String result = "";
        if(response != null && !response.equalsIgnoreCase("")) {
            Document doc = Jsoup.parse(response);
            Elements inputs = doc.select("input#carenginenumber");
            if(inputs.size() > 0) {
                result = inputs.get(0).attr("value");
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        new A42_ApplyBjMessage_Pager().run("0E0CDBD816C846A9A9BF71AA57C94851","19422944", "",  "%E8%B1%ABUK9852", "03", "3", "JNJ7153", "2013-05-15");
        String[] personInfo = new A42_ApplyBjMessage_Pager().getEnterPersonInfo("0E0CDBD816C846A9A9BF71AA57C94851","19422944", "039201801010212503428269",  "%E8%B1%ABUK9852", "03", "3", "JNJ7153", "2013-05-15");
        System.out.println("userName:"+personInfo[0]+ " | userNo:"+personInfo[1]);

        //        run("d131c66355654e9b83bdc4f0db55c01d", "254698", "%e8%b1%abAL2Z53", "03", "3", "SGM7202EAAB", "2015-01-19");
        // bitmapToBase64
        // zoomImg
        // run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852","JNJ7153","2013-05-15");

        // bridge.registerHandler('webviewGetImage', function(data, responseCallback) {
        // var imgalt = $('div[class=img_f][data='+data.position+']').find('img');
        // imgalt.attr('src','data:image/jpeg;base64,'+data.image);
        // $('div[class=img_f][data='+data.position+']').find('input').val(data.image)
        // var responseData = { 'rescode':'200' }
        // responseCallback(responseData)
        // })

    }

}
