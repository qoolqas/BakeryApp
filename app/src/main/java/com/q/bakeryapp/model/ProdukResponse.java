package com.q.bakeryapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProdukResponse {
    @SerializedName("result")
    private List<ProdukModel> data;

    public List<ProdukModel> getData() {
        return data;
    }

    public void setData(List<ProdukModel> data) {
        this.data = data;
    }
}
