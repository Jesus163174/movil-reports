package com.example.api;

public class Connection {
    private Connection(){};
    public static  String API_URL = "http://192.168.0.5:3000/api/v1/";

    public static ReportService getServiceRemote(){
        return Client.getClient(API_URL).create(ReportService.class);
    }
    public static UserService getServiceRemotee(){
        return Client.getClient(API_URL).create(UserService.class);
    }
}
