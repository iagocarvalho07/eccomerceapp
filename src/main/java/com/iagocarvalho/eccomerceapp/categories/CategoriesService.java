package com.iagocarvalho.eccomerceapp.categories;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CategoriesService {
	List<CategoryModel> getAllCategories();
	void createCategory(CategoryModel categoryModel);
	public String deleteCategorie(Long categorieId);
	public CategoryModel updateCategorie(CategoryModel categoryModel, Long categorieId);

}
