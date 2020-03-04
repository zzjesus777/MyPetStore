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
import java.lang.reflect.Array;
import java.util.Iterator;

public class ChangeNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Iterator<CartItem> iterator = cart.getCartItems();
        String updateCartQuantities;
        String array = request.getParameter("array");
        System.out.println(array);
        String[] number = array.split("a");
        int i = 0;
        while (iterator.hasNext()){
            CartItem cartItem = (CartItem)iterator.next();
            System.out.println("1");
            Item item = cartItem.getItem();
            System.out.println("2");
            updateCartQuantities = number[i];
            i++;
            System.out.println("3="+updateCartQuantities+" "+item.getItemId());
            cart.setQuantityByItemId(item.getItemId(), Integer.valueOf(updateCartQuantities));
            System.out.println("4");
        }
        session.setAttribute("cart",cart);
    }
}
