package com.fbb.jjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fbb.jjz.bean.Account;

/**
 * 
 * @author fengbb
 *
 */
public class AccountDao {
    private static final String TABLE_NAME = "bjjj_account";
    private final String sql_add = "insert into " + TABLE_NAME + " values (?,?,?,?,?,now(),now())";
    private final String sql_del = "delete from " + TABLE_NAME + " where userid = ?";
    private final String sql_query = "select * from " + TABLE_NAME + " where userid = ?";
    private final String sql_query_all = "select * from " + TABLE_NAME;
    

    public Account queryById(String userid) {
        Account result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query);
            pstmt.setString(1, userid);
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
    
    public ArrayList<Account> queryAll() {
        ArrayList<Account> result = null;
        Connection conn = DBHelper.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql_query_all);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if(result == null){
                    result = new ArrayList<Account>();
                }
                Account bean = fromResultset(resultSet);
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
    
    private Account fromResultset(ResultSet resultSet) {
        Account account = new Account();
        try {
            account.setUserid(resultSet.getString("userid"));
            account.setPhone(resultSet.getString("phone"));
            account.setName(resultSet.getString("name"));
            account.setHeadurl(resultSet.getString("headurl"));
            account.setSex(resultSet.getString("sex"));
            account.setCreate_time(resultSet.getDate("create_time"));
            account.setUpdate_time(resultSet.getDate("update_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean add(Account bean) throws SQLException {
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
    
    

    private boolean addBean(Connection conn, Account bean) throws SQLException {
        boolean result = false;
        if (bean != null) {
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(sql_add);
                pstmt.setString(1, bean.getUserid());
                pstmt.setString(2, bean.getPhone());
                pstmt.setString(3, bean.getName());
                pstmt.setString(4, bean.getSex());
                pstmt.setString(5, bean.getHeadurl());
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
        Account account = new Account();
        account.setUserid("0719afcd79e847c8abfb4c6d0cf8b02b");
        account.setPhone("18701501627");
        try {
            new AccountDao().add(account);
//            new AccountDao().del(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
