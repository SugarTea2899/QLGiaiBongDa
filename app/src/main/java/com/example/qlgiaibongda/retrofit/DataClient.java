package com.example.qlgiaibongda.retrofit;

import com.example.qlgiaibongda.model.Coach;
import com.example.qlgiaibongda.model.Match;
import com.example.qlgiaibongda.model.MatchStatDetails;
import com.example.qlgiaibongda.model.Player;
import com.example.qlgiaibongda.model.Rank;
import com.example.qlgiaibongda.model.Referee;
import com.example.qlgiaibongda.model.Team;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DataClient {

    @FormUrlEncoded
    @POST("account/authenticate")
    Call<ResponseBody> login(@Field("username") String userName, @Field("password") String pass);

    @GET("player/teammate")
    Call<List<Player>> getListPlayerTeam(@Query("teamId") String teamId);

    @GET("team/list")
    Call<List<Team>> getTeamList();

    @FormUrlEncoded
    @POST("player/add")
    Call<ResponseBody> addPlayer(@Field("name") String name, @Field("dob") String dob, @Field("type") int type,
                                 @Field("nationality") String nationality, @Field("teamId") String teamId, @Field("number") int playerNumber);


    @FormUrlEncoded
    @POST("player/update")
    Call<ResponseBody> updatePlayer(@Field("playerId") String playerId, @Field("name") String name, @Field("dob") String dob, @Field("type") int type,
                                    @Field("nationality") String nationality, @Field("teamId") String teamId, @Field("number") int playerNumber);

    @Multipart
    @POST("player/upload-avatar")
    Call<ResponseBody> uploadPlayerAvatar(@Query("id") String id ,@Part MultipartBody.Part avatar);

    @GET("player/info")
    Call<Player> getPlayerInfo(@Query("playerId") String playerId);


    @GET("player/search?")
    Call<ArrayList<Player>> getListSearchPlayer(@Query("name") String name);

    @GET("team/getInfo?")
    Call<Team> getInfoTeam(@Query("teamId") String teamId);


    @GET("team/search?")
    Call<ArrayList<Team>> getListSearchTeam(@Query("name") String name);

    @GET("coach/info?")
    Call<Coach> getInfoCoach(@Query("coachId") String coachId);

    @GET("coach/search?")
    Call<ArrayList<Coach>> getListSearchCoach(@Query("name") String name);

    @GET("match/history?")
    Call<List<Match>> getListMatchOfTeam(@Query("team") String team, @Query("stateMatch") Integer state);

    @GET("match/detail")
    Call<List<MatchStatDetails>> getMatchDetails(@Query("matchId") String matchId);

    @GET("match/search?")
    Call<ArrayList<Match>> getListSearchMatch(@Query("name") String name);

    @GET("match/recent-round")
    Call<Match> getCurrentRound();

    @GET("match/recent-match")
    Call<ArrayList<Match>> getListCurrentMatch(@Query("round") int round);


    @GET("referee/search?")
    Call<ArrayList<Referee>> getListSearchReferee(@Query("name") String name);

    @GET("match/get-match-info")
    Call<Match> getMatchInfo(@Query("matchId") String matchId);

    @GET("rank/current")
    Call<ArrayList<Rank>> getCurrentRank(@Query("season") int season);

}
