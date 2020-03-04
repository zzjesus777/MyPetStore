package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class NewOrderServlet extends HttpServlet {
    private String creditCard;
    private String expiryDate;
    private String cardType;
    private String billToFirstName;
    private String billToLastName;
    private String billAddress1;
    private String billAddress2;
    private String billCity;
    private String billState;
    private String billZip;
    private String billCountry;
    private boolean is_change_ship = false;
    private String[] change_ships;
    private Order order;
    private Orderstatus orderstatus;
    private static final String VIEW_CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String VIEW_SHIP_FORM = "/WEB-INF/jsp/order/ShippingForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        creditCard = request.getParameter("order.creditCard");
        expiryDate = request.getParameter("order.expiryDate");
        cardType = request.getParameter("order.cardType");
        billToFirstName = request.getParameter("order.billToFirstName");
        billToLastName = request.getParameter("order.billToLastName");
        billAddress1 = request.getParameter("order.billAddress1");
        billAddress2 = request.getParameter("order.billAddress2");
        billCity = request.getParameter("order.billCity");
        billState = request.getParameter("order.billState");
        billZip = request.getParameter("order.billZip");
        billCountry = request.getParameter("order.billCountry");
        change_ships = request.getParameterValues("shippingAddressRequired");

        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account") ;
        Cart cart = (Cart)session.getAttribute("cart");
        orderstatus = new Orderstatus();
        order = new Order();
        order.setOrderstatus(orderstatus);
        order.initOrder(account,cart);
        order.setCreditCard(creditCard);
        order.setExpiryDate(expiryDate);
        order.setCardType(cardType);
        order.setBillToFirstName(billToFirstName);
        order.setBillToLastName(billToLastName);
        order.setBillAddress1(billAddress1);
        order.setBillAddress2(billAddress2);
        order.setBillCity(billCity);
        order.setBillState(billState);
        order.setBillZip(billZip);
        order.setBillCountry(billCountry);


        if (change_ships != null)
            is_change_ship = true;
        if(is_change_ship){
            session.setAttribute("order",order);
            request.getRequestDispatcher(VIEW_SHIP_FORM).forward(request,response);
        }
        else {
            order.setShipToFirstName(account.getFirstName());
            order.setShipToLastName(account.getLastName());
            order.setShipAddress1(account.getAddress1());
            order.setShipAddress2(account.getAddress2());
            order.setShipCity(account.getCity());
            order.setShipState(account.getState());
            order.setShipZip(account.getZip());
            order.setShipCountry(account.getCountry());

            session.setAttribute("order",order);
            request.getRequestDispatcher(VIEW_CONFIRM_ORDER).forward(request,response);
        }
    }
}
