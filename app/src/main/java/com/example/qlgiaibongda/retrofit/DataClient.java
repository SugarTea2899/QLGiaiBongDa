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

    @FormUrlEncoded
    @POST("player/remove")
    Call<ResponseBody> removePlayer(@Field("playerId") String playerId);

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

    @FormUrlEncoded
    @POST("team/add")
    Call<ResponseBody> addTeam(@Field("name") String name, @Field("shortName") String shortName,
                               @Field("stadium") String stadium, @Field("sponsor") String donors);

    @FormUrlEncoded
    @POST("team/update")
    Call<ResponseBody> updateTeam(@Field("teamId") String teamId, @Field("name") String name, @Field("shortName") String shortName,
                                  @Field("stadium") String stadium, @Field("sponsor") String donors, @Field("coachId") String coachId,
                                  @Field("captainId") String captainId);



    @Multipart
    @POST("team/upload-logo")
    Call<ResponseBody> uploadTeamLogo(@Query("id") String id ,@Part MultipartBody.Part logo);

    @GET("coach/info?")
    Call<Coach> getInfoCoach(@Query("coachId") String coachId);

    @GET("coach/search?")
    Call<ArrayList<Coach>> getListSearchCoach(@Query("name") String name);

    @GET("coach/list")
    Call<List<Coach>> getCoachList();

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

    @GET("referee/list")
    Call<List<Referee>> getRefereeList();

    @GET("match/get-match-info")
    Call<Match> getMatchInfo(@Query("matchId") String matchId);

    @FormUrlEncoded
    @POST("match/add")
    Call<ResponseBody> addMatch(@Field("homeTeam") String homeTeam, @Field("guestTeam") String guestTeam, @Field("dateStart") long dateStart,
                                @Field("stadium") String stadium, @Field("refereeId") String refereeId, @Field("round") int round);

    @FormUrlEncoded
    @POST("match/update")
    Call<ResponseBody> updateMatch(@Field("matchId") String matchId, @Field("homeTeam") String homeTeam, @Field("guestTeam") String guestTeam, @Field("dateStart") long dateStart,
                                   @Field("stadium") String stadium, @Field("refereeId") String refereeId, @Field("round") int round);

    @GET("rank/current")
    Call<ArrayList<Rank>> getCurrentRank(@Query("season") int season);


}
