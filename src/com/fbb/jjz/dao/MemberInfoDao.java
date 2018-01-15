package com.fbb.jjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fbb.jjz.bean.EnterPerson;
import com.fbb.jjz.bean.MemberInfo;

/**
 * 
 * @author fengbb
 *
 */
public class MemberInfoDao {
    private static final String TABLE_NAME = "member_info";
    private final String sql_add = "insert into " + TABLE_NAME + " values (?,?,?,?,now(),now())";
    private final String sql_del = "delete from " + TABLE_NAME + " where userid = ?";

    public boolean add(MemberInfo bean) throws SQLException {
        Connection conn = DBHelper.getConnection();
        boolean success = addBean(conn, bean);
        DBHelper.close(conn);
        return success;
    }

    public boolean del(MemberInfo bean) throws SQLException {
        if (bean != null && bean.getMemberid() != null && !bean.getMemberid().equalsIgnoreCase("")) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = DBHelper.getConnection();
                pstmt = conn.prepareStatement(sql_del);
                pstmt.setString(1, bean.getMemberid());
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



    private boolean addBean(Connection conn, MemberInfo bean) throws SQLException {
        boolean result = false;
        if (bean != null) {
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(sql_add);
                pstmt.setString(1, bean.getMemberid());
                pstmt.setString(2, bean.getMember_type());
                pstmt.setDate(3, new java.sql.Date(bean.getStart_date().getTime()));
                pstmt.setDate(4, new java.sql.Date(bean.getEnd_date().getTime()));
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
        MemberInfo bean = new MemberInfo();
        bean.setMemberid("0719afcd79e847c8abfb4c6d0cf8b02b");
        bean.setMember_type(MemberInfo.MEMBER_TYPE_NORMAL);
        try {
            bean.setStart_date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01"));
            bean.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01"));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        // ApplyCarInfo carInfo = new ApplyCarInfo("GTM7150AE", "2015-02-15", "Q042607", "03",
        // "冯贝贝", "410882198710125512");
        try {
            new MemberInfoDao().add(bean);
            // new AccountDao().del(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
