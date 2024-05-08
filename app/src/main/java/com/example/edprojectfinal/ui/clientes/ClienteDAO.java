package com.example.edprojectfinal.ui.clientes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edprojectfinal.sqlite.ConexionSQLiteHelper;

import java.util.ArrayList;

public class ClienteDAO {

    public static ArrayList<ClienteBean>listaCliente=null;

    public static final String NOMBRE_BD="DAM_BD.db";
    public static final String TABLA_CLIENTES="Clientes";
    public static final String CAMPO_ID="idcliente";
    public static final String CAMPO_RUC="ruc";
    public static final String CAMPO_RS="razon_social";
    public static final String CAMPO_REPRE="representante";
    public static final String CAMPO_TELF="telefono";
    public static final String CAMPO_EMAIL="email";

    public static final String CREAR_TABLA_CLIENTES="CREATE TABLE "+TABLA_CLIENTES+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_RUC+" TEXT NOT NULL, "
            +CAMPO_RS+" TEXT NOT NULL, "
            +CAMPO_REPRE+" TEXT NOT NULL, "
            +CAMPO_TELF+" TEXT NOT NULL, "
            +CAMPO_EMAIL+" TEXT NOT NULL)";

    public static void consultarListaClientes(Activity actividad){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        ClienteBean objclientebean=null;
        listaCliente=new ArrayList<ClienteBean>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLA_CLIENTES,null);

        while (cursor.moveToNext()){
            objclientebean=new ClienteBean();
            objclientebean.setIdcliente(cursor.getInt(0));
            objclientebean.setRuc(cursor.getString(1));
            objclientebean.setRazon_social(cursor.getString(2));
            objclientebean.setRepresentante(cursor.getString(3));
            objclientebean.setTelefono(cursor.getString(4));
            objclientebean.setEmail(cursor.getString(5));

            listaCliente.add(objclientebean);
        }
        db.close();
    }

}
