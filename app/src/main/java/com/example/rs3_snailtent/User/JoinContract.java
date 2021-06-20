package com.example.rs3_snailtent.User;

public class JoinContract {
    public interface View {
        void onToastMessage(String message);
        void showProgress();
        void hideProgress();
        void blockUserID();

    }
    interface Presenter{
        void requestCheckID(String User_ID);
        void requestSignup(String User_ID, String User_PW, String User_NAME, String User_SEX, String User_PHONE, String User_EMAIL);
        void requestDetection(String User_ID);
        void requestFace(String User_ID);
        void requestGPS(String User_ID, String User_LATITUDE, String User_LONGITUDE);

    }
}
