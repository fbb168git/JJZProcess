package com.fbb.jjz.task;

import java.util.ArrayList;

import com.fbb.jjz.bean.ApplyEnterCar;
import com.fbb.jjz.bean.ApplyEnterCar.ApplyInfo;
import com.fbb.jjz.bean.ApplyParams;
import com.fbb.jjz.test.A1_EnterCarList;
import com.fbb.jjz.test.A32_AddCarType_Pager;
import com.fbb.jjz.test.A42_ApplyBjMessage_Pager;
import com.fbb.jjz.test.A4_CheckEnvGrade;

public class ApplyInfoHelper {
    
    public ArrayList<ApplyParams> getApplyParamsInfo(String userid) {
        ArrayList<ApplyEnterCar> enterCarList = new A1_EnterCarList().getEnterCarList(userid);
        if(enterCarList != null && enterCarList.size() > 0) {
            
            A32_AddCarType_Pager task2 = new A32_AddCarType_Pager();
            A4_CheckEnvGrade task3 = new A4_CheckEnvGrade();
            A42_ApplyBjMessage_Pager task4 = new A42_ApplyBjMessage_Pager();
            
            ArrayList<ApplyParams> result = new ArrayList<ApplyParams>();
            for(ApplyEnterCar car : enterCarList) {
                ApplyParams params = new ApplyParams();
                result.add(params);
//                params.vehicletype = "03";//TODO暂时只支持小型客车
                params.userid = userid;
                params.carid = car.getCarid();
                params.licenseno = car.getLicenseno();
                params.cartypecode = car.getCartype();
                params.applyflag = car.getApplyflag();
                ArrayList<ApplyInfo> carapplyarr = car.getCarapplyarr();
                if(carapplyarr != null && carapplyarr.size() > 0) {
                    params.engineno =  carapplyarr.get(0).engineno;
                    params.applyid = carapplyarr.get(0).applyid;
                }
                String[] carInfo = task2.getCarInfo(userid, params.carid, params.licenseno, params.cartypecode, params.vehicletype);
                params.carmodel = carInfo[0];
                params.carregtime = carInfo[1];
                params.envGrade = task3.checkEnvGrade(userid, car.getCarid(), car.getLicenseno(), params.carmodel, params.carregtime);
                if(params.applyid != null && !params.applyid.equalsIgnoreCase("")) {
                    String[] enterPersonInfo = task4.getEnterPersonInfo(userid, params.carid, params.applyid, params.licenseno, params.vehicletype, params.envGrade, params.carmodel, params.carregtime);
                    params.drivername = enterPersonInfo[0];
                    params.driverlicenseno = enterPersonInfo[1];
                } else {
                    String carEngineNumber = task4.getCarEngineNumber(userid, params.carid, params.applyid, params.licenseno, params.vehicletype, params.envGrade, params.carmodel, params.carregtime);
                    params.engineno =  carEngineNumber;
                }
                System.out.println(params);
            }
            return result;
        }
        return null;
    }
    
    public static void main(String[] args) {
//       new GerUserInfo().getApplyParamsInfo("0719afcd79e847c8abfb4c6d0cf8b02b");//fbb
       new ApplyInfoHelper().getApplyParamsInfo("0E0CDBD816C846A9A9BF71AA57C94851");//zhc
//       new ApplyInfoHelper().getApplyParamsInfo("d131c66355654e9b83bdc4f0db55c01d");//lk
//       new ApplyInfoHelper().getApplyParamsInfo("F867CCD9C7DE499681AD24BD9491648A");//ljw
    }

}
