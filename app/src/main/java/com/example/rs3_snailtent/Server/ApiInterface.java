package com.example.rs3_snailtent.Server;

import com.example.rs3_snailtent.User.JoinData;
import com.example.rs3_snailtent.User.LoginData;
import com.example.rs3_snailtent.User.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/member/join")
    Call<JoinData> saveUser(
            @Field("User_ID") String User_ID,
            @Field("User_PW") String User_PW,
            @Field("User_NAME") String User_NAME,
            @Field("User_SEX") String User_SEX,
            @Field("User_PHONE") String User_PHONE,
            @Field("User_EMAIL") String User_EMAIL
    );

    @FormUrlEncoded
    @POST("/member/checkId")
    Call<JoinData> checkUser (
            @Field("User_ID") String User_ID

    );

    @FormUrlEncoded
    @POST("/camera/detection")
    Call<JoinData> detection (
            @Field("User_ID") String User_ID

    );

    @FormUrlEncoded
    @POST("/camera/face")
    Call<JoinData> face (
            @Field("User_ID") String User_ID

    );

    @FormUrlEncoded
    @POST("/spot/place")
    Call<JoinData> GPS (
            @Field("User_ID") String User_ID,
            @Field("User_LATITUDE") String User_LATITUDE,
            @Field("User_LONGITUDE") String User_LONGITUDE

    );

    @POST("/member/login")
    Call<LoginResponse> getLogin(@Body LoginData data);

}
