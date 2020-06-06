package com.q.bakeryapp.ui.produk.kering;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;
import com.q.bakeryapp.ui.produk.ViewModel;
import com.q.bakeryapp.ui.produk.basah.BasahAdapter;

import java.util.ArrayList;
import java.util.List;


public class KeringFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_kering, container, false);
    }
}
