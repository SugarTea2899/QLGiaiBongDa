package com.example.qlgiaibongda.retrofit;

public class APIUtils {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    public static DataClient getData(){
        return RetrofitClient.getClient(BASE_URL).create(DataClient.class);
    }
}
