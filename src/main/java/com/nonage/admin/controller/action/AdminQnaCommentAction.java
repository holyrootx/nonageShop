package com.nonage.admin.controller.action;

import com.nonage.admin.controller.dao.AdminQnaDAO;
import com.nonage.admin.controller.dto.AdminQnaVO;
import com.nonage.admin.controller.dto.AdminVO;
import com.nonage.controller.action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminQnaCommentAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "NonageServlet?command=admin_qna_list";

        HttpSession session = req.getSession();
        AdminVO adminLoginUser = (AdminVO) session.getAttribute("adminLoginUser");

        if(adminLoginUser == null){
            url = "NonageServlet?command=admin_login_form";
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req,resp);
        }

        AdminQnaDAO adminQnaDAO = AdminQnaDAO.getInstance();
        Integer qseq = Integer.parseInt(req.getParameter("qseq"));
        String reply = req.getParameter("reply");

        adminQnaDAO.updateAdminQnaByQseq(qseq,reply);



        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);


    }
}
