package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView email = findViewById(R.id.email);
        final TextView password = findViewById(R.id.password);
        final Button add     = findViewById(R.id.btnAdd);
        final Button register = findViewById(R.id.btnRegister);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantalla = new Intent(Login.this, List.class);
                startActivity(pantalla);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla = new Intent(Login.this, Register.class);
                startActivity(pantalla);

            }
        });
    }
}
