package com.example.qlgiaibongda.retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient {

    @FormUrlEncoded
    @POST("account/authenticate")
    Call<ResponseBody> login(@Field("username") String userName, @Field("password") String pass);
}
