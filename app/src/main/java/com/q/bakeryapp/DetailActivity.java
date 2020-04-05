package com.q.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.q.bakeryapp.model.ProdukModel;

public class DetailActivity extends AppCompatActivity {
    ProdukModel produkModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        produkModel = getIntent().getParcelableExtra("data");
    }
}
