package com.iagocarvalho.eccomerceapp.categories;


import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoriesServiceIMPL categoriesServiceIMPL;
	
	
	@GetMapping("/api/public/categories")
	public ResponseEntity<List<CategoryModel>> getAllCategories(){
		List<CategoryModel> categories =  categoriesServiceIMPL.getAllCategories();
		return  new ResponseEntity<List<CategoryModel>>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/api/public/categories")
	public ResponseEntity<String> createCategrory(@Valid @RequestBody CategoryModel categoryModel) {
		categoriesServiceIMPL.createCategory(categoryModel);
		return  new ResponseEntity<>("CAtegory create ", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/public/categories/{categorieId}")
	public ResponseEntity<String> deleteCategrory( @PathVariable Long categorieId) {
//		try {
//
//			String status =  categoriesServiceIMPL.deleteCategorie(categorieId);
//			return  ResponseEntity.status( HttpStatus.OK).body(status);
//		} catch (ResponseStatusException e) {
//			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
//		}
		String status =  categoriesServiceIMPL.deleteCategorie(categorieId);
		return  ResponseEntity.status( HttpStatus.OK).body(status);
		
		
	}
	
	@PutMapping("/api/public/categories/{categorieId}")
	public ResponseEntity<String> updateCategories(
			@Valid
			@PathVariable Long categorieId,
			@RequestBody CategoryModel categoryModel){
//		try {
//
//			CategoryModel saveCategorie =  categoriesServiceIMPL.updateCategorie(categoryModel, categorieId);
//			return  ResponseEntity.status( HttpStatus.OK).body(" category with category id " +  categorieId + saveCategorie);
//		} catch (ResponseStatusException e) {
//			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
//		}
		CategoryModel saveCategorie =  categoriesServiceIMPL.updateCategorie(categoryModel, categorieId);
		return  ResponseEntity.status( HttpStatus.OK).body(" category with category id " +  categorieId + saveCategorie);

		
		
	}

}
