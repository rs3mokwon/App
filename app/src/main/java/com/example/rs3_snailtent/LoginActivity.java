package com.example.rs3_snailtent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rs3_snailtent.Server.ApiInterface;
import com.example.rs3_snailtent.Server.RetroFitClient;
import com.example.rs3_snailtent.User.LoginData;
import com.example.rs3_snailtent.User.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editid, editpw;
    Intent intent;
    private ProgressBar mProgressView;

    private ApiInterface service;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editid = (EditText)findViewById(R.id.editid);
        editpw = (EditText)findViewById(R.id.editpw);

        final Button loginbtn = (Button)findViewById(R.id.loginbtn);
        final Button joinbtn = (Button)findViewById(R.id.joinbtn);

        mProgressView = (ProgressBar) findViewById(R.id.login_progress);

        service = RetroFitClient.getClient().create(ApiInterface.class);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), JoinActivity.class );
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        editid.setError(null);
        editpw.setError(null);

        String User_ID = editid.getText().toString().trim();
        String User_PW = editpw.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        //????????? ????????? ??????
        if(User_ID.isEmpty()) {
            editid.setError("???????????? ??????????????????");
            focusView = editid;
            cancel = true;
        } else if(!isIDValid(User_ID)) {
            editid.setError("???????????? ???????????????.");
            focusView = editid;
            cancel = true;
        }

        //???????????? ????????? ??????
        if(User_PW.isEmpty()) {
            editpw.setError("??????????????? ??????????????????");
            focusView = editpw;
            cancel = true;
        } else if (!isPasswordValid(User_PW)) {
            Log.d("asd","asd");
            editpw.setError("??????????????? ???????????????");
            focusView = editpw;
            cancel = true;
        }

        if(cancel) {
            focusView.requestFocus();
        } else {
            Loginpresent(new LoginData(User_ID, User_PW));
            showProgress(true);
        }
    }

    private void Loginpresent(LoginData data){
        service.getLogin(data).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse result = response.body();

                String login = "????????? ??????!";
                //Log.d("asd", result.getMessage());

                if(result.getMessage().contains(login)){
                    Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    showProgress(false);

                    String User_ID = editid.getText().toString().trim();

                    intent = new Intent(getApplicationContext(), MainSensorActivity.class );
                    intent.putExtra("User_ID",User_ID);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    showProgress(false);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                Log.e("????????? ?????? ??????", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isIDValid(String User_ID) {
        return User_ID.contains("");
    }

    private boolean isPasswordValid(String User_PW) {
        return User_PW.length() >= 6;
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
