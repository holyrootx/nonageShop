package com.nonage.admin.controller.action;

import com.google.gson.Gson;
import com.nonage.admin.controller.dao.AdminProductDAO;
import com.nonage.admin.controller.dto.AdminProductVO;
import com.nonage.admin.controller.dto.AdminVO;
import com.nonage.controller.action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AdminProductSearchAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "admin/product/adminProductList.jsp";

        String pname = req.getParameter("pname");

        HttpSession session = req.getSession();
        AdminVO adminLoginUser = (AdminVO)session.getAttribute("adminLoginUser");
        System.out.println(pname);
        if(adminLoginUser == null){
            url = "NonageServlet?command=admin_login_form";
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req,resp);
        }

        AdminProductDAO adminProductDAO = AdminProductDAO.getInstance();
        ArrayList<AdminProductVO> adminProductList = new ArrayList<>();
        adminProductList = adminProductDAO.searchAdminProductByPName(pname);
        System.out.println("(AdminProductSearchAction에서 알림) adminProductList.size() : " + adminProductList.size());
        req.setAttribute("adminProductList",adminProductList);



        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);





    }
}
