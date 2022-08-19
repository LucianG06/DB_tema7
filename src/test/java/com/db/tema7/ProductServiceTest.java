package com.db.tema7;

import com.db.tema7.model.Product;
import com.db.tema7.model.ProductType;
import com.db.tema7.repository.ProductRepository;
import com.db.tema7.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void itShouldAddProduct() throws Exception {
        Product testProduct = new Product(1L, "Mouse", ProductType.ELG, 14, false);
        when(productRepository.save(any(Product.class))).thenReturn(new Product());

        Product product = productService.addNewProduct(testProduct);
        Assertions.assertThat(product.getName()).isSameAs(testProduct.getName());
    }
}
