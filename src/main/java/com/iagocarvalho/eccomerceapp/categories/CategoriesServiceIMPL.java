package com.iagocarvalho.eccomerceapp.categories;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iagocarvalho.eccomerceapp.exceptions.APIException;
import com.iagocarvalho.eccomerceapp.exceptions.MyResourceNotFoundException;

@Service
public class CategoriesServiceIMPL implements CategoriesService {

	@Autowired
	private CategoiresRepository categoiresRepository;



	@Override
	public List<CategoryModel> getAllCategories() {
		List<CategoryModel> categories = categoiresRepository.findAll();
		if (categories.isEmpty() ) {
			throw new APIException("No category created till now");
		}
		return categoiresRepository.findAll();
	}

	@Override
	public void createCategory(CategoryModel categoryModel) {
		CategoryModel savedCategori = categoiresRepository.findByCategoryName(categoryModel.getCategoryName());
		if (savedCategori != null) {
			throw new APIException("Category with the name " + categoryModel.getCategoryName() + "alredy exists!!");
		}else {
			categoiresRepository.save(categoryModel);
		}
	}

	@Override
	public String deleteCategorie(Long categorieId) {
		CategoryModel categorie = categoiresRepository.findById(categorieId).orElse(null);
		if (categorie != null) {
			categoiresRepository.delete(categorie);
			return "categoriry with this id " + categorieId + " deleted sucessful";
		} else {
			return "categoriry with this id " + categorieId + " not Found";
		}

	}

	@Override
	public CategoryModel updateCategorie(CategoryModel categoryModel, Long categorieId) {
//		List<CategoryModel> CategoriList = categoiresRepository.findAll();
//		  Optional<CategoryModel> optionalCategory = CategoriList.stream()
//	                .filter(c -> c.getCategoryId().equals(categorieId))
//	                .findFirst();
//
//	        if(optionalCategory.isPresent()){
//	        	CategoryModel existingCategory = optionalCategory.get();
//	            existingCategory.setCategoryName(categoryModel.getCategoryName());
//	            CategoryModel savedCategory = categoiresRepository.save(existingCategory);
//	            return savedCategory;
		CategoryModel categorieUpdate = categoiresRepository.findById(categorieId).orElse(null);
		if (categorieUpdate != null) {
			categorieUpdate.setCategoryName(categoryModel.getCategoryName());
			CategoryModel UpdatedCategori = categoiresRepository.save(categorieUpdate);
			return UpdatedCategori;
		} else {
			throw new  MyResourceNotFoundException("Category", "categoryId", categorieId);
		}
	}

//	private CategoryModel findCategorieById(Long categorieId) {
//		CategoryModel categorie = CategoriList.stream()
//				.filter(c -> c.getCategoryId().equals(categorieId))
//				.findFirst().orElseThrow(
//						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
//		return categorie;
//	}

}
