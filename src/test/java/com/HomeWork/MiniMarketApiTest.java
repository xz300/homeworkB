package com.HomeWork;

import com.retrofit.api.MiniMarketService;
import com.retrofit.model.ProductDto;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MiniMarketApiTest {
    private static MiniMarketService miniMarketService;

    public MiniMarketApiTest() throws IOException {
    }

    @BeforeAll
    static void beforeALL() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8189/market/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        miniMarketService = retrofit.create(MiniMarketService.class);
    }

    @Test
    @Order(1)
    @DisplayName("Получение продукта")
    void getProduct() throws IOException {
        ProductDto dto = miniMarketService.getProduct(13)
                .execute()
                .body();
        System.out.println("product: " + dto);
    }

    @Test
    @Order(2)
    @DisplayName("Получение списка продуктов")
    void getProducts() throws IOException {
        List<ProductDto> products = miniMarketService.getProducts()
                .execute()
                .body();
        System.out.println("products: " + products);
    }

    ProductDto dto = ProductDto.builder()
            .title("Juice")
            .categoryTitle("Food")
            .price(85)
            .build();

    @Test
    @Order(3)
    @DisplayName("Добавление продукта")
    void addProduct() throws IOException {
        ProductDto body = miniMarketService
                .createProduct(dto)
                .execute()
                .body();
        System.out.println("products: " + body);
    }

    @Test
    @Order(4)
    @DisplayName("Апдейт продукта")
    void tearDown() throws IOException {
        ProductDto returnProduct = ProductDto.builder()
                .id((long) 2)
                .title("Bread")
                .price(22)
                .categoryTitle("Food")
                .build();
        Response<ProductDto> updateProduct = miniMarketService.updateProduct(returnProduct).execute();
        System.out.println("products: " + returnProduct);
    }

    @Test
    @Order(5)
    @DisplayName("Проверка категории продукта")
    void getProducts1() throws IOException {
        List<ProductDto> responseProducts = miniMarketService.getProducts().execute().body();
        assertThat(responseProducts.get(4).getCategoryTitle(), equalTo("Electronic"));
    }

    @Test
    @Order(6)
    @DisplayName("Удаление продукта")
    void delProduct() throws IOException {
        ResponseBody dto = miniMarketService.deleteProduct(5)
                .execute()
                .body();
        System.out.println("Продукт удален: " + dto);
    }

}
