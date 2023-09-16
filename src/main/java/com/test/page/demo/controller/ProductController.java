package com.test.page.demo.controller;

import com.test.page.demo.dto.ApiResponse;
import com.test.page.demo.entity.Product;
import com.test.page.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ApiResponse<List<Product>> getProducts(){
        List<Product> allProducts = productService.findAllProduct();
        return new ApiResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/products/{field}")
    public ApiResponse<List<Product>> getProductsWithSort(@PathVariable String field){
        List<Product> allProducts = productService.findProductsWithSorting(field);
        return new ApiResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/products/{offset}/{pageSize}")
    public ApiResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        Page<Product> productsWithPagination = productService.findProductsWithPagination(offset, pageSize);
        return new ApiResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }

    @GetMapping("/products/{offset}/{pageSize}/{field}")
    public ApiResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<Product> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new ApiResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }
}
