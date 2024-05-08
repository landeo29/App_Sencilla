package com.example.edprojectfinal.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.edprojectfinal.ui.clientes.ClienteDAO;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClienteDAO.CREAR_TABLA_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ClienteDAO.TABLA_CLIENTES);
        onCreate(db);
    }
}
