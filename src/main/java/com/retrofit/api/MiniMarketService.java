package com.retrofit.api;

import com.retrofit.model.Category;
import com.retrofit.model.ProductDto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarketService {

    @GET("api/v1/products/{id}")
    Call<ProductDto> getProduct(@Path("id") long id);

    @GET("api/v1/products")
    Call<List<ProductDto>> getProducts();

    @POST("api/v1/products")
    Call<ProductDto> createProduct (@Body ProductDto dto);

    @PUT("/market/api/v1/products")
    Call<ProductDto> updateProduct(@Body ProductDto product);

    @DELETE("/market/api/v1/products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") long id);

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") long id);


}
