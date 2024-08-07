package com.iagocarvalho.eccomerceapp.categories;


import java.util.List;

import com.iagocarvalho.eccomerceapp.config.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoriesServiceIMPL categoriesServiceIMPL;
	
	
	@GetMapping("/api/public/categories")
	public ResponseEntity<CategoryResponse> getAllCategories(){
		CategoryResponse categories =  categoriesServiceIMPL.getAllCategories();
		return  new ResponseEntity<CategoryResponse>(categories, HttpStatus.OK);
	}

	@GetMapping("/api/public/categoriesPage")
	public ResponseEntity<CategoryResponse> getCategoriesPage(
			@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
		CategoryResponse categories =  categoriesServiceIMPL.getCategoriesPage(pageNumber, pageSize);
		return  new ResponseEntity<CategoryResponse>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/api/public/categories")
	public ResponseEntity<String> createCategrory(@Valid @RequestBody CategoryDTO categoryModel) {
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
	public ResponseEntity<CategoryDTO> updateCategories(
			@Valid
			@PathVariable Long categorieId,
			@RequestBody CategoryDTO categoryDTO){
//		try {
//
//			CategoryModel saveCategorie =  categoriesServiceIMPL.updateCategorie(categoryModel, categorieId);
//			return  ResponseEntity.status( HttpStatus.OK).body(" category with category id " +  categorieId + saveCategorie);
//		} catch (ResponseStatusException e) {
//			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
//		}
		CategoryDTO saveCategorieDTP =  categoriesServiceIMPL.updateCategorie(categoryDTO, categorieId);
		return new  ResponseEntity<>(saveCategorieDTP, HttpStatus.OK);

		
		
	}

}
