package com.nonage.admin.controller.dao;

import com.nonage.admin.controller.dto.AdminQnaVO;
import com.nonage.dto.QnaVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminQnaDAO {
    private AdminQnaDAO() {
    }

    private static AdminQnaDAO instance = new AdminQnaDAO();

    public static AdminQnaDAO getInstance() {return  instance; }

    public ArrayList<AdminQnaVO> getAllOfQnaList(){
        ArrayList<AdminQnaVO> adminQnaVOList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT qseq, subject, content, reply, id, rep, indate"
                + " FROM qna "
                + " ORDER BY indate DESC ";
        try{
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                AdminQnaVO adminQnaVO = new AdminQnaVO();
                adminQnaVO.setQseq(rs.getInt("qseq"));
                adminQnaVO.setSubject(rs.getString("subject"));
                adminQnaVO.setContent(rs.getString("content"));
                adminQnaVO.setReply(rs.getString("reply"));
                adminQnaVO.setId(rs.getString("id"));
                adminQnaVO.setRep(rs.getString("rep"));
                adminQnaVO.setIndate(rs.getTimestamp("indate"));
                adminQnaVOList.add(adminQnaVO);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  adminQnaVOList;
    }
    public AdminQnaVO getAdminQnaByQseq(int qseq){
        AdminQnaVO adminQnaVO = new AdminQnaVO();
        String sql = "SELECT qseq, subject, content, reply, id, rep, indate"
                + " FROM qna "
                + " WHERE qseq = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,qseq);
            rs = pstmt.executeQuery();

            if (rs.next()){

                adminQnaVO.setQseq(rs.getInt("qseq"));
                adminQnaVO.setSubject(rs.getString("subject"));
                adminQnaVO.setContent(rs.getString("content"));
                adminQnaVO.setId(rs.getString("id"));
                adminQnaVO.setIndate(rs.getTimestamp("indate"));
                adminQnaVO.setReply(rs.getString("reply"));
                adminQnaVO.setRep(rs.getString("rep"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }


        return adminQnaVO;
    }
    public int updateAdminQnaByQseq(int qseq,String reply){
        AdminQnaVO adminQnaVO = new AdminQnaVO();
        String sql = "UPDATE qna "
                + " SET reply = ?,"
                + " rep = '2'"
                + " WHERE qseq = ? ";
        int result = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,reply);
            pstmt.setInt(2,qseq);
            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }


        return result;
    }
}
