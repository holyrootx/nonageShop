package com.nonage.admin.controller.action;

import com.nonage.admin.controller.dao.AdminDAO;
import com.nonage.admin.controller.dto.AdminVO;
import com.nonage.controller.action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLoginAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url ="admin/worker/login_fail.jsp";

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        AdminDAO adminDAO = AdminDAO.getInstance();
        AdminVO result = adminDAO.confirmAuth(id,pwd);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
        if (result.getId() == null || result.getId().length() == 0){
            resp.sendRedirect("admin/worker/login_fail.jsp");
            return;
        } else{
            AdminVO adminLoginUser = new AdminVO();
            adminLoginUser = adminDAO.getAdminById(id);

            HttpSession session = req.getSession();
            session.setAttribute("adminLoginUser",adminLoginUser);

            url = "NonageServlet?command=admin_product_list";
            // url = "admin/worker/loginTest.jsp";
            // 테스트 완료
            requestDispatcher = req.getRequestDispatcher(url);
            requestDispatcher.forward(req,resp);
        }



    }
}
