package com.iagocarvalho.eccomerceapp.product;


import com.iagocarvalho.eccomerceapp.categories.CategoryModel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String image;
    private String description;
    private Integer quantity;
    private double price;
    private double disconte;
    private double specialPrice;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModel category;

    public Product() {
    }

    public Product(Long productId, String productName, String image, String description, Integer quantity, double price, double disconte, double specialPrice, CategoryModel category) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.disconte = disconte;
        this.specialPrice = specialPrice;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getDisconte() {
        return disconte;
    }

    public void setDisconte(double disconte) {
        this.disconte = disconte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Double.compare(disconte, product.disconte) == 0 && Double.compare(specialPrice, product.specialPrice) == 0 && Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && Objects.equals(image, product.image) && Objects.equals(description, product.description) && Objects.equals(quantity, product.quantity) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, image, description, quantity, price, disconte, specialPrice, category);
    }
}
