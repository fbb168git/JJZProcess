package com.fbb.jjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fbb.jjz.bean.Account;
import com.fbb.jjz.bean.Device;

/**
 * 
 * @author fengbb
 *
 */
public class DeviceDao {
    private static final String TABLE_NAME = "virtual_device";
    private final String sql_add = "insert into " + TABLE_NAME + " values (?,?,?,?,?,?,now(),now())";
    private final String sql_del = "delete from " + TABLE_NAME + " where userid = ?";
    private final String sql_query = "select * from " + TABLE_NAME + " where userid = ?";
    

    public ArrayList<Device> queryByUserid(String userid) {
        ArrayList<Device> result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query);
            pstmt.setString(1, userid);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if(result == null){
                    result = new ArrayList<Device>();
                }
                result.add(fromResultset(resultSet));
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
    
    private Device fromResultset(ResultSet resultSet) {
        Device bean = new Device();
        try {
            bean.setDeviceid(resultSet.getString("deviceid"));
            bean.setUserid(resultSet.getString("userid"));
            bean.setImei(resultSet.getString("imei"));
            bean.setImsi(resultSet.getString("imsi"));
            bean.setGpslon(resultSet.getString("gpslon"));
            bean.setGpslat(resultSet.getString("gpslat"));
            bean.setCreate_time(resultSet.getDate("create_time"));
            bean.setUpdate_time(resultSet.getDate("update_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public boolean add(Device bean) throws SQLException {
        Connection conn = DBHelper.getConnection();
        boolean success = addBean(conn, bean);
        DBHelper.close(conn);
        return success;
    }

    public boolean del(Account bean) throws SQLException  {
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
    
    

    private boolean addBean(Connection conn, Device bean) throws SQLException {
        boolean result = false;
        if (bean != null) {
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(sql_add);
                pstmt.setString(1, bean.getDeviceid());
                pstmt.setString(2, bean.getUserid());
                pstmt.setString(3, bean.getImei());
                pstmt.setString(4, bean.getImsi());
                pstmt.setString(5, bean.getGpslon());
                pstmt.setString(6, bean.getGpslat());
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
    }
}
