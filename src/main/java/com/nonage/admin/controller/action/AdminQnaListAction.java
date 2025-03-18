package com.nonage.admin.controller.action;

import com.nonage.admin.controller.dao.AdminQnaDAO;
import com.nonage.admin.controller.dto.AdminVO;
import com.nonage.controller.action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import com.nonage.admin.controller.dto.AdminQnaVO;
public class AdminQnaListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "admin/qna/admin_qna_list.jsp";

        HttpSession session = req.getSession();
        AdminVO adminLoginUser = (AdminVO)session.getAttribute("adminLoginUser");
        if(adminLoginUser == null){
            url = "NonageServlet?command=admin_login_form";
        } else{
            AdminQnaDAO adminQnaDAO = AdminQnaDAO.getInstance();
            ArrayList<AdminQnaVO> adminQnaList = adminQnaDAO.getAllOfQnaList();
            req.setAttribute("adminQnaList",adminQnaList);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);



    }
}
