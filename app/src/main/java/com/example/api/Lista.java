package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lista extends AppCompatActivity implements DenunciaAdapter.AdapterButtonsListener{

    ReportService reportService;
    /*ListView list;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    Button btnEliminar;
    ArrayList<String> lista;*/

    private ListView listView;
    private DenunciaAdapter denunciaAdapter;
    private  ArrayList<Report> denuncia;
    public static final String EXTRA_MESSAGE = "app.activities.MESSAGE";

    public static String USERID = "";
    DatabaseHelper db = new DatabaseHelper(Lista.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //OBTENER ID DEL USUARIO QUE TRAIGO DEL LOGIN
        Intent login = getIntent();
        USERID = login.getStringExtra(Login.EXTRA_MESSAGE);

        System.out.println("USERID: "+USERID);

        listView = findViewById(R.id.ViewList);
        denuncia = new ArrayList<>();
        denunciaAdapter = new DenunciaAdapter(getApplicationContext(), denuncia);
        denunciaAdapter.setListener(this);
        listView.setAdapter(denunciaAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:3000/api/v1/denuncias/"+USERID+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportService reportService = retrofit.create(ReportService.class);

        Call<List<Report>> call = reportService.getReports();
        call.enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                for(Report report : response.body()) {
                    denuncia.add(new Report(report.getId(),report.getTitle(), report.getDescription(), report.getImage_url()));
                }
            }
            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                System.out.println("Error en algo");
            }
        });

        /*final Button details = findViewById(R.id.btnDetails);
        final FloatingActionButton addItem = findViewById(R.id.fab);
        final Button close = findViewById(R.id.btnCerrar);
        final TextView txtEmail = findViewById(R.id.txtEmail);
        final TextView txtName  = findViewById(R.id.txtName);
        final DatabaseHelper dbHandler = new DatabaseHelper(Lista.this);


        btnEliminar = findViewById(R.id.btnEliminar);


        ArrayAdapter arrayAdapter = new ArrayAdapter(Lista.this,R.layout.activity_form_denuncias,R.id.listTitle,titles);
        getReports(dbHandler);

        list = findViewById(R.id.simpleList);

        list.setAdapter(arrayAdapter);*/



        /*addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla2 = new Intent(Lista.this, Form.class);
                startActivity(pantalla2);

            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla2 = new Intent(Lista.this, Details.class);
                startActivity(pantalla2);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = dbHandler.getDataUser(0);

                System.out.println("Cerrando sesión");
                System.out.println("---------------------");

                System.out.println("registrados: "+lista.size());
                System.out.println("---------------------------");

                System.out.println("eliminando usuario de sqlite");
                dbHandler.deleteDataUser(lista.get(0));
                System.out.println("---------------------------");

                Intent pantalla2 = new Intent(Lista.this, Login.class);
                startActivity(pantalla2);
            }
        });*/
    }


    /*private void getReports(DatabaseHelper db){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.13:3000/api/v1/denuncias/"+USERID+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ReportService reportService = retrofit.create(ReportService.class);
        Call<List<Report>> call = reportService.getReports();
        call.enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                for(Report report : response.body()) {
                    //System.out.println("gola bb22");
                    titles.add(report.getTitle());
                    System.out.println(report.getTitle());
                }
                //arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {

            }
        });

    }*/


    @Override
    public void onButtonEditClickListener(int position, Report denuncia) {
        /*String reportID = denuncia.getId();
        System.out.println("ID Report: "+reportID);
        /*Intent pantalla2 = new Intent(Lista.this, Details.class);
        pantalla2.putExtra(EXTRA_MESSAGE,reportID);
        startActivity(pantalla2);*/
        System.out.println("-------------------------------------");
        System.out.println("ID: "+denuncia.getId());
        System.out.println("Title: "+denuncia.getTitle());
        System.out.println("Description: "+denuncia.getDescription());
        System.out.println("--------------------------------------");

        String reportID = denuncia.getId();
        System.out.println("ID Report: "+reportID);
        Intent pantalla2 = new Intent(Lista.this, Details.class);
        pantalla2.putExtra(EXTRA_MESSAGE,reportID);
        startActivity(pantalla2);
    }

    @Override
    public void onButtonDeleteClickListener(int position, Report denuncia) {

    }
}



