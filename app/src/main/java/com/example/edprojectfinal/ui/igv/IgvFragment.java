package com.example.edprojectfinal.ui.igv;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.edprojectfinal.R;
import com.example.edprojectfinal.databinding.FragmentIgvBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IgvFragment extends Fragment {

    private IgvViewModel igvViewModel;
    private FragmentIgvBinding binding;
    EditText text_monto,v_igvdolar,v_igvsol,v_dolar,v_sol;
    Button btnigv;
    RadioButton text_sol,text_dolar;
    View vista;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_igv, container, false);
        text_monto=vista.findViewById(R.id.text_monto);
        v_igvdolar=vista.findViewById(R.id.v_igvdolar);
        v_igvsol=vista.findViewById(R.id.v_igvsol);
        v_dolar=vista.findViewById(R.id.v_dolar);
        v_sol=vista.findViewById(R.id.v_sol);
        text_sol=vista.findViewById(R.id.text_sol);
        text_dolar=vista.findViewById(R.id.text_dolar);
        btnigv=vista.findViewById(R.id.btnigv);
        btnigv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcular();
            }
        });
        return vista;
    }
    public void Calcular(){

        double monto = Integer.parseInt(text_monto.getText().toString());
        double igvhoy = 0.18;
        double cambiodolar = 3.98;

        if(monto==0) /*Falta la sentencia para ver cuando no hay dato ingresado */{
            text_monto.setError("Ingrese datos por favor!");
            text_monto.requestFocus();
        }else{
            if (text_sol.isChecked() ==true || text_dolar.isChecked() ==true){

                if (text_sol.isChecked() ==true){
                    double montodolar = monto/cambiodolar;
                    double igvdolar = (montodolar * igvhoy);
                    double igvsol = (monto * igvhoy);
                    double totaldolar = igvdolar+montodolar;
                    double totalsol = (monto * igvhoy) + monto;
                    BigDecimal igvsol2 = new BigDecimal(igvsol);
                    igvsol2 = igvsol2.setScale(2, RoundingMode.DOWN);
                    BigDecimal igvdolar2 = new BigDecimal(igvdolar);
                    igvdolar2 = igvdolar2.setScale(2, RoundingMode.DOWN);
                    BigDecimal totalsol2 = new BigDecimal(totalsol);
                    totalsol2 = totalsol2.setScale(2, RoundingMode.DOWN);
                    BigDecimal totaldolar2 = new BigDecimal(totaldolar);
                    totaldolar2 = totaldolar2.setScale(2, RoundingMode.DOWN);
                    String t_igvdolar = String.valueOf(igvdolar2);
                    String t_igvsol = String.valueOf(igvsol2);
                    String t_totaldolar = String.valueOf(totaldolar2);
                    String t_totalsol = String.valueOf(totalsol2);
                    v_igvdolar.setText(t_igvdolar);
                    v_igvsol.setText(t_igvsol);
                    v_dolar.setText(t_totaldolar);
                    v_sol.setText(t_totalsol);

                }else{
                    if (text_dolar.isChecked() ==true){
                        double montosol = monto*cambiodolar;
                        double igvsol = (montosol * igvhoy);
                        double igvdolar = (monto * igvhoy);
                        double totalsol = igvsol+montosol;
                        double totaldolar = (monto * igvhoy) + monto;
                        BigDecimal igvsol2 = new BigDecimal(igvsol);
                        igvsol2 = igvsol2.setScale(2, RoundingMode.DOWN);
                        BigDecimal igvdolar2 = new BigDecimal(igvdolar);
                        igvdolar2 = igvdolar2.setScale(2, RoundingMode.DOWN);
                        BigDecimal totalsol2 = new BigDecimal(totalsol);
                        totalsol2 = totalsol2.setScale(2, RoundingMode.DOWN);
                        BigDecimal totaldolar2 = new BigDecimal(totaldolar);
                        totaldolar2 = totaldolar2.setScale(2, RoundingMode.DOWN);
                        String t_igvsol = String.valueOf(igvsol2);
                        String t_igvdolar = String.valueOf(igvdolar2);
                        String t_totaldolar = String.valueOf(totaldolar2);
                        String t_totalsol = String.valueOf(totalsol2);
                        v_igvdolar.setText(t_igvdolar);
                        v_igvsol.setText(t_igvsol);
                        v_dolar.setText(t_totaldolar);
                        v_sol.setText(t_totalsol);
                    }
                }
            }else{
            text_monto.setError("Seleccionar moneda por favor!");
            text_monto.requestFocus();
            }
        }
    }
    public void LimpiarCampos(){
        text_monto.setText("");
        v_igvdolar.setText("");
        v_igvsol.setText("");
        v_dolar.setText("");
        v_sol.setText("");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}