<%@ page import="java.util.ArrayList" %>  <!-- Import ArrayList -->
<%@ page import="com.sample.Product" %>  <!-- Correctly import Product class -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shopping Cart</title>
</head>
<body>
    <h1>Your Shopping Cart</h1>

    <ul>
        <%
            // Retrieve the cart from the session
            ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");

            // Check if the cart is not empty
            if (cart != null && !cart.isEmpty()) {
                // Loop through the products in the cart
                for (Product product : cart) {
        %>
                    <li><%= product.toString() %></li> <!-- Display each product -->
        <%
                }
            } else {
        %>
                <p>Your cart is empty.</p> <!-- Message if cart is empty -->
        <%
            }
        %>
    </ul>

    <p><a href="index.html">Continue Shopping</a></p>
</body>
</html>
