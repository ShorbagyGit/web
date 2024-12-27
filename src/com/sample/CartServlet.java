package com.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String productId = req.getParameter("productId");
        double price = Double.parseDouble(req.getParameter("price"));


        String productInfo = "Product ID: " + productId + " - Price: " + price + "$" +" - product name: "+productName ;

        CartItem item = new CartItem(productId,price,productName);

        HttpSession session = req.getSession(true);
        if ( session == null ) return;

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(item);

        session.setAttribute("cart", cart);

        PrintWriter out= resp.getWriter();
            resp.setContentType("text/html");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cart Servlet</title>");
        out.println("<style>");
        out.println("  .toolbar {\n" +
                "            width: 100%;\n" +
                "            height: 5%;\n" +
                "            background-color: #2c2727;\n" +
                "            display: flex;\n" +
                "            justify-content: space-evenly; /* Space out the items evenly */\n" +
                "            align-items: center;\n" +
                "            position: fixed;\n" +
                "            top: 0;\n" +
                "            left: 0;\n" +
                "            z-index: 100;\n" +
                "            padding: 20px;\n" +
                "        }\n");
        out.println("h1 { opacity: 0; animation: fadeInUp 1s forwards; margin: 20px; }");
        out.println("h1:nth-child(2) { animation-delay: 1s; }");
        out.println("@keyframes fadeInUp {");
        out.println("    to { opacity: 1; transform: translateY(0); }");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<br><br><br><br>");
        out.println("<h1>HURRAYYYY! (: </h1>");
        out.println("<h1>Product added to cart!</h1>");
        out.println("<h2>Product Details:</h2>");
        out.println("<hr>");
        out.println("<p>Product Name: " + productName + "</p>");
        out.println("<p>Product ID: " + productId + "</p>");
        out.println("<p>Price: " + price + "$</p>");
        out.println("<hr>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        out.println("    <div class=\"toolbar\"  >\n" +
                "        <a href=\"ViewCartServlet\"><h2 style=\"color: DodgerBlue;\"><b>View Cart</b></h2></a>\n" +
                "        <a href=\"index.html\"><h2 style=\"color: DodgerBlue;\"><b>HOME</b></h2></a>\n" +
                "        <a href=\"contact.html\"><h2 style=\"color:DodgerBlue;\"><b> CONTACT US</b></h2> </a>\n" +
                "        <a href=\"return.html\"><h2 style=\"color:DodgerBlue;\"><b> RETURN-POLICY</b></h2> </a>");

    }

}
