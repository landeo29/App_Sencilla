package com.example.edprojectfinal.ui.clientes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.edprojectfinal.R;
import com.example.edprojectfinal.databinding.FragmentClientesBinding;
import com.example.edprojectfinal.interfaces.IComunicaFragments;

public class ClientesFragment extends Fragment {
    private ClientesViewModel clientesViewModel;
    private FragmentClientesBinding binding;
    View vista;
    Activity actividad;
    CardView cardregistrarcliente, cardmodificarcliente, cardeliminarcliente, cardconsultarcliente;
    IComunicaFragments interfaceComunicaFragments;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clientesViewModel =
                new ViewModelProvider(this).get(ClientesViewModel.class);

        binding = FragmentClientesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textClientes;
        clientesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        vista= root;
        cardregistrarcliente=vista.findViewById(R.id.card_registrarCliente);

        cardconsultarcliente=vista.findViewById(R.id.card_consultarCliente);

        eventosMenu();

        return vista;
    }

    private void eventosMenu() {
        cardregistrarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragments.registrarCliente();
            }
        });

        cardconsultarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragments.consultarCliente();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            actividad=(Activity) context;
            interfaceComunicaFragments=(IComunicaFragments) actividad;
        }
    }
}