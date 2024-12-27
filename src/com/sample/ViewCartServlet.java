package com.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>checkout </title></head>");
        out.println("<body>");
        out.println("<h1>Your Cart</h1>");

        if (cart == null || cart.isEmpty()) {
            out.println("<p>Your cart is empty.</p>");
        } else {
            out.println("<ul>");
            double total = 0;

            for (CartItem item : cart) {
                out.println("<li>" + item.getProductName() + " - $" + item.getPrice() + "</li>");
                total += item.getPrice();
            }
            out.println("</ul>");
            out.println("<h1><strong>Then The Total is: $" + total + "</strong></h1>");
            req.setAttribute("total", total);

        }

    out.println("<a href='login.html'><h2>PLACE ORDER</h2></a>");



        out.println("</body>");
        out.println("</html>");

    }
}
