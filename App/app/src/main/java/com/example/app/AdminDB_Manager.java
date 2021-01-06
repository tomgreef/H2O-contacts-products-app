package com.example.app;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AdminDB_Manager {

    private AdminDB h2odb;
    private Context context;
    private SQLiteDatabase db;

    public AdminDB_Manager(Context c){
        context = c;
    }

    public AdminDB_Manager open() throws SQLException {
        h2odb = new AdminDB(context, "h2o", null, 1);
        db = h2odb.getWritableDatabase();

        //SingletonMap.getInstance().put("H2O", h2odb);

        return this;
    }

    public void close(){
        h2odb.close();
    }

    //INSERTAR EN DB
    public void agregarProductos(Integer id, String nombre, Integer precio){
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("nombre", nombre);
        cv.put("precio", precio);
        db.insert("productos", null, cv);
    }

    public void agregarClientes(String nombre, String phone){
        ContentValues cv = new ContentValues();
        //cv.put("id", id);
        cv.put("nombre", nombre);
        cv.put("phone", phone);
        db.insert("clientes", null, cv);
    }

    public void agregarPedidos(Integer id, String nombre, String fecha){
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("nombre", nombre);
        cv.put("fecha", fecha);
        db.insert("pedidos", null, cv);
    }

    //CONSULTAR EN DB
    public Cursor listarProductos(){
        Cursor c = db.query("productos", null, null, null, null, null, null);
        return c;
    }

    public Cursor listaClientes(){
        Cursor c = db.query("clientes", null, null, null, null, null, null);
        return c;
    }

    public Cursor listaPedidos(){
        Cursor c = db.query("pedidos", null, null, null, null, null, null);

        return c;
    }

    //BORRAR DE DB
    public int borrarProductos(Integer id){
        return db.delete("productos", "id" + "=" + id, null);
    }

    public int borrarClientes(String id){
        return db.delete("clientes", "id" + "=" + id, null);
    }

    public int borrarPedidos(Integer id){
        return db.delete("pedidos", "id" + "=" + id, null);
    }

    //Actualizar DB
    public int editarClientes(String id, String name, String phone){
        ContentValues content = new ContentValues();
        content.put("id", id);
        content.put("nombre", name);
        content.put("phone", phone);
        return db.update("clientes", content, "id = ?", new String[]{id} );
    }
}

