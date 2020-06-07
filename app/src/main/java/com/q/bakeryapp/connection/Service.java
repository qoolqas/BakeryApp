package com.q.bakeryapp.connection;

import com.q.bakeryapp.model.delete.DeleteResponse;
import com.q.bakeryapp.model.login.LoginResponse;
import com.q.bakeryapp.model.produk.ProdukResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {


    @GET("read.php")
    Call<ProdukResponse> getProduk();

    @GET("read.php")
    Call<ProdukResponse> getProdukKategori(@Query("kategori")String kategori);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password
    );
    @DELETE("delete.php")
    Call<DeleteResponse> delete(@Query("produk_id")String id);

}
