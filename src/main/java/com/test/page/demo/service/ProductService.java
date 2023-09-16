package com.test.page.demo.service;

import com.test.page.demo.entity.Product;
import com.test.page.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

   //@PostConstruct
    public void initDB(){
        List<Product> productList = IntStream.rangeClosed(1, 200)
               .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextLong(50000)))
               .collect(Collectors.toList());
        repository.saveAll(productList);
    }

    public List<Product> findAllProduct(){
        return repository.findAll();
    }

    public List<Product> findProductsWithSorting(String field){
       return repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC,field));
        return products;
    }
}
