package com.db.tema7;

import com.db.tema7.controller.ProductController;
import com.db.tema7.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void itShouldAddProduct() throws Exception {
        String request = "{\"name\":\"Laptop\", \"productType\":\"ELG\", \"stock\": 10, \"deleted\": false}";
        mockMvc.perform(post("/product/insert")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void itShouldGetAllProducts() throws Exception {
        mockMvc.perform(get("/product/getAll")).andExpect(status().isOk());
    }
}
