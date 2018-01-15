package com.fbb.jjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fbb.jjz.bean.Account;
import com.fbb.jjz.bean.ApplyCarInfo;
import com.fbb.jjz.bean.Car;

/**
 * 
 * @author fengbb
 *
 */
public class CarDao {
    private static final String TABLE_NAME = "car";
    private final String sql_add_or_upate = "replace into " + TABLE_NAME + " values (?,?,?,?,?,?,?,?,?,now(),now())";
    private final String sql_del = "delete from " + TABLE_NAME + " where carid = ?";
    private final String sql_query = "select * from " + TABLE_NAME + " where carid = ?";
    private final String sql_query_userid = "select * from " + TABLE_NAME + " where userid = ?";
    
    
    public Car queryByCarid(String carid) {
        Car result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query);
            pstmt.setString(1, carid);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                result = fromResultset(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.close(resultSet);
            DBHelper.close(pstmt);
            DBHelper.close(conn);
        }
        return result;
    }
    
    public ArrayList<Car> queryByUserid(String userid) {
        ArrayList<Car> result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query_userid);
            pstmt.setString(1, userid);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if(result == null){
                    result = new ArrayList<Car>();
                }
                Car bean = fromResultset(resultSet);
                result.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.close(resultSet);
            DBHelper.close(pstmt);
            DBHelper.close(conn);
        }
        return result;
    }
    
    private Car fromResultset(ResultSet resultSet) {
        Car car = new Car();
        try {
            car.setUserid(resultSet.getString("userid"));
            car.setCarid(resultSet.getString("carid"));
            car.setLicenseno(resultSet.getString("licenseno"));
            car.setEngineno(resultSet.getString("engineno"));
            car.setCarmodel(resultSet.getString("carmodel"));
            car.setCarregtime(resultSet.getString("carregtime"));
            car.setVehicletype(resultSet.getString("vehicletype"));
            car.setCartype(resultSet.getString("cartype"));
            car.env_grade = resultSet.getString("env_grade");
            car.setCreate_time(resultSet.getDate("create_time"));
            car.setUpdate_time(resultSet.getDate("update_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }
    
    public boolean addOrUpdate(Car bean) throws SQLException {
        Connection conn = DBHelper.getConnection();
        boolean success = addBean(conn, bean);
        DBHelper.close(conn);
        return success;
    }

    public boolean del(Car bean) throws SQLException  {
        if(bean != null && bean.getUserid() != null && !bean.getUserid().equalsIgnoreCase("")) {
            Connection conn= null;
            PreparedStatement pstmt = null;
            try {
                conn = DBHelper.getConnection();
                pstmt = conn.prepareStatement(sql_del);
                pstmt.setString(1, bean.getUserid());
                int executeUpdate = pstmt.executeUpdate();
                return executeUpdate > 0 ? true : false;
            } catch (SQLException e) {
                throw e;
            } finally {
                DBHelper.close(pstmt);
                DBHelper.close(conn);
            }
        }
        return false;
    }
    
    

    private boolean addBean(Connection conn, Car bean) throws SQLException {
        boolean result = false;
        if (bean != null) {
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(sql_add_or_upate);
                pstmt.setString(1, bean.getCarid());
                pstmt.setString(2, bean.getUserid());
                pstmt.setString(3, bean.getLicenseno());
                pstmt.setString(4, bean.getEngineno());
                pstmt.setString(5, bean.getCarmodel());
                pstmt.setString(6, bean.getCarregtime());
                pstmt.setString(7, bean.getVehicletype());
                pstmt.setString(8, bean.getCartype());
                pstmt.setString(9, bean.env_grade);
                int executeUpdate = pstmt.executeUpdate();
                return executeUpdate > 0 ? true : false;
            } catch (SQLException e) {
                throw e;
            } finally {
                DBHelper.close(resultSet);
                DBHelper.close(pstmt);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Car bean = new Car();
        bean.setUserid("0719afcd79e847c8abfb4c6d0cf8b02b");
        //enterCarlist接口获取
        bean.setCarid("643359");
        bean.setLicenseno("豫HFB768");
        bean.setCartype("02");
        bean.setEngineno("Q042607");
        //TODO接口取
        bean.setCarmodel("GTM7150AE");
        bean.setCarregtime("2015-02-15");
        bean.setVehicletype("03");
        
//        ApplyCarInfo carInfo = new ApplyCarInfo("GTM7150AE", "2015-02-15", "Q042607", "03", "冯贝贝", "410882198710125512");
        try {
            new CarDao().addOrUpdate(bean);
//            new AccountDao().del(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
