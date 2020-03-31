package com.q.bakeryapp.connection;

import com.q.bakeryapp.model.BakeryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {


    @GET("allData")
    Call<BakeryModel> getAll();



}
