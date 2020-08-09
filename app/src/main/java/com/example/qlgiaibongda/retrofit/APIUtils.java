package com.example.qlgiaibongda.retrofit;

public class APIUtils {
    public static final String BASE_URL = "";

    public static DataClient getData(){
        return RetrofitClient.getClient(BASE_URL).create(DataClient.class);
    }
}
