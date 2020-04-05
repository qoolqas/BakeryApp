package com.q.bakeryapp.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.q.bakeryapp.connection.Client;
import com.q.bakeryapp.connection.Service;
import com.q.bakeryapp.model.ProdukResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<ProdukResponse> produk;

    void loadProduct() {

        Service service = Client.getClient().create(Service.class);
        Call<ProdukResponse> call = service.getProduk();
        call.enqueue(new Callback<ProdukResponse>() {

            @Override
            public void onResponse(@NotNull Call<ProdukResponse> call, @NotNull Response<ProdukResponse> response) {
                produk.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ProdukResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    LiveData<ProdukResponse> liveGet() {
        if (produk == null) {
            produk = new MutableLiveData<>();
            loadProduct();
        }
        return produk;
    }
}