package com.fbb.jjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fbb.jjz.bean.Account;
import com.fbb.jjz.bean.ApplyCarInfo;
import com.fbb.jjz.bean.ApplyStatus;
import com.fbb.jjz.bean.Car;

/**
 * 
 * @author fengbb
 *
 */
public class ApplyStatusDao {
    private static final String TABLE_NAME = "apply_status";
    private final String sql_add_or_upate = "replace into " + TABLE_NAME + " values (?,?,?,?,?,?,now(),now())";
    private final String sql_del = "delete from " + TABLE_NAME + " where carid = ?";
    private final String sql_query_all = "select * from " + TABLE_NAME;
    private final String sql_query_carid = "select * from " + TABLE_NAME + " where carid = ?";
    private final String sql_query_userid = "select * from " + TABLE_NAME + " where userid = ?";
    private final String sql_query_status = "select * from " + TABLE_NAME + " where apply_status = ?";
    
    
    public ApplyStatus queryByCarid(String carid) {
        ApplyStatus result = null;
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
    
    public ArrayList<ApplyStatus> queryByStatus(String apply_status) {
        ArrayList<ApplyStatus> result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query_status);
            pstmt.setString(1, apply_status);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if(result == null){
                    result = new ArrayList<ApplyStatus>();
                }
                ApplyStatus bean = fromResultset(resultSet);
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
    
    public ArrayList<ApplyStatus> queryByUserid(String userid) {
        ArrayList<ApplyStatus> result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query_userid);
            pstmt.setString(1, userid);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if(result == null){
                    result = new ArrayList<ApplyStatus>();
                }
                ApplyStatus bean = fromResultset(resultSet);
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
    
    public ArrayList<ApplyStatus> queryAll() {
        ArrayList<ApplyStatus> result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query_all);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if(result == null){
                    result = new ArrayList<ApplyStatus>();
                }
                ApplyStatus bean = fromResultset(resultSet);
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
    
    private ApplyStatus fromResultset(ResultSet resultSet) {
        ApplyStatus bean = new ApplyStatus();
        try {
            bean.userid = resultSet.getString("userid");
            bean.carid = resultSet.getString("carid");
            bean.apply_status = resultSet.getString("apply_status");
            bean.enterbjstart = resultSet.getDate("enterbjstart");
            bean.enterbjend = resultSet.getDate("enterbjend");
            bean.apply_time = resultSet.getDate("apply_time");
            bean.update_time = resultSet.getDate("update_time");
            bean.create_time = resultSet.getDate("create_time");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
    
    public boolean addOrUpdate(ApplyStatus bean) throws SQLException {
        Connection conn = DBHelper.getConnection();
        boolean success = addBean(conn, bean);
        DBHelper.close(conn);
        return success;
    }

    private boolean addBean(Connection conn, ApplyStatus bean) throws SQLException {
        boolean result = false;
        if (bean != null) {
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(sql_add_or_upate);
                pstmt.setString(1, bean.userid);
                pstmt.setString(2, bean.carid);
                pstmt.setString(3, bean.apply_status);
                pstmt.setDate(4, new java.sql.Date(bean.enterbjstart.getTime()));
                pstmt.setDate(5, new java.sql.Date(bean.enterbjend.getTime()));
                pstmt.setDate(6, new java.sql.Date(bean.apply_time.getTime()));
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
