package com.example.api;

public class Connection {
    private Connection(){};
    public static  String API_URL = "http://127.0.0.1:3000/api/v1/";

    public static ReportService getServiceRemote(){
        return Client.getClient(API_URL).create(ReportService.class);
    }
}
