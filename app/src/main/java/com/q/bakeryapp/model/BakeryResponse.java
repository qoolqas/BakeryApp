package com.q.bakeryapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BakeryResponse {
    @SerializedName("data")
    private List<BakeryModel> data;

    public List<BakeryModel> getData() {
        return data;
    }

    public void setData(List<BakeryModel> data) {
        this.data = data;
    }
}
