package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView title = findViewById(R.id.txtTitle);
        final TextView description = findViewById(R.id.txtDescription);
        final TextView image = findViewById(R.id.txtImage);
        final Button back = findViewById(R.id.btnBack);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla2 = new Intent(Details.this, List.class);
                startActivity(pantalla2);

            }
        });
    }
}
