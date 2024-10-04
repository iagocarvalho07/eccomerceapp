package com.iagocarvalho.eccomerceapp.product;

import com.iagocarvalho.eccomerceapp.categories.CategoiresRepository;
import com.iagocarvalho.eccomerceapp.categories.CategoryModel;
import com.iagocarvalho.eccomerceapp.exceptions.APIException;
import com.iagocarvalho.eccomerceapp.exceptions.MyResourceNotFoundException;
import com.iagocarvalho.eccomerceapp.fileServices.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    CategoiresRepository categoiresRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {

        CategoryModel categoryModel = categoiresRepository.findById(categoryId)
                .orElseThrow(() ->
                new MyResourceNotFoundException("Category", "categoryId", categoryId));
        boolean ifProductNotPresent = true;

        List<Product> productList = categoryModel.getProducts();
        for (Product val: productList){
            if (val.getProductName().equals(product.getProductName())){
                ifProductNotPresent = false;
                break;
            }
        }
        if (ifProductNotPresent){
            product.setImage("defult.png");
            product.setCategory(categoryModel);
            double especialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
            product.setSpecialPrice(especialPrice);
            Product saveProduct = productRepository.save(product);
            return modelMapper.map(saveProduct, ProductDTO.class);
        }else {
            throw new APIException("Product Already exists");
        }
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize) {

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Product> Pageproducts =  productRepository.findAll(pageDetails);
        List<Product> products =  Pageproducts.getContent();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        if (products.isEmpty()){
            throw  new APIException("Não a produtos cadastrados");
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumbe(Pageproducts.getNumber());
        productResponse.setPageSize(Pageproducts.getSize());
        productResponse.setTotalElements(Pageproducts.getTotalElements());
        productResponse.setTotalPgs(Pageproducts.getTotalPages());
        productResponse.setLastPage(Pageproducts.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoriId, Integer pageNumber, Integer pageSize) {


        CategoryModel categoryModel = categoiresRepository.findById(categoriId)
                .orElseThrow(() ->
                        new MyResourceNotFoundException("Category", "categoryId", categoriId));
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Product> Pageproducts =  productRepository.findByCategoryOrderByPriceAsc(categoryModel, pageDetails);
        List<Product> products =  Pageproducts.getContent();
        //List<Product> products =  productRepository.findByCategoryOrderByPriceAsc(categoryModel);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumbe(Pageproducts.getNumber());
        productResponse.setPageSize(Pageproducts.getSize());
        productResponse.setTotalElements(Pageproducts.getTotalElements());
        productResponse.setTotalPgs(Pageproducts.getTotalPages());
        productResponse.setLastPage(Pageproducts.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse searchProductBykeyword(String keyword, Integer pageNumber, Integer pageSize) {
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Product> Pageproducts =  productRepository.findByProductNameLikeIgnoreCase("%" + keyword + "%", pageDetails);
        List<Product> products =  Pageproducts.getContent();


       // List<Product> products =  productRepository.findByProductNameLikeIgnoreCase( "%" + keyword + "%");
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();
        if (products.size() == 0){
            throw new APIException("Product not found with this keyword" + keyword);
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumbe(Pageproducts.getNumber());
        productResponse.setPageSize(Pageproducts.getSize());
        productResponse.setTotalElements(Pageproducts.getTotalElements());
        productResponse.setTotalPgs(Pageproducts.getTotalPages());
        productResponse.setLastPage(Pageproducts.isLast());
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, Product product) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new MyResourceNotFoundException("product", "productId",productId));
        productFromDB.setProductName(product.getProductName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setQuantity(product.getQuantity());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setDiscount(product.getDiscount());
        productFromDB.setSpecialPrice(product.getSpecialPrice());
        Product savedProduct = productRepository.save(productFromDB);
        return  modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new MyResourceNotFoundException("product", "ProductId", productId));
        productRepository.delete(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product productFromDb = productRepository
                .findById(productId).orElseThrow(() -> new MyResourceNotFoundException("product", "ProductId", productId));
        String fileName = fileService.uploadImage(path, image);
        productFromDb.setImage(fileName);
        Product savedproduct = productRepository.save(productFromDb);
        return  modelMapper.map(savedproduct, ProductDTO.class);
    }

}
