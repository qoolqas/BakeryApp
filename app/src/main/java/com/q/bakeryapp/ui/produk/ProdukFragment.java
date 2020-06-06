package com.q.bakeryapp.ui.produk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.q.bakeryapp.R;


public class ProdukFragment extends Fragment {
    CardView cardRoti, cardKukis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardRoti = view.findViewById(R.id.cardRoti);
        cardKukis = view.findViewById(R.id.cardKukis);
        cardRoti.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_produk_to_nav_basah, null));
        cardKukis.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_produk_to_nav_kering, null));
    }
}
