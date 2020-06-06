package com.q.bakeryapp.ui.produk.basah;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;
import com.q.bakeryapp.model.produk.ProdukResponse;
import com.q.bakeryapp.ui.produk.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BasahFragment extends Fragment {
    private RecyclerView recyclerView;
    private ViewModel viewModel;
    private BasahAdapter adapter;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    private ProgressBar pb;
    private List<ProdukModel> produk = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basah, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new BasahAdapter(this, getActivity());
        adapter.setProduk(produk);
        recyclerView = view.findViewById(R.id.rv);
        pb = view.findViewById(R.id.pb);
        initRV();
        getData();
        pb.setVisibility(View.VISIBLE);
    }

    private void getData() {


        viewModel.liveGetBasah().observe(getViewLifecycleOwner(), new Observer<ProdukResponse>() {
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
