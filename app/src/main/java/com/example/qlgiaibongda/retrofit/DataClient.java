package com.example.qlgiaibongda.retrofit;


import com.example.qlgiaibongda.model.Coach;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.MatchStatDetails;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.model.Team;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataClient {

    @FormUrlEncoded
    @POST("account/authenticate")
    Call<ResponseBody> login(@Field("username") String userName, @Field("password") String pass);

    @GET("player/teammate?")
    Call<List<Player>> getListPlayerTeam(@Query("teamId") String teamId);

    @GET("team/getInfo?")
    Call<Team> getInfoTeam(@Query("teamId") String teamId);

    @GET("coach/info?")
    Call<Coach> getInfoCoach(@Query("coachId") String coachId);

    @GET("match/history?")
    Call<List<Match>> getListMatchOfTeam(@Query("team") String team, @Query("stateMatch") Integer state);

    @GET("match/detail?")
    Call<List<MatchStatDetails>> getMatchDetails(@Query("matchId") String matchId);
}
