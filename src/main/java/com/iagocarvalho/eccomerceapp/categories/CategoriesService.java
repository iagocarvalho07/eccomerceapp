package com.iagocarvalho.eccomerceapp.categories;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CategoriesService {
	CategoryResponse getAllCategories();
	CategoryResponse getCategoriesPage(Integer pageNumber, Integer pageSize);
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	 String deleteCategorie(Long categorieId);
	 CategoryDTO updateCategorie(CategoryDTO categoryDTO, Long categorieId);

}
