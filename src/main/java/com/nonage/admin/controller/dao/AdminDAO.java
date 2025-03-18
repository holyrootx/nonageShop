package com.nonage.admin.controller.dao;

import com.nonage.admin.controller.dto.AdminVO;
import com.nonage.dao.AddressDAO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class AdminDAO {
    private AdminDAO() {
    }

    private static AdminDAO instance = new AdminDAO();

    public static AdminDAO getInstance() {return  instance; }

    public AdminVO confirmAuth(String id, String pwd) {
        String sql = "select * from worker where id =? AND pwd = ?";
        AdminVO adminVO = new AdminVO();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                adminVO.setId(rs.getString("id"));
                adminVO.setPwd(rs.getString("pwd"));
                adminVO.setName(rs.getString("name"));
                adminVO.setPhone(rs.getString("phone"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return adminVO;
    }
    public AdminVO getAdminById(String id){
        String sql = "SELECT * FROM worker WHERE id =?";
        AdminVO adminVO = new AdminVO();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                adminVO.setId(rs.getString("id"));
                adminVO.setPwd(rs.getString("pwd"));
                adminVO.setName(rs.getString("name"));
                adminVO.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return adminVO;
    }
}
