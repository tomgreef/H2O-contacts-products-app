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

    static String H2O = "H2O"; //Variable estatica para el SingletonMap

    public AdminDB_Manager(Context c){
        context = c;
    }

    public AdminDB_Manager open() throws SQLException {
        h2odb = new AdminDB(context, "h2o", null, 1);
        db = h2odb.getWritableDatabase();

       SingletonMap.getInstance().put(H2O, h2odb); //Se guarda la instancia a compartir

        return this;
    }

    public void close(){
        h2odb.close();
    }

    //CRUD - AGREGAR (CREATE)
    public void agregarProductos(String nombre, Integer precio){
        ContentValues cv = new ContentValues();

        cv.put("nombre", nombre);
        cv.put("precio", precio);

        db.insert("productos", null, cv);
    }

    public void agregarClientes(String nombre, String phone){
        ContentValues cv = new ContentValues();

        cv.put("nombre", nombre);
        cv.put("phone", phone);

        db.insert("clientes", null, cv);
    }

    public void agregarPedidos(String fecha){
        ContentValues cv = new ContentValues();
        //cv.put("id", id);
        cv.put("fecha", fecha);

        db.insert("pedidos", null, cv);
    }

    //CRUD - LISTAR (READ)
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

    //CRUD - BORRAR (DELETE)
    public int borrarProductos(Integer id){
        return db.delete("productos", "id" + "=" + id, null);
    }

    public int borrarClientes(String id){
        return db.delete("clientes", "id" + "=" + id, null);
    }

    public int borrarPedidos(Integer id){
        return db.delete("pedidos", "id" + "=" + id, null);
    }

    //CRUD - EDITAR (UPDATE)
    public int editarClientes(String id, String name, String phone){
        ContentValues content = new ContentValues();
        content.put("id", id);
        content.put("nombre", name);
        content.put("phone", phone);
        return db.update("clientes", content, "id = ?", new String[]{id} );
    }

    //Filtrar DB
    public Cursor contactoByNombre(String nombre){
        String[] query = {nombre + "%"};
        String[] campos = {"id", "nombre", "phone"};
        Cursor c  = db.query("clientes", campos, "nombre" + " like ?", query, null, null, null);
        return c;
    }
}

