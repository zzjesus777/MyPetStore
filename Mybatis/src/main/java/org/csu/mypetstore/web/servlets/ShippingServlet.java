package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class ShippingServlet extends HttpServlet {

    private String shipToFirstName;
    private String shipToLastName;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;
    private Order order;
    private static final String VIEW_CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        shipToFirstName = request.getParameter("order.shipToFirstName");
        shipToLastName = request.getParameter("order.shipToLastName");
        shipAddress1 = request.getParameter("order.shipAddress1");
        shipAddress2 = request.getParameter("order.shipAddress2");
        shipCity = request.getParameter("order.shipCity");
        shipState = request.getParameter("order.shipState");
        shipZip = request.getParameter("order.shipZip");
        shipCountry = request.getParameter("order.shipCountry");

        HttpSession session = request.getSession();

        order = (Order) session.getAttribute("order");
        order.setShipToFirstName(shipToFirstName);
        order.setShipToLastName(shipToLastName);
        order.setShipAddress1(shipAddress1);
        order.setShipAddress2(shipAddress2);
        order.setShipCity(shipCity);
        order.setShipState(shipState);
        order.setShipZip(shipZip);
        order.setShipCountry(shipCountry);

        session.setAttribute("order",order);
        request.getRequestDispatcher(VIEW_CONFIRM_ORDER).forward(request,response);
    }
}
