package com.example.edprojectfinal.ui.clientes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edprojectfinal.R;
import com.example.edprojectfinal.interfaces.IComunicaFragments;
import com.example.edprojectfinal.sqlite.ConexionSQLiteHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrarClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarClienteFragment extends Fragment {

    View vista;
    Activity actividad;
    IComunicaFragments iComunicaFragments;
    EditText TXTRUC,TXTRS,TXTREPRESENTANTE,TXTTELEFONO,TXTEMAIL;
    Button BTNGUARDAR, BTNCANCELAR;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegistrarClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarClientesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarClienteFragment newInstance(String param1, String param2) {
        RegistrarClienteFragment fragment = new RegistrarClienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_registrar_cliente, container, false);
        TXTRUC=vista.findViewById(R.id.txtruc);
        TXTRS=vista.findViewById(R.id.txtrazon_social);
        TXTREPRESENTANTE=vista.findViewById(R.id.txtrepresentante);
        TXTTELEFONO=vista.findViewById(R.id.txttelefono);
        TXTEMAIL=vista.findViewById(R.id.txtemail);
        BTNGUARDAR=vista.findViewById(R.id.btnguardar);
        BTNCANCELAR=vista.findViewById(R.id.btncancelar);
        BTNGUARDAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar();
            }
        });
        BTNCANCELAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(vista).navigate(R.id.nav_clientes);
            }
        });
        return vista;
    }
    public void Guardar(){
        String ruc=TXTRUC.getText().toString();
        String rs=TXTRS.getText().toString();
        String repre=TXTREPRESENTANTE.getText().toString();
        String telf=TXTTELEFONO.getText().toString();
        String email=TXTEMAIL.getText().toString();

        if(ruc.length()==0) {
            TXTRUC.setError("Ingrese datos por favor!");
            TXTRUC.requestFocus();
        }else{
            if(rs.length()==0){
                TXTRS.setError("Ingrese datos por favor!");
                TXTRS.requestFocus();
            }else{
                if(repre.length()==0){
                    TXTREPRESENTANTE.setError("Ingrese datos por favor!");
                    TXTREPRESENTANTE.requestFocus();
                }else{
                    if(telf.length()==0){
                        TXTTELEFONO.setError("Ingrese datos por favor!");
                        TXTTELEFONO.requestFocus();
                    }else{
                        if(email.length()==0){
                            TXTEMAIL.setError("Ingrese datos por favor!");
                            TXTEMAIL.requestFocus();
                        }else{
                            ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,ClienteDAO.NOMBRE_BD,null,1);
                            SQLiteDatabase db=conn.getWritableDatabase();
                            ContentValues values= new ContentValues();
                            values.put(ClienteDAO.CAMPO_RUC,ruc);
                            values.put(ClienteDAO.CAMPO_RS,rs);
                            values.put(ClienteDAO.CAMPO_REPRE,repre);
                            values.put(ClienteDAO.CAMPO_TELF,telf);
                            values.put(ClienteDAO.CAMPO_EMAIL,email);

                            Long idResultante=db.insert(ClienteDAO.TABLA_CLIENTES,ClienteDAO.CAMPO_ID,values);
                            if(idResultante!=-1){
                                Toast.makeText(actividad, "El Cliente fue registrado con exito", Toast.LENGTH_LONG).show();
                                LimpiarCampos();
                            }else{
                                Toast.makeText(actividad, "No se pudo registrar al Cliente", Toast.LENGTH_LONG).show();
                            }
                            db.close();
                        }
                    }
                }
            }
        }
    }

    public void LimpiarCampos(){
        TXTRUC.setText("");
        TXTRS.setText("");
        TXTREPRESENTANTE.setText("");
        TXTTELEFONO.setText("");
        TXTEMAIL.setText("");
        TXTRUC.requestFocus();
    }
    public void onButtonPressed(Uri uri){
        if(mListener!=null){
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad= (Activity) context;
        }
        if (context instanceof OnFragmentInteractionListener){
            mListener= (OnFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()+ " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
        mListener=null;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}