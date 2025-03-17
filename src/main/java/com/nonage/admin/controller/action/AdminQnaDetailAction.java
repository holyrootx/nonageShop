package com.nonage.admin.controller.action;

import com.nonage.admin.controller.dao.AdminQnaDAO;
import com.nonage.admin.controller.dto.AdminQnaVO;
import com.nonage.admin.controller.dto.AdminVO;
import com.nonage.controller.action.Action;
import com.nonage.dao.QnaDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminQnaDetailAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "admin/qna/admin_qna_detail.jsp";

        HttpSession session = req.getSession();
        AdminVO adminLoginUser = (AdminVO) session.getAttribute("adminLoginUser");

        if(adminLoginUser == null){
            url = "NonageServlet?command=admin_login_form";
        } else{
            int qseq = Integer.parseInt(req.getParameter("qseq"));
            AdminQnaDAO adminQnaDAO = AdminQnaDAO.getInstance();
            AdminQnaVO adminQnaVO = adminQnaDAO.getAdminQnaByQseq(qseq);
            req.setAttribute("adminQnaVO",adminQnaVO);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);

    }
}
