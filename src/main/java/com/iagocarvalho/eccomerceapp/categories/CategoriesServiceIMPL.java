package com.iagocarvalho.eccomerceapp.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoriesServiceIMPL implements CategoriesService {
	
	

	private List<CategoryModel> CategoriList = new ArrayList<>();
	private Long nextInd = 1L;

	@Override
	public List<CategoryModel> getAllCategories() {
		
		return CategoriList;
	}

	@Override
	public void createCategory(CategoryModel categoryModel) {
		categoryModel.setCategoryId(nextInd++);
		CategoriList.add(categoryModel);

	}
	@Override
	public String deleteCategorie(Long categorieId) {
		CategoryModel categorie = findCategorieById(categorieId);
		CategoriList.remove(categorie);
		return "categoriry with this id " + categorieId + " deleted sucessful";
	}
	@Override
	public CategoryModel updateCategorie(CategoryModel categoryModel, Long categorieId) {
		  Optional<CategoryModel> optionalCategory = CategoriList.stream()
	                .filter(c -> c.getCategoryId().equals(categorieId))
	                .findFirst();

	        if(optionalCategory.isPresent()){
	        	CategoryModel existingCategory = optionalCategory.get();
	            existingCategory.setCategoryName(categoryModel.getCategoryName());
	            return existingCategory;
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
	        }
	}
	
	private CategoryModel findCategorieById(Long categorieId) {
		CategoryModel categorie = CategoriList.stream()
				.filter(c -> c.getCategoryId().equals(categorieId))
				.findFirst().orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
		return categorie;
	}

}
