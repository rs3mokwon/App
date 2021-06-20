package com.example.rs3_snailtent.User;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("ID")
    private int ID;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getID() {
        return ID;
    }
}
