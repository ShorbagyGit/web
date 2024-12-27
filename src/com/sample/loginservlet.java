package com.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {

    // Database connection details
    static String user = "root";
    static String pass = "admin@123";
    static String address = "jdbc:mysql://localhost:3306/webdb";
    static Connection connection = null;
    static PreparedStatement customerStmt = null;
    static PreparedStatement productStmt = null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get session and cart information
        HttpSession session = req.getSession(true);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

           //user input
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("Password");


        // Set the response content type
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            connection = DriverManager.getConnection(address, user, pass);

            // Insert into Customers table
            String customerQuery = "INSERT INTO Customers (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
            customerStmt = connection.prepareStatement(customerQuery);
            customerStmt.setString(1, firstname);
            customerStmt.setString(2, lastname);
            customerStmt.setString(3, email);
            customerStmt.setString(4, password);

            // Execute the query to insert customer data
            int customerRowsAffected = customerStmt.executeUpdate();
            if (customerRowsAffected > 0) {
                out.println("<h2>Customer data inserted successfully!</h2>");
            } else {
                out.println("<h2>Failed to insert customer data.</h2>");
            }

            // Print the form data to the user (this is optional)
            out.println("<html><head><title>Login Servlet</title></head><body>");
            out.println("<h1><b>First Name: " + firstname + "</b></h1>");
            out.println("<h1><b>Last Name: " + lastname + "</b></h1>");
            out.println("<h1><b>Email: " + email + "</b></h1>");
            out.println("<h1><b>Password: " + password + "</b></h1>");

            // If the cart is not empty, insert products into the Products table
            if (cart != null && !cart.isEmpty()) {
                String productQuery = "INSERT INTO Products (productId, productName, price) VALUES (?, ?, ?)";

                productStmt = connection.prepareStatement(productQuery);

                for (CartItem item : cart) {
                    productStmt.setString(1, item.getProductId());
                    productStmt.setString(2, item.getProductName());
                    productStmt.setDouble(3, item.getPrice());

                    // Execute the query to insert product data into Products table
                    int productRowsAffected = productStmt.executeUpdate();
                    if (productRowsAffected > 0) {
                        out.println("<hr>");
                        out.println("<h2>Product Name: " + item.getProductName() + "</h2>");
                        out.println("<h2>Price: $" + item.getPrice() + "</h2>");
                        out.println("<hr>");

                    } else {
                        out.println("<h2>Failed to insert product: " + item.getProductName() + "</h2>");
                    }
                }
            } else {
                out.println("<h1><h1 style='color:darkred;'><b>Your cart is empty.</b></h1></h1>");
            }

            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            // Print detailed error for debugging
            e.printStackTrace();
            out.println("<h1>Error while processing data!</h1>");
            out.println("<pre>");
            out.println("Error: " + e.getMessage());
            out.println("</pre>");
        }





    }

}
