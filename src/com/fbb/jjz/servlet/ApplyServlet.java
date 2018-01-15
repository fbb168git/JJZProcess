package com.fbb.jjz.servlet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fbb.jjz.bean.Account;
import com.fbb.jjz.bean.ApplyParams;
import com.fbb.jjz.bean.Car;
import com.fbb.jjz.bean.Device;
import com.fbb.jjz.bean.EnterPerson;
import com.fbb.jjz.bean.ResultLogin;
import com.fbb.jjz.bean.ResultPhoneCode;
import com.fbb.jjz.dao.AccountDao;
import com.fbb.jjz.dao.CarDao;
import com.fbb.jjz.dao.DeviceDao;
import com.fbb.jjz.dao.EnterPersonDao;
import com.fbb.jjz.helper.ApplyInfoHelper;
import com.fbb.jjz.helper.LoginHelper;
import com.fbb.jjz.net.BaseResp;
import com.fbb.jjz.servlet.base.BaseServlet;
import com.fbb.jjz.util.JsonUtil;
import com.fbb.jjz.util.TextUtil;

public class ApplyServlet extends BaseServlet {
    private static Logger logger = LoggerFactory.getLogger(ApplyServlet.class);

    @Override
    protected String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String type = request.getParameter("type");
        if (type == null || type.equalsIgnoreCase("")) {
            BaseResp phoneCodeResult = new BaseResp();
            phoneCodeResult.rescode = "80001";
            phoneCodeResult.resdes = "参数不完整";
            return JsonUtil.toJson(phoneCodeResult);
        }
        String result = null;
        if (type.equalsIgnoreCase("0")) {// query
            result = processQuery(request);
        } else if (type.equalsIgnoreCase("1")) {// commit
            result = processCommit(request);
        } else if (type.equalsIgnoreCase("2")) {// apply

        } else {

        }
        return result;
    }

    private String processCommit(HttpServletRequest request) {
        String userid = request.getParameter("userid");
        String data = request.getParameter("data");
        
        return null;
    }

    private String processQuery(HttpServletRequest request) throws SQLException {
        String userid = request.getParameter("userid");
        ArrayList<ApplyParams> result = new ArrayList<ApplyParams>();
        CarDao carDao = new CarDao();
        EnterPersonDao personDao = new EnterPersonDao();
        
        ArrayList<Car> carlist = carDao.queryByUserid(userid);
        if(carlist != null && carlist.size() > 0) {
            for(Car car : carlist) {
                EnterPerson enterPerson = personDao.queryByCarid(car.carid);
                ApplyParams params = new ApplyParams();
                params.fillCarInfo(car);
                params.fillPersonInfo(enterPerson);
                params.userid = userid;
                result.add(params);
            }
        }
        String responseText = JsonUtil.toJson(result);
        return responseText;
    }

}
