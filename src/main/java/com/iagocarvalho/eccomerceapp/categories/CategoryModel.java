package com.iagocarvalho.eccomerceapp.categories;

import java.util.List;
import java.util.Objects;


import com.iagocarvalho.eccomerceapp.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "categories")
public class CategoryModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	@NotBlank
	@Size(min = 5, message = "Category name must be have a min 5 caracter")
	private String categoryName;


	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;

	public CategoryModel() {
	}

	public CategoryModel(Long categoryId, String categoryName, List<Product> products) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.products = products;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public @NotBlank @Size(min = 5, message = "Category name must be have a min 5 caracter") String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(@NotBlank @Size(min = 5, message = "Category name must be have a min 5 caracter") String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoryModel that = (CategoryModel) o;
		return Objects.equals(categoryId, that.categoryId) && Objects.equals(categoryName, that.categoryName) && Objects.equals(products, that.products);
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, categoryName, products);
	}
}
