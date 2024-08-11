package com.iagocarvalho.eccomerceapp.product;

import com.iagocarvalho.eccomerceapp.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(
            @RequestBody Product product, @PathVariable Long categoryId){
       ProductDTO productDTO =  productService.addProduct(categoryId, product);
       return  new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE)Integer PageSize
    ){
       ProductResponse productResponse = productService.getAllProducts(pageNumber, PageSize);
       return  new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoriId}/products")
    public ResponseEntity<ProductResponse> getProductByCategory(
            @PathVariable Long categoriId,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE)Integer PageSize
    ){
        ProductResponse productResponse = productService.searchByCategory(categoriId, pageNumber, PageSize);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyWord(
            @PathVariable String keyword,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE)Integer PageSize
            ){
        ProductResponse productResponse = productService.searchProductBykeyword(keyword, pageNumber, PageSize);
        return  new ResponseEntity<>(productResponse, HttpStatus.FOUND);

    }

    @PutMapping("/admin/product/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product, @PathVariable Long productId){
        ProductDTO updateProductDTO =  productService.updateProduct(productId, product);
        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/product/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){
        ProductDTO deleteProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deleteProduct, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> UpdateProductImage(
            @PathVariable Long productId,
            @RequestParam("Image")MultipartFile image) throws IOException {
        ProductDTO updateProduct = productService.updateProductImage(productId, image);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);

    }
}
