package com.q.bakeryapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.q.bakeryapp.R;
import com.q.bakeryapp.SharedPrefManager;
import com.q.bakeryapp.ui.manage.ManageActivity;

public class HomeFragment extends Fragment {
    private CardView cardProduk, cardManage, cardToko, cardProfile;
    SharedPrefManager sharedPrefManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getActivity());
        TextView nama = view.findViewById(R.id.namaUser);
        cardProduk = view.findViewById(R.id.cardProduk);
        cardManage = view.findViewById(R.id.cardManage);
        cardToko = view.findViewById(R.id.cardToko);
        cardProfile = view.findViewById(R.id.cardProfile);
        nama.setText("Welcome "+ sharedPrefManager.getSpName());
        cardProduk.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_produk, null));
        cardToko.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_toko, null));
        cardProfile.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_profile, null));

        cardManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ManageActivity.class);
                startActivity(intent);
            }
        });

    }
}
