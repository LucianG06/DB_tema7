package com.db.tema7.controller;

import com.db.tema7.model.Product;
import com.db.tema7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product/insert")
    public void addProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @PostMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.softDelete(id);
    }

    @GetMapping("/product/getAll")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/getAllAndDeleted")
    public List<Product> getAllProductsAndDeleted() {
        return productService.getAllProductsAndDeleted();
    }

    @PostMapping("product/updateStock/{id}/{stock}")
    public void updateStock(@PathVariable Long id, @PathVariable Integer stock){
        productService.updateStock(id, stock);
    }

    @PostMapping("product/increaseStock/{id}")
    public void increaseStock(@PathVariable Long id){
        productService.increaseStock(id);
    }

    @PostMapping("product/decreaseStock/{id}")
    public void decreaseStock(@PathVariable Long id){
        productService.decreaseStock(id);
    }
}
