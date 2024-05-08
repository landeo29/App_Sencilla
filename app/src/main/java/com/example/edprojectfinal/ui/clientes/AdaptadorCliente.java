package com.example.edprojectfinal.ui.clientes;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edprojectfinal.R;

import java.util.List;

public class AdaptadorCliente extends RecyclerView.Adapter<AdaptadorCliente.ViewHolderCliente> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<ClienteBean> listaCliente;
    View vista;

    public AdaptadorCliente(List<ClienteBean> listaCliente) {
        this.listaCliente = listaCliente;
    }

    @NonNull
    @Override
    public ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        vista= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_cliente,viewGroup,false);
        vista.setOnClickListener(this);
        return new ViewHolderCliente(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCliente viewHolderCliente, int i) {
        viewHolderCliente.txtruc.setText(listaCliente.get(i).getRuc());
        viewHolderCliente.txtrs.setText(listaCliente.get(i).getRazon_social());
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listaCliente.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {
        TextView txtruc;
        TextView txtrs;

        public ViewHolderCliente(@NonNull View itemView){
            super(itemView);
            txtruc=itemView.findViewById(R.id.lblruc);
            txtrs=itemView.findViewById(R.id.lblrs);
        }
    }

}
