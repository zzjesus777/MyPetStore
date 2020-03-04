package org.csu.mypetstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckCodeCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input_checkcode = request.getParameter("check_code").toLowerCase();
        System.out.println(input_checkcode);
        HttpSession session = request.getSession();
        String real_checkcode = ((String) session.getAttribute("checkCode_inSession")).toLowerCase();
        PrintWriter out = response.getWriter();
        System.out.println(real_checkcode);

        if (input_checkcode.equals(real_checkcode)){
            out.print("yes");
        }else {
            out.print("no");
        }
    }
}
