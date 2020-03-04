package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {

    private static final String VIEW_CART= "/WEB-INF/jsp/cart/Cart.jsp";
    private String updateCartQuantities;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Iterator<CartItem> iterator = cart.getCartItems();
        while (iterator.hasNext()){
            CartItem cartItem = (CartItem)iterator.next();
            Item item = cartItem.getItem();
            updateCartQuantities = request.getParameter(item.getItemId());
            cart.setQuantityByItemId(item.getItemId(),Integer.valueOf(updateCartQuantities).intValue());
        }
        session.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
