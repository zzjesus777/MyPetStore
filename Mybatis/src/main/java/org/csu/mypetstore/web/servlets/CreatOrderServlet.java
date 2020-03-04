package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreatOrderServlet extends HttpServlet {
    private Order order;
    private OrderService orderService;
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        orderService = new OrderService();
        orderService.insertOrder(order);

        Cart cart = null;
        session.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
    }
}
