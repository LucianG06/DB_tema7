package com.db.tema7.service;

import com.db.tema7.model.Product;
import com.db.tema7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public void softDelete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setDeleted(true);
            productRepository.save(product.get());
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().iterator()
                .forEachRemaining(product -> {if (!product.getDeleted()) products.add(product);});
        return products;
    }

    public List<Product> getAllProductsAndDeleted() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    public void updateStock(Long id, Integer newStock) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setStock(newStock);
            productRepository.save(product.get());
        }
    }

    @Modifying
    @Query("UPDATE Product product set product.stock = product.stock + 1 WHERE product.id = :productId")
    public void increaseStock(@Param("productId") Long productId) {

    }

    @Modifying
    @Query("UPDATE Product product set product.stock = product.stock - 1 WHERE product.id = :productId")
    public void decreaseStock(@Param("productId") Long productId) {

    }

}
