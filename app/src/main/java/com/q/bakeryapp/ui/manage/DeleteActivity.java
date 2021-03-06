package com.q.bakeryapp.ui.manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;
import com.q.bakeryapp.model.produk.ProdukResponse;
import com.q.bakeryapp.ui.produk.ViewModel;
import com.q.bakeryapp.ui.produk.basah.BasahAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ViewModel viewModel;
    private DeleteAdapter adapter;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    private ProgressBar pb;
    private List<ProdukModel> produk = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new DeleteAdapter(this, this);
        adapter.setProduk(produk);
        recyclerView = findViewById(R.id.rv);
        pb = findViewById(R.id.pb);
        initRV();
        getData();
        pb.setVisibility(View.VISIBLE);
    }
    private void getData() {


        viewModel.liveGet().observe(this, new Observer<ProdukResponse>() {
            @Override
            public void onChanged(ProdukResponse produkResponse) {

                try {
                    produk.clear();
                    produk.addAll(produkResponse.getData());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                } catch (Exception e) {
                    Log.d("catch", "produk");
                }
            }
        });
    }

    private void initRV() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
