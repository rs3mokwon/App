package com.example.rs3_snailtent.User;

import android.util.Log;
import android.widget.Toast;

import com.example.rs3_snailtent.Server.ApiInterface;
import com.example.rs3_snailtent.Server.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinPresenter implements JoinContract.Presenter{

    JoinContract.View view;
    public JoinPresenter(JoinContract.View view) {
        this.view = view;
    }

    @Override
    public void requestFace(String User_ID) {
        view.showProgress();
        ApiInterface service = RetroFitClient.getClient().create(ApiInterface.class);
        Call<JoinData> call = service.face(User_ID);
        call.enqueue(new Callback<JoinData>(){
            @Override
            public void onResponse(Call<JoinData> call, Response<JoinData> response) {
                view.hideProgress();
                view.onToastMessage("");
            }

            @Override
            public void onFailure(Call<JoinData> call, Throwable t) {
                view.hideProgress();
                view.onToastMessage("");
            }
        });
    }

    @Override
    public void requestGPS(String User_ID, String User_LATITUDE, String User_LONGITUDE) {
        view.showProgress();
        ApiInterface service = RetroFitClient.getClient().create(ApiInterface.class);
        Call<JoinData> call = service.GPS(User_ID,User_LATITUDE, User_LONGITUDE);
        call.enqueue(new Callback<JoinData>() {

            @Override
            public void onResponse(Call<JoinData> call, Response<JoinData> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    Boolean isSuccess = response.body().getResult();
                    if(isSuccess) {
                        view.onToastMessage("위치등록 성공");
                    }
                }
            }

            @Override
            public void onFailure(Call<JoinData> call, Throwable t) {
                view.hideProgress();
                view.onToastMessage(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void requestDetection(String User_ID) {
        view.showProgress();
        ApiInterface service = RetroFitClient.getClient().create(ApiInterface.class);
        Call<JoinData> call = service.detection(User_ID);
        call.enqueue(new Callback<JoinData>() {

            @Override
            public void onResponse(Call<JoinData> call, Response<JoinData> response) {
                view.hideProgress();
                view.onToastMessage("dection ok.");
            }

            @Override
            public void onFailure(Call<JoinData> call, Throwable t) {
                view.hideProgress();
                view.onToastMessage("dection orr.");
            }
        });

    }

    @Override
    public void requestCheckID(String User_ID) {
        view.showProgress();
        ApiInterface service = RetroFitClient.getClient().create(ApiInterface.class);
        Call<JoinData> call = service.checkUser(User_ID);
        call.enqueue(new Callback<JoinData>() {
            @Override
            public void onResponse(Call<JoinData> call, Response<JoinData> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean isDuplicate = response.body().getResult();
                    if(isDuplicate) {
                        view.onToastMessage("중복된 아이디가 이미 존재합니다.");
                    } else {
                        view.onToastMessage("사용가능한 아이디입니다.");
                        view.blockUserID();
                    }
                }
            }

            @Override
            public void onFailure(Call<JoinData> call, Throwable t) {
                view.hideProgress();
                view.onToastMessage(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void requestSignup(String User_ID, String User_PW, String User_NAME, String User_SEX, String User_PHONE, String User_EMAIL) {
        view.showProgress();
        ApiInterface service = RetroFitClient.getClient().create(ApiInterface.class);
        Call<JoinData> call = service.saveUser(User_ID, User_PW, User_NAME, User_SEX, User_PHONE, User_EMAIL);
        call.enqueue(new Callback<JoinData>() {
            @Override
            public void onResponse(Call<JoinData> call, Response<JoinData> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    Boolean isSuccess = response.body().getResult();
                    if(isSuccess) {
                        view.onToastMessage("회원가입 성공");
                    }
                }
            }

            @Override
            public void onFailure(Call<JoinData> call, Throwable t) {
                view.hideProgress();
                view.onToastMessage(t.getLocalizedMessage());
            }
        });
    }
}