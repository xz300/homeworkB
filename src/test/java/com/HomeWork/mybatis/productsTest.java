package com.HomeWork.mybatis;

import com.gb.db.dao.ProductsMapper;
import com.gb.db.model.Products;
import com.gb.db.model.ProductsExample;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class productsTest {
    public static ProductsMapper mapper;

    @BeforeAll
    public static void beforeAll() {
        mybatisHelp mybatisHelp = new mybatisHelp();
        mapper = mybatisHelp.getProductMapper();
    }
    @Order(1)
    @Test
    @DisplayName("Получение списка продуктов")
    void getProducts() {
        ProductsExample criteria = new ProductsExample();
        criteria.createCriteria()
                .andIdIsNotNull();
        List<Products> productsList = mapper.selectByExample(criteria);
        System.out.println(productsList);
        assertTrue(productsList.size() > 0);
    }
    @Order(2)
    @Test
    @DisplayName("Получение продукта")
    void getProduct() {
        Products product = mapper.selectByPrimaryKey(13L);
        Assertions.assertEquals(product.getPrice(), 360);
        Assertions.assertEquals(product.getCategoryId(), 1);
        Assertions.assertEquals(product.getTitle(), "Cheese");
    }
    @Order(3)
    @Test
    @DisplayName("Удаление продукта")
    void deleteProduct() {
        mapper.deleteByPrimaryKey(13L);
        Products product = mapper.selectByPrimaryKey(13L);
        System.out.println(product);
        Assertions.assertNull(product);
    }

    @Order(4)
    @Test
    @DisplayName("Добавление продукта")
    void createProduct() {
        Products product = new Products();
        product.setPrice(10);
        product.setTitle("Juice");
        product.setCategoryId(1L);
        mapper.insert(product);
        System.out.println(product);
        ProductsExample criteria = new ProductsExample();
        criteria.createCriteria()
                .andIdIsNotNull()
                .andPriceEqualTo(10)
                .andCategoryIdEqualTo(1L)
                .andTitleEqualTo("Juice");
        List<Products> productsList = mapper.selectByExample(criteria);
        System.out.println(productsList);
        assertTrue(productsList.size() > 0);
    }
}
