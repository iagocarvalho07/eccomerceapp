package com.iagocarvalho.eccomerceapp.categories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoiresRepository extends JpaRepository<CategoryModel, Long> {

	CategoryModel findByCategoryName(String categoryName);

}

