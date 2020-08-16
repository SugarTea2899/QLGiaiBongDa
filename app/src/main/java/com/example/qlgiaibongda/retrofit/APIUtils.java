package com.example.qlgiaibongda.retrofit;

public class APIUtils {
    public static final String BASE_URL = "http://192.168.0.105:3000/";

    public static DataClient getData(){
        return RetrofitClient.getClient(BASE_URL).create(DataClient.class);
    }
}
