package com.iagocarvalho.eccomerceapp.categories;

import java.util.Objects;


public class CategoryModel {
	
	
	private Long categoryId;
	private String CategoryName;
	public CategoryModel() {
	}
	public CategoryModel(Long categoryId, String categoryName) {
		this.categoryId = categoryId;
		CategoryName = categoryName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(CategoryName, categoryId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryModel other = (CategoryModel) obj;
		return Objects.equals(CategoryName, other.CategoryName) && Objects.equals(categoryId, other.categoryId);
	}
	
}
