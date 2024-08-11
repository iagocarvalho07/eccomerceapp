package com.iagocarvalho.eccomerceapp.product;

import com.iagocarvalho.eccomerceapp.categories.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryOrderByPriceAsc(CategoryModel categoryModel, Pageable pageDetails);

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);
}
