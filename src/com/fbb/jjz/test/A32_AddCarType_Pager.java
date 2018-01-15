package com.fbb.jjz.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fbb.jjz.net.HttpUtil;

public class A32_AddCarType_Pager {
    public static final String URL = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/addcartype";
    public static final String PARAMS ="appsource=bjjj&hiddentime=2017-12-27+20%3A36%3A30&applyid=&userid=0E0CDBD816C846A9A9BF71AA57C94851&applystatus=&carid=19422944&gpslon=4.9E-324&gpslat=4.9E-324&imei=526bdde9-3bda-314d-9743-305c70f92451&imsi=&cartype=02&licenseno=%E8%B1%ABUK9852&vehicletype=03";
    public static final String REFER = "https://enterbj.zhongchebaolian.com/enterbj/platform/enterbj/toVehicleType";
//    appsource=bjjj&hiddentime=2018-01-03+16%3A25%3A00&applyid=039201801010212503428269&userid=0E0CDBD816C846A9A9BF71AA57C94851&applystatus=&carid=19422944&gpslon=116.471818&gpslat=40.026413&imei=82e22736-ce3f-3a03-a790-56c0634abd40&imsi=460025013937347&cartype=02&licenseno=%E8%B1%ABUK9852&vehicletype=03
    /**
     * 获取进京证在线申请页
     * 
     * @param userId 用户id
     * @param carid 汽车id
     * @param licenseno 车牌号URLEcode编码
     * @param cartype
     * @param vehicletype 03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车
     * @return
     */
    private String run(String userid,String carid,String licenseno,String cartype,String vehicletype) {
        String appsource = "bjjj";//TODO bjjj or jjzx?
        String hiddentime = "2017-12-27+20%3A36%3A30";
        String applyid = "";
        String gpslon = "4.9E-324";
        String gpslat = "4.9E-324";
        String imei = "526bdde9-3bda-314d-9743-305c70f92451";
        String imsi = "";
        String applystatus = "";
        
        String params = "appsource="+appsource+"&hiddentime="+hiddentime+"&applyid="+applyid+"&userid="+userid+"&applystatus="+applystatus+"&carid="+carid+"&gpslon="+gpslon+"&gpslat="+gpslat+"&imei="+imei+"&imsi="+imsi+"&cartype="+cartype+"&licenseno="+licenseno+"&vehicletype="+vehicletype;
        String result = HttpUtil.sendHtpps2(URL, params, REFER);
        System.out.println("result:" + result);
        return result;
    }
    
    public String[] getCarInfo(String userid,String carid,String licenseno,String cartype,String vehicletype) {
        String[] result = new String[2];
        String response = run(userid ,carid ,licenseno ,cartype ,vehicletype);
        if(response != null && !response.equalsIgnoreCase("")) {
            Document doc = Jsoup.parse(response);
            Elements inputs = doc.select("input#carmodel");
            if(inputs.size() > 0) {
                result[0] = inputs.get(0).attr("value");
            }
            inputs = doc.select("input#carregtime");
            if(inputs.size() > 0) {
                result[1] = inputs.get(0).attr("value");
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
//        run("0E0CDBD816C846A9A9BF71AA57C94851","19422944","%E8%B1%ABUK9852","02","03");//zhc
//        run("d131c66355654e9b83bdc4f0db55c01d","254698","%e8%b1%abAL2Z53","02","03");//lk
        String[] carinfo = new A32_AddCarType_Pager().getCarInfo("d131c66355654e9b83bdc4f0db55c01d","254698","%e8%b1%abAL2Z53","02","03");//lk
        System.out.println("carmodel:"+carinfo[0] +" | carregtime:"+carinfo[1]);
    }

}
