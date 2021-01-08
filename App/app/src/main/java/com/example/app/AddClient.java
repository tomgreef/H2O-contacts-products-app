package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.fragments.Clases.ClientContent;

public class AddClient extends AppCompatActivity {

    //private AdminDB_Manager db = new AdminDB_Manager(this);
    private AdminDB db_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        Button cancel = findViewById(R.id.cancel);
        Button add = findViewById(R.id.save);

        //Abrir la BBDD
        //db.open(); //Ya se ha abierto antes -> se hace uso de la instancia en SingletonMap
        db_map = (AdminDB) SingletonMap.getInstance().get(AdminDB_Manager.H2O);

        //Añadir un nuevo cliente en la BBDD
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombre = findViewById(R.id.addFullName);
                EditText phone = findViewById(R.id.addPhone);

                String n = nombre.getText().toString();
                String p = phone.getText().toString();

                if(n.isEmpty() || p.isEmpty()) {
                    if (!n.isEmpty()){
                        Toast.makeText(AddClient.this, R.string.no_phone, Toast.LENGTH_LONG).show();
                    } else if (!p.isEmpty()){
                        Toast.makeText(AddClient.this, R.string.no_name, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddClient.this, R.string.no_changes , Toast.LENGTH_LONG).show();
                    }
                } else {
                    SQLiteDatabase bd = db_map.getWritableDatabase();

                    ContentValues cv = new ContentValues();

                    cv.put("nombre", n);
                    cv.put("phone", p);

                    bd.insert("clientes", null, cv);
                    //db.insert("clientes", null, cv); //Comentar para usar SingletonMap

                    Toast.makeText(AddClient.this, R.string.client_added, Toast.LENGTH_LONG).show();
                    cancelar(view);

                    ClientContent.ITEM_MAP.clear();
                    ClientContent.ITEMS.clear();
                }
            }
        });

        //Cancelar actividad actual, volver a la anterior
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar(view);
            }
        });
    }

    public void cancelar(android.view.View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}