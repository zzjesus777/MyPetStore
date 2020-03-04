package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignonServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String VIEW_SIGNON_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    private AccountService accountService;
    private String username;
    private String password;
    private String massage;
    private String checkcode;
    private String verificationCode = " ";
    private  Boolean authenticated = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        checkcode = request.getParameter("check_code").toLowerCase();
        HttpSession session = request.getSession();
        verificationCode = ((String) session.getAttribute("checkCode_inSession")).toLowerCase();
        session.removeAttribute("checkCode_inSession");

        accountService = new AccountService();
        Account account = accountService.getAccount(username, password);
        if (account == null) {
            massage = "Invalid username or password. Signon failed.";
            request.setAttribute("massage", massage);
            request.getRequestDispatcher(VIEW_SIGNON_FORM).forward(request, response);
        }
        else if(!checkcode.equals(verificationCode)){
            massage = "Invalid verificationCode. Signon failed.";
            request.setAttribute("massage", massage);
            request.getRequestDispatcher(VIEW_SIGNON_FORM).forward(request, response);
        }
        else {
            session.setAttribute("account", account);
            if(account.getUsername()!=null)
                authenticated = true;
            session.setAttribute("authenticated",authenticated);
            request.getRequestDispatcher(MAIN).forward(request, response);
        }
    }
}
