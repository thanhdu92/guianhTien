package org.dung.module3.models;

public class Product {
    private String productname;
    private Integer price;
    private Integer quantity;
    private String color;
    private String category;

    public Product(String productname, Integer price, Integer quantity, String color, String category) {
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.category = category;
    }

    public Product() {

    }

    public Product(String productname, float price, float quantity, String color, String category) {
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
