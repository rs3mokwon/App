package com.example.rs3_snailtent.User;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("User_ID")
    String User_ID;

    @SerializedName("User_PW")
    String User_PW;

    public LoginData(String User_ID, String User_PW) {
        this.User_ID = User_ID;
        this.User_PW = User_PW;
    }
}
