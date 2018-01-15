package com.fbb.jjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fbb.jjz.bean.Car;
import com.fbb.jjz.bean.EnterPerson;

/**
 * 
 * @author fengbb
 *
 */
public class EnterPersonDao {
    private static final String TABLE_NAME = "enter_person";
    private final String sql_add_or_update = "replace into " + TABLE_NAME + " values (?,?,?,?,?,?,?,?,now(),now())";
    private final String sql_del = "delete from " + TABLE_NAME + " where userid = ?";
    private final String sql_query = "select *  from " + TABLE_NAME + " where driverlicenseno = ?";
    private final String sql_query_carid = "select *  from " + TABLE_NAME + " where carid = ?";

    public EnterPerson queryByLicenseno(String driverlicenseno) {
        EnterPerson result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query);
            pstmt.setString(1, driverlicenseno);
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
    
    public EnterPerson queryByCarid(String carid) {
        EnterPerson result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query_carid);
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
    
    private EnterPerson fromResultset(ResultSet resultSet) {
        EnterPerson bean = new EnterPerson();
        try {
            
            bean.setUserid(resultSet.getString("userid"));
            bean.setCarid(resultSet.getString("carid"));
            
            
            bean.setDriverlicenseno(resultSet.getString("driverlicenseno"));
            bean.setDrivername(resultSet.getString("drivername"));
            
            bean.setDrivingphoto(resultSet.getString("drivingphoto"));
            bean.setCarphoto(resultSet.getString("carphoto"));
            bean.setDriverphoto(resultSet.getString("driverphoto"));
            bean.setPersonphoto(resultSet.getString("personphoto"));
            
            bean.setCreate_time(resultSet.getDate("create_time"));
            bean.setUpdate_time(resultSet.getDate("update_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
    
    public boolean addOrUpdate(EnterPerson bean) throws SQLException {
        Connection conn = DBHelper.getConnection();
        boolean success = addBean(conn, bean);
        DBHelper.close(conn);
        return success;
    }

    public boolean del(EnterPerson bean) throws SQLException {
        if (bean != null && bean.getUserid() != null && !bean.getUserid().equalsIgnoreCase("")) {
            Connection conn = null;
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



    private boolean addBean(Connection conn, EnterPerson bean) throws SQLException {
        boolean result = false;
        if (bean != null) {
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(sql_add_or_update);
                pstmt.setString(1, bean.getUserid());
                pstmt.setString(2, bean.getCarid());
                pstmt.setString(3, bean.getDriverlicenseno());
                pstmt.setString(4, bean.getDrivername());
                pstmt.setString(5, bean.getDrivingphoto());
                pstmt.setString(6, bean.getCarphoto());
                pstmt.setString(7, bean.getDriverphoto());
                pstmt.setString(8, bean.getPersonphoto());
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
        EnterPerson bean = new EnterPerson();
        bean.setUserid("0719afcd79e847c8abfb4c6d0cf8b02b");
        bean.setCarid("643359");
        bean.setDriverlicenseno("410882198710125512");
        bean.setDrivername("冯贝贝");

        // ApplyCarInfo carInfo = new ApplyCarInfo("GTM7150AE", "2015-02-15", "Q042607", "03",
        // "冯贝贝", "410882198710125512");
        try {
            new EnterPersonDao().addOrUpdate(bean);
            // new AccountDao().del(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
