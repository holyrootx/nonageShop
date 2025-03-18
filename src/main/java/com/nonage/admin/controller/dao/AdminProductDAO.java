package com.nonage.admin.controller.dao;

import com.nonage.admin.controller.dto.AdminProductVO;
import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminProductDAO {

    private AdminProductDAO() {}

    private static AdminProductDAO instance = new AdminProductDAO();

    public static AdminProductDAO getInstance() {
        return instance;
    }

    // 상품 목록 조회
    public ArrayList<AdminProductVO> listProduct() {
        ArrayList<AdminProductVO> productList = new ArrayList<>();
        String sql = "SELECT pseq, name, price1, price2, indate, useyn FROM product";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AdminProductVO adminProductVO = new AdminProductVO();
                adminProductVO.setPseq(rs.getInt("pseq"));
                adminProductVO.setName(rs.getString("name"));
                adminProductVO.setPrice1(rs.getInt("price1"));
                adminProductVO.setPrice2(rs.getInt("price2"));
                adminProductVO.setIndate(rs.getTimestamp("indate"));
                adminProductVO.setUseyn(rs.getString("useyn"));

                // 상품분류 설정
                adminProductVO.setKind(getCategoryByPseq(adminProductVO.getPseq()));

                productList.add(adminProductVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return productList;
    }

    // 특정 상품 정보 가져오기
    public AdminProductVO getProduct(String pseq) {
        AdminProductVO adminProductVO = null;
        String sql = "SELECT * FROM product WHERE pseq = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pseq);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                adminProductVO = new AdminProductVO();
                adminProductVO.setPseq(rs.getInt("pseq"));
                adminProductVO.setName(rs.getString("name"));
                adminProductVO.setPrice1(rs.getInt("price1"));
                adminProductVO.setPrice2(rs.getInt("price2"));
                adminProductVO.setPrice3(rs.getInt("price3"));
                adminProductVO.setContent(rs.getString("content"));
                adminProductVO.setImage(rs.getString("image"));
                adminProductVO.setUseyn(rs.getString("useyn"));
                adminProductVO.setBestyn(rs.getString("bestyn"));
                adminProductVO.setIndate(rs.getTimestamp("indate"));

                // 상품분류 설정
                adminProductVO.setKind(getCategoryByPseq(adminProductVO.getPseq()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return adminProductVO;
    }

    // 상품 업데이트 메서드 추가
    public void updateProduct(AdminProductVO product) {
        String sql = "UPDATE product SET name=?, price1=?, price2=?, price3=?, content=?, image=?, useyn=?, bestyn=? WHERE pseq=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice1());
            pstmt.setInt(3, product.getPrice2());
            pstmt.setInt(4, product.getPrice3());
            pstmt.setString(5, product.getContent());
            pstmt.setString(6, product.getImage());
            pstmt.setString(7, product.getUseyn());
            pstmt.setString(8, product.getBestyn());
            pstmt.setInt(9, product.getPseq());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
    }

    // pseq에 따른 상품 분류 반환
    private String getCategoryByPseq(int pseq) {
        switch (pseq) {
            case 1: case 2: case 6: return "Boots";
            case 3: case 5: return "Heels";
            case 4: case 7: case 8: case 10: return "Sandals";
            case 9: case 11: return "Sneakers";
            default: return "Unknown";
        }
    }
    public ArrayList<AdminProductVO> searchAdminProductByPName(String pname){
        ArrayList<AdminProductVO> adminProductList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name LIKE '%'||?||'%' AND useyn IS NOT NULL";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pname.trim());
            rs = pstmt.executeQuery();

            while(rs.next()) {
                AdminProductVO adminProductVO = new AdminProductVO();
                adminProductVO.setPseq(rs.getInt("pseq"));
                adminProductVO.setName(rs.getString("name"));
                adminProductVO.setPrice1(rs.getInt("price1"));
                adminProductVO.setPrice2(rs.getInt("price2"));
                adminProductVO.setPrice3(rs.getInt("price3"));
                adminProductVO.setContent(rs.getString("content"));
                adminProductVO.setImage(rs.getString("image"));
                adminProductVO.setUseyn(rs.getString("useyn"));
                adminProductVO.setBestyn(rs.getString("bestyn"));
                adminProductVO.setIndate(rs.getTimestamp("indate"));

                // 상품분류 설정
                // adminProductVO.setKind(getCategoryByPseq(adminProductVO.getPseq()));
                adminProductList.add(adminProductVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return adminProductList;
    }
}