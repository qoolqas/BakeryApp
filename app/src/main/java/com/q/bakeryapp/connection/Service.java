package com.q.bakeryapp.connection;

import com.q.bakeryapp.model.login.LoginResponse;
import com.q.bakeryapp.model.produk.ProdukResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {


    @GET("read.php")
    Call<ProdukResponse> getProduk();

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password
    );

}
