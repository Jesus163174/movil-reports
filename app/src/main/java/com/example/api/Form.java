package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form extends AppCompatActivity {

    ReportService reportService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final EditText title = findViewById(R.id.txttitle);
        final EditText description = findViewById(R.id.description);
        final EditText image = findViewById(R.id.txtimage);
        final Button add = findViewById(R.id.btnAddReport);

        reportService = Connection.getServiceRemote();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Report report = new Report();
                report.setTitle(title.getText().toString());
                report.setDescription(description.getText().toString());
                report.setImage_url(image.getText().toString());
                addReport(report);
                Intent pantalla2 = new Intent(Form.this, List.class);
                startActivity(pantalla2);

            }
        });


    }

    private void addReport(Report r) {
        Call<Report> call = reportService.addReport(r);

        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                System.out.println(response);
                if(response.isSuccessful()){
                    Toast.makeText(Form.this, "Agregado Correctamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                System.out.println(t.getMessage());
                Log.e("Error: ",t.getMessage());

            }
        });
    }
}
