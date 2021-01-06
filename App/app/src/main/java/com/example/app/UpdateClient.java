package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.fragments.Clases.ClientContent;

public class UpdateClient extends AppCompatActivity {

    private AdminDB_Manager db = new AdminDB_Manager(this);

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
        EditText nombre = findViewById(R.id.ClientName);
        EditText phone = findViewById(R.id.ClientPhone);

        nombre.setText(client.name);
        phone.setText(client.phone);

        //abrir BBDD
        db.open();

        //actualizar cliente BBDD
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = nombre.getText().toString();
                String p = phone.getText().toString();

                if (client.name.equals(n) && client.phone.equals(p)) {
                    Toast.makeText(UpdateClient.this, R.string.no_changes, Toast.LENGTH_LONG).show();
                } else {
                    if (db.editarClientes(client.id, n, p) == 1) {
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

        //borrar cliente BBDD
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle(R.string.warning).setMessage(R.string.removeMessage)
                        .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (db.borrarClientes(client.id) == 1) {
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