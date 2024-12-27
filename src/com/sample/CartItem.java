package com.sample;

public class CartItem {
    private String productId;
    private double price;
    private String productName;

    public CartItem(String productId, double price,String productName) {
        this.productId = productId;
        this.price = price;
        this.productName=productName;

    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
