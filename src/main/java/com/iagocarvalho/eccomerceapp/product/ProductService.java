package com.iagocarvalho.eccomerceapp.product;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize);

    ProductResponse searchByCategory(Long categoriId, Integer pageNumber, Integer pageSize);

    ProductResponse searchProductBykeyword(String keyword, Integer pageNumber, Integer pageSize);

    ProductDTO updateProduct(Long productId, Product product);

    ProductDTO deleteProduct(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
