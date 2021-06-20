package com.example.rs3_snailtent.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoinData {
    @Expose
    @SerializedName("User_ID") private  String User_ID;

    @Expose
    @SerializedName("User_PW") private  String User_PW;

    @Expose
    @SerializedName("User_NAME") private  String User_NAME;

    @Expose
    @SerializedName("User_SEX") private  String User_SEX;

    @Expose
    @SerializedName("User_PHONE") private  String User_PHONE;

    @Expose
    @SerializedName("User_EMAIL") private  String User_EMAIL;

    @Expose
    @SerializedName("result") private Boolean result;

    @Expose
    @SerializedName("msg") private String msg;

    @Expose
    @SerializedName("User_LATITUDE") private String User_LATITUDE;

    @Expose
    @SerializedName("User_LONGITUDE") private String User_LONGITUDE;

    public String getUser_ID() {
        return User_ID;
    }
    public void setUser_ID(String User_ID) {
        this.User_ID = User_ID;
    }

    public String getUser_PW() { return User_PW; }
    public void setUser_PW(String User_PW) { this.User_PW = User_PW; }

    public String getUser_NAME() { return User_NAME; }
    public void setUser_NAME(String User_NAME) { this.User_NAME = User_NAME; }

    public String getUser_SEX() { return User_SEX; }
    public void setUser_SEX(String User_SEX) { this.User_SEX = User_SEX; }

    public String getUser_PHONE() { return User_PHONE; }
    public void setUser_PHONE(String User_PHONE) { this.User_PHONE = User_PHONE; }

    public String getUser_EMAIL() { return User_EMAIL; }
    public void setUser_EMAIL(String User_EMAIL) { this.User_EMAIL = User_EMAIL; }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getUser_LATITUDE() { return User_LATITUDE; }
    public void setUser_LATITUDE(String User_LATITUDE){ this.User_LATITUDE = User_LATITUDE; }

    public String getUser_LONGITUDE() { return  User_LONGITUDE; }
    public void setUser_LONGITUDE(String User_LONGITUDE) {this.User_LONGITUDE = User_LONGITUDE; }
}
