package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity{

    UserService userService;
    DatabaseHelper dbHandler = new DatabaseHelper(Login.this);
    public static final String EXTRA_MESSAGE = "app.activities.MESSAGE";
    public static String IDUSER = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView email = findViewById(R.id.email);
        final TextView password = findViewById(R.id.password);
        final Button add     = findViewById(R.id.btnAdd);
        final Button register = findViewById(R.id.btnRegister);

        userService = Connection.getServiceRemotee();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                login(user);
                System.out.println("USERID: "+IDUSER);
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
    public void login(final User user){
        Call<List<User>> call = userService.loginAuth(user);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                String userID = "";
                for(User user : response.body()){
                    userID = user.getId();
                }

                System.out.println("-------------------");
                System.out.println("ID: "+userID);
                System.out.println("-------------------");

                IDUSER = userID;

                System.out.println("Insertando usuario");
                dbHandler.insertUser(userID);
                System.out.println("----------------------");

                System.out.println("Trayendo  los datos de sqlite");
                ArrayList<String> lista = dbHandler.getDataUser(0);
                System.out.println("------------------------------");

                System.out.println("Registrados: "+lista.size());
                System.out.println("---------------------------");

                if(response.isSuccessful()){
                    Toast.makeText(Login.this, "Logeado correctamente", Toast.LENGTH_SHORT).show();
                    Intent pantalla = new Intent(Login.this, Lista.class);
                    pantalla.putExtra(EXTRA_MESSAGE,IDUSER);
                    startActivity(pantalla);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println(t.getMessage());
                Log.e("Error: ",t.getMessage());

            }
        });
    }
}
