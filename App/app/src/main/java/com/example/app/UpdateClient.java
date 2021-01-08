package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.fragments.Clases.ClientContent;

public class UpdateClient extends AppCompatActivity {

    //private AdminDB_Manager db = new AdminDB_Manager(this);
    private AdminDB db_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);
        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("index");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Button update = findViewById(R.id.update);
        Button delete = findViewById(R.id.delete);
        Button cancel = findViewById(R.id.cancel);

        ClientContent.Client client = ClientContent.ITEMS.get(index);

        //Cliente
        EditText nombre = findViewById(R.id.editFullName);
        EditText phone = findViewById(R.id.editPhone);

        nombre.setText(client.name);
        phone.setText(client.phone);

        //Abrir la BBDD
        //db.open(); //Ya se ha abierto antes -> se hace uso de la instancia en SingletonMap
        db_map = (AdminDB) SingletonMap.getInstance().get(AdminDB_Manager.H2O);

        //Actualizar/Editar un cliente existente en la BBDD
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = nombre.getText().toString();
                String p = phone.getText().toString();

                if (client.name.equals(n) && client.phone.equals(p)) {
                    Toast.makeText(UpdateClient.this, R.string.no_changes, Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase bd = db_map.getWritableDatabase();

                    ContentValues cv = new ContentValues();
                    cv.put("id", client.id);
                    cv.put("nombre", n);
                    cv.put("phone", p);

                    if (bd.update("clientes", cv, "id = ?", new String[]{client.id}) == 1) {
                        Toast.makeText(UpdateClient.this, R.string.client_updated, Toast.LENGTH_LONG).show();
                        ClientContent.ITEM_MAP.clear();
                        ClientContent.ITEMS.clear();
                    } else {
                        Toast.makeText(UpdateClient.this, R.string.error, Toast.LENGTH_LONG).show();
                    }
                    cancelar(view);
                }
            }
        });

        //Eliminar un cliente existente en la BBDD
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle(R.string.warning).setMessage(R.string.removeMessage)
                        .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SQLiteDatabase bd = db_map.getWritableDatabase();

                                if (bd.delete("clientes", "id" + "=" + client.id, null) == 1) {
                                    Toast.makeText(UpdateClient.this, R.string.client_deleted, Toast.LENGTH_LONG).show();
                                    cancelar(view);
                                    ClientContent.ITEM_MAP.clear();
                                    ClientContent.ITEMS.clear();
                                } else {
                                    Toast.makeText(UpdateClient.this, "ERROR", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
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