package com.example.edprojectfinal.ui.servicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.edprojectfinal.R;
import com.example.edprojectfinal.databinding.FragmentServiciosBinding;

public class ServiciosFragment extends Fragment {

    private ServiciosViewModel serviciosViewModel;
    private FragmentServiciosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        serviciosViewModel =
                new ViewModelProvider(this).get(ServiciosViewModel.class);

        binding = FragmentServiciosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textServicios;
        serviciosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}