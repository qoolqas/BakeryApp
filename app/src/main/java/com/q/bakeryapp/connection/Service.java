package com.q.bakeryapp.connection;

import com.q.bakeryapp.model.ProdukModel;
import com.q.bakeryapp.model.ProdukResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {


    @GET("read.php")
    Call<ProdukResponse> getProduk();



}
