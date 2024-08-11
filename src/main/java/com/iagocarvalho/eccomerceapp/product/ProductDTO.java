package com.iagocarvalho.eccomerceapp.product;

import java.util.Objects;

public class ProductDTO {
    private Long productId;
    private String productName;
    private String image;
    private Integer quantity;
    private double price;
    private double disconte;
    private double specialPrice;

    public ProductDTO() {
    }

    public ProductDTO(Long productId, String productName, String image, double price, Integer quantity, double disconte, double specialPrice) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.disconte = disconte;
        this.specialPrice = specialPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getDisconte() {
        return disconte;
    }

    public void setDisconte(double disconte) {
        this.disconte = disconte;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Double.compare(price, that.price) == 0 && Double.compare(disconte, that.disconte) == 0 && Double.compare(specialPrice, that.specialPrice) == 0 && Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && Objects.equals(image, that.image) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, image, quantity, price, disconte, specialPrice);
    }
}
