package com.iagocarvalho.eccomerceapp.categories;


import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.iagocarvalho.eccomerceapp.exceptions.APIException;
import com.iagocarvalho.eccomerceapp.exceptions.MyResourceNotFoundException;

@Service
public class CategoriesServiceIMPL implements CategoriesService {

	@Autowired
	private CategoiresRepository categoiresRepository;

	@Autowired
	private ModelMapper modelMapper;



	@Override
	public CategoryResponse getAllCategories() {
		List<CategoryModel> categories = categoiresRepository.findAll();
		if (categories.isEmpty() ) {
			throw new APIException("No category created till now");
		}
		List<CategoryDTO> categoryDTOS =
				categories.stream()
						.map(categoryModel -> modelMapper.map(
						categoryModel, CategoryDTO.class))
						.collect(Collectors.toList());
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setContent(categoryDTOS);
		return categoryResponse;
	}

	@Override
	public CategoryResponse getCategoriesPage(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<CategoryModel> categoryModelPage = categoiresRepository.findAll(pageable);
		List<CategoryModel> categories = categoryModelPage.getContent();
		if (categories.isEmpty() ) {
			throw new APIException("No category created till now");
		}
		List<CategoryDTO> categoryDTOS =
				categories.stream()
						.map(categoryModel -> modelMapper.map(
								categoryModel, CategoryDTO.class)).toList();

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setContent(categoryDTOS);
		categoryResponse.setPageNumbe(categoryModelPage.getNumber());
		categoryResponse.setPageSize(categoryModelPage.getSize());
		categoryResponse.setTotalElements(categoryModelPage.getTotalElements());
		categoryResponse.setTotalPgs(categoryModelPage.getTotalPages());
		categoryResponse.setLastPage(categoryModelPage.isLast());
		return categoryResponse;
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		CategoryModel categoryModel = modelMapper.map(categoryDTO, CategoryModel.class);
		CategoryModel savedCategori = categoiresRepository.findByCategoryName(categoryDTO.getCategoryName());
		if (savedCategori != null) {
			throw new APIException("Category with the name " + categoryDTO.getCategoryName() + "alredy exists!!");
		}else {
			  CategoryModel savedCAtedogry =  categoiresRepository.save(categoryModel);
			  CategoryDTO savedCAtedogryDTO = modelMapper.map(savedCAtedogry, CategoryDTO.class);
			  return  savedCAtedogryDTO;
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
	public CategoryDTO updateCategorie(CategoryDTO categoryDTO, Long categorieId) {
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
		CategoryModel categorieUpdate = categoiresRepository.findById(categorieId).orElseThrow(
				() ->  new  MyResourceNotFoundException("Category", "categoryId", categorieId));

			CategoryModel categoryModel = modelMapper.map(categoryDTO, CategoryModel.class);
			categoryModel.setCategoryId(categorieId);
			categorieUpdate = categoiresRepository.save(categoryModel);
			return modelMapper.map(categorieUpdate, CategoryDTO.class);



	}

//	private CategoryModel findCategorieById(Long categorieId) {
//		CategoryModel categorie = CategoriList.stream()
//				.filter(c -> c.getCategoryId().equals(categorieId))
//				.findFirst().orElseThrow(
//						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
//		return categorie;
//	}

}
