package com.q.bakeryapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.q.bakeryapp.R;
import com.q.bakeryapp.SharedPrefManager;

public class SlideshowFragment extends Fragment {
    TextView profileName, logout, profileEmail;
    SharedPrefManager sharedPrefManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());

        profileName = root.findViewById(R.id.profileName);
        profileEmail = root.findViewById(R.id.profileEmail);
        profileEmail.setText(sharedPrefManager.getSpEmail());
        profileName.setText(sharedPrefManager.getSpName());
        return root;
    }
}
