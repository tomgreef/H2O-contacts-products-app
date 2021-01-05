package com.example.app;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDB extends SQLiteOpenHelper {

    private static final int VERSION_BD = 1;
    private static final String BD = "h5o.db";

    //CREACIÓN TABLAS
    private static final String TABLA_PRODUCTOS = "productos";
    private static final String TABLA_CLIENTES = "clientes";
    private static final String TABLA_PEDIDOS = "pedidos";

    public AdminDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, BD, factory, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_1 = "CREATE TABLE " + TABLA_PRODUCTOS + "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " + "precio" +
                " INTEGER, " + "nombre" + " TEXT NOT NULL);";

        db.execSQL(query_1);

        String query_2 = "CREATE TABLE " + TABLA_CLIENTES + "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " + "nombre" +
                " TEXT, " + "phone" + " TEXT NOT NULL);";

        db.execSQL(query_2);

        String query_3 = "CREATE TABLE " + TABLA_PEDIDOS + "(" + "id" + " INTEGER PRIMARY KEY, " + "fecha" +
                " DATE);";

        db.execSQL(query_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PRODUCTOS);
        db.execSQL(TABLA_PRODUCTOS);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CLIENTES);
        db.execSQL(TABLA_CLIENTES);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PEDIDOS);
        db.execSQL(TABLA_PEDIDOS);
        onCreate(db);
    }
}
