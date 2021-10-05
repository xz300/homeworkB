package com.retrofit;

import com.retrofit.api.MiniMarketService;
import com.retrofit.model.ProductDto;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;


public class TestApi {
    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8189/market/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MiniMarketService marketService = retrofit.create(MiniMarketService.class);

        ProductDto product = marketService.getProduct(1)
                .execute()
                .body();

        List<ProductDto> products = marketService.getProducts()
                .execute()
                .body();

        System.out.println("product: " + product);
        System.out.println("products: " + products);

        ProductDto dto = ProductDto.builder()
                .title("Juice")
                .categoryTitle("Food")
                .price(80)
                .build();

        ProductDto body = marketService.createProduct(dto).execute().body();
        System.out.println(body);

    }
}
