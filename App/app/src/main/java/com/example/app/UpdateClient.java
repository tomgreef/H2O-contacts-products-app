package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdateClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);

        Button cancel = findViewById(R.id.cancel);

        if(cancel != null){
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(UpdateClient.this, "Cancel New Client", Toast.LENGTH_LONG).show();
                    cancelar(view);
                }
            });
        }

    }

    public void cancelar(android.view.View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}