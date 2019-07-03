package com.example.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @GET("users/")
    Call<List<User>> getUsers();

    @POST("users/")
    Call<User> addUser(@Body User user);

    @POST("auth/login")
    Call<List<User>> loginAuth(@Body User user);

    @GET("users/{id}")
    Call<User> getByIdUser(@Path("id") int id);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @PUT("users/{id}/delete")
    Call<User> deleteUser(@Path("id") int id, @Body User user);
}
