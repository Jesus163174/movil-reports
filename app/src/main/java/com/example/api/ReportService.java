package com.example.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReportService {


    @GET("all")
    Call<List<Report>> getReports();

    @POST("denuncias/")
    Call<Report> addReport(@Body Report report);

    @GET("show")
    Call<Report> getByIdReport();

    @PUT("denuncias/{id}")
    Call<Report> updateReport(@Path("id") int id, @Body Report report);

    @PUT("denuncias/{id}/delete")
    Call<Report> deleteReport(@Path("id") int id, @Body Report report);
}
