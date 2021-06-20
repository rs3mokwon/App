package com.example.rs3_snailtent;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.rs3_snailtent.Camera.FaceActivity;
import com.example.rs3_snailtent.Server.ApiInterface;
import com.example.rs3_snailtent.User.JoinContract;
import com.example.rs3_snailtent.User.JoinPresenter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RapsberryActivity extends AppCompatActivity  implements JoinContract.View{

    Button face, detection;
    private JoinPresenter presenter;

    private ApiInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapsberry);

        face = (Button)findViewById(R.id.face);
        detection = (Button)findViewById(R.id.detection);

        presenter = new JoinPresenter(this);

        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        FaceActivity facefragment = new FaceActivity();
        transaction1.replace(R.id.main_frame, facefragment);
        transaction1.commit();

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_ID = "aaa";
                presenter.requestFace(User_ID);

                AlertDialog.Builder builder = new AlertDialog.Builder(RapsberryActivity.this);
                builder.setTitle("RS3").setMessage("얼굴인식을 시작합니다. CCTV를 봐주세요");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        detection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_ID = "aaa";
                presenter.requestDetection(User_ID);

                Toast.makeText(RapsberryActivity.this, "CCTV가 작동 됩니다.", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onToastMessage(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void blockUserID() {

    }
}
