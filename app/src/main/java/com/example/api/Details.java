package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView title = findViewById(R.id.txtTitle);
        final TextView description = findViewById(R.id.txtDescription);
        final TextView image = findViewById(R.id.txtImage);
        final Button back = findViewById(R.id.btnBack);

        Intent reportID = getIntent();
        String ID = reportID.getStringExtra(Login.EXTRA_MESSAGE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:3000/api/v1/denuncias/"+ID+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportService reportService = retrofit.create(ReportService.class);
        Call<Report> call = reportService.getByIdReport();

        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                title.setText(response.body().getTitle());
                description.setText(response.body().getDescription());
                image.setText(response.body().getImage_url());
            }
            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                System.out.println("Error en algo");
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla2 = new Intent(Details.this, Lista.class);
                startActivity(pantalla2);

            }
        });
    }
}
