package com.example.edprojectfinal.ui.clientes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edprojectfinal.R;
import com.example.edprojectfinal.interfaces.IComunicaFragments;
import com.example.edprojectfinal.sqlite.ConexionSQLiteHelper;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarClienteFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private int activarrecyclerview=0;

    Activity actividad;
    View vista;
    IComunicaFragments iComunicaFragments;
    RecyclerView recyclerClientes;
    EditText TXTREPRE,TXTTELF,TXTEMAIL;
    FloatingActionsMenu botonesFlotantes;
    FloatingActionButton bfConfirmar,bfActualizar,bfEliminar;

    ClienteBean objclientebean;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConsultarClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarClienteFragment newInstance(String param1, String param2) {
        ConsultarClienteFragment fragment = new ConsultarClienteFragment();
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
        vista= inflater.inflate(R.layout.fragment_consultar_cliente, container, false);
        recyclerClientes=vista.findViewById(R.id.recyclerClientes_listado);
        TXTREPRE=vista.findViewById(R.id.txtrepresentante);
        TXTTELF=vista.findViewById(R.id.txttelefono);
        TXTEMAIL=vista.findViewById(R.id.txtemail);
        botonesFlotantes=vista.findViewById(R.id.BotonesFlotantes);
        bfConfirmar=vista.findViewById(R.id.bfConfirmar);
        bfActualizar=vista.findViewById(R.id.bfActualizar);
        bfEliminar=vista.findViewById(R.id.bfEliminar);
        recyclerClientes.setLayoutManager(new LinearLayoutManager(this.actividad));
        recyclerClientes.setHasFixedSize(true);
        bfActualizar.setEnabled(false);
        bfEliminar.setEnabled(false);
        bfConfirmar.setEnabled(false);

        bfActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarCliente();
                botonesFlotantes.collapse();
            }
        });

        bfConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmarCliente();
                LlenarAdaptadorClientes();
                botonesFlotantes.collapse();
            }
        });

        bfEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEliminar().show();
                botonesFlotantes.collapse();
            }
        });

        LlenarAdaptadorClientes();

        return vista;
    }

    private void EliminarCliente() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,ClienteDAO.NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        int idResultante=db.delete(ClienteDAO.TABLA_CLIENTES,ClienteDAO.CAMPO_ID+"="+objclientebean.getIdcliente(),null);
        if(idResultante!=-1){
            Toast.makeText(actividad, "El Cliente se eliminó con exito", Toast.LENGTH_LONG).show();
            recyclerClientes.scrollToPosition(objclientebean.getIdcliente());
            ClienteDAO.consultarListaClientes(actividad);
            TXTREPRE.setText("");
            TXTTELF.setText("");
            TXTEMAIL.setText("");
            bfActualizar.setEnabled(false);
            bfEliminar.setEnabled(false);
            bfConfirmar.setEnabled(false);
        }else{
            Toast.makeText(actividad, "No se pudo eliminar al Cliente", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public AlertDialog dialogEliminar(){
        AlertDialog.Builder builder= new AlertDialog.Builder(actividad);
        builder.setTitle("Advertencia!").setMessage("Está seguro que desea eliminar a "+objclientebean.getRazon_social().toUpperCase())
        .setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
        .setPositiveButton("SI",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EliminarCliente();
                        LlenarAdaptadorClientes();
                    }
                });
        return builder.create();
    }

    private void ConfirmarCliente() {
        String repre=TXTREPRE.getText().toString();
        String telf=TXTTELF.getText().toString();
        String email=TXTEMAIL.getText().toString();

        if(repre.length()==0){
            TXTREPRE.setError("Ingrese datos por favor!");
            TXTREPRE.requestFocus();
        }else{
            if(telf.length()==0){
                TXTTELF.setError("Ingrese datos por favor!");
                TXTTELF.requestFocus();
            }else{
                if(email.length()==0){
                    TXTEMAIL.setError("Ingrese datos por favor!");
                    TXTEMAIL.requestFocus();
                }else{
                    ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,ClienteDAO.NOMBRE_BD,null,1);
                    SQLiteDatabase db=conn.getWritableDatabase();

                    ContentValues values= new ContentValues();
                    values.put(ClienteDAO.CAMPO_REPRE,repre);
                    values.put(ClienteDAO.CAMPO_TELF,telf);
                    values.put(ClienteDAO.CAMPO_EMAIL,email);

                    int idResultante=db.update(ClienteDAO.TABLA_CLIENTES,values,ClienteDAO.CAMPO_ID+"="+objclientebean.getIdcliente(),null);
                    if(idResultante!=-1){
                        Toast.makeText(actividad, "El Cliente fue actualizado con exito", Toast.LENGTH_LONG).show();
                        recyclerClientes.scrollToPosition(objclientebean.getIdcliente()-1);
                        ClienteDAO.consultarListaClientes(actividad);
                        TXTREPRE.setEnabled(false);
                        TXTTELF.setEnabled(false);
                        TXTEMAIL.setEnabled(false);
                        bfActualizar.setEnabled(true);
                        bfEliminar.setEnabled(true);
                        bfConfirmar.setEnabled(false);
                    }else{
                        Toast.makeText(actividad, "No se pudo actualizar al Cliente", Toast.LENGTH_LONG).show();
                    }
                    db.close();
                }
            }
        }
    }

    private void actualizarCliente() {
        TXTREPRE.setEnabled(true);
        TXTTELF.setEnabled(true);
        TXTEMAIL.setEnabled(true);
        bfEliminar.setEnabled(false);
        bfActualizar.setEnabled(false);
        bfConfirmar.setEnabled(true);
        activarrecyclerview=1;
    }

    private void LlenarAdaptadorClientes() {
        AdaptadorCliente miAdaptadorCliente=new AdaptadorCliente(ClienteDAO.listaCliente);
        miAdaptadorCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonesFlotantes.collapse();
                objclientebean=ClienteDAO.listaCliente.get(recyclerClientes.getChildAdapterPosition(v));
                TXTREPRE.setText(objclientebean.getRepresentante());
                TXTTELF.setText(objclientebean.getTelefono());
                TXTEMAIL.setText(objclientebean.getEmail());
                if(activarrecyclerview==0) {
                    bfActualizar.setEnabled(true);
                    bfEliminar.setEnabled(true);
                }
            }
        });
        recyclerClientes.setAdapter(miAdaptadorCliente);
    }

    public void onButtonPressed(Uri uri){
        if(mListener!=null){
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad= (Activity) context;
            iComunicaFragments= (IComunicaFragments) this.actividad;
        }
        if(context instanceof OnFragmentInteractionListener){
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