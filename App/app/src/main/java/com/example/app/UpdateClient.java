package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateClient extends AppCompatActivity {

    private AdminDB_Manager db = new AdminDB_Manager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);
        Button update = findViewById(R.id.update);
        Button delete = findViewById(R.id.delete);
        Button cancel = findViewById(R.id.cancel);

        //abrir BBDD
        db.open();

        //añadir cliente BBDD
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nombre = findViewById(R.id.editTextTextPersonName);
                EditText phone = findViewById(R.id.editTextPhone);

                db.agregarClientes(nombre.getText().toString(), phone.getText().toString());

                Toast.makeText(UpdateClient.this, "Client added", Toast.LENGTH_LONG).show();
                cancelar(view);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nombre = findViewById(R.id.editTextTextPersonName);
                EditText phone = findViewById(R.id.editTextPhone);

                //int id = db.getClient(nombre, phone);
                //db.borrarClientes(id);

                Toast.makeText(UpdateClient.this, "Client added", Toast.LENGTH_LONG).show();
                cancelar(view);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateClient.this, "Cancel New Client", Toast.LENGTH_LONG).show();
                cancelar(view);
            }
        });


    }

    public void cancelar(android.view.View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}