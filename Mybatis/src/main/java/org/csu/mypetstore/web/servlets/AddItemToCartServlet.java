package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    private static final String VIEW_CART= "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String VIEW_SIGNON_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    private String workingItemId;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
            if (cart.containsItemId(workingItemId)) {
                cart.incrementQuantityByItemId(workingItemId);
            } else {
                CatalogService service = new CatalogService();
                boolean isInStork = service.isItemInStock(workingItemId);
                Item item = service.getItem(workingItemId);
                cart.addItem(item, isInStork);
            }
            session.setAttribute("cart", cart);
            request.getRequestDispatcher(VIEW_CART).forward(request, response);

        }
        else {
            request.getRequestDispatcher(VIEW_SIGNON_FORM).forward(request, response);
        }
    }
}
