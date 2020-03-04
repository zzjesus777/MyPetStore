package org.csu.mypetstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewAccountFormServlet extends HttpServlet {
    private static final String VIEW_NEW_FORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> languages = new ArrayList<String>();
        languages.add("english");
        languages.add("japanese");

        List<String> categories = new ArrayList<String>();
        categories.add("FISH");
        categories.add("DOGS");
        categories.add("REPTILES");
        categories.add("CATS");
        categories.add("BIRDS");
        request.setAttribute("languages",languages);
        request.setAttribute("categories",categories);
        request.getRequestDispatcher(VIEW_NEW_FORM).forward(request,response);
    }
}

