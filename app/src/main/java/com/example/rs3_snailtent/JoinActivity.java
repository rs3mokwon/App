package com.example.rs3_snailtent;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rs3_snailtent.User.JoinContract;
import com.example.rs3_snailtent.User.JoinPresenter;

public class JoinActivity extends AppCompatActivity implements JoinContract.View{

    private String usersex;

    EditText putid, putpw, pwputok, putname, putpn, putemail, putsex;
    Button overlapbtn, okbtn;
    RadioGroup radioGroup;
    ProgressDialog progressDialog;

    boolean isChecked = false;

    private JoinPresenter presenter;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        putid = (EditText)findViewById(R.id.putid);
        putpw = (EditText)findViewById(R.id.putpw);
        pwputok = (EditText)findViewById(R.id.pwputok);
        putname = (EditText)findViewById(R.id.putname);
        putsex = (EditText)findViewById(R.id.putsex);
        putpn = (EditText)findViewById(R.id.putpn);
        putemail = (EditText)findViewById(R.id.putemail);

        presenter = new JoinPresenter(this);

        overlapbtn = (Button)findViewById(R.id.overlapbtn);
        okbtn = (Button)findViewById(R.id.okbtn);

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        int sexID = radioGroup.getCheckedRadioButtonId();
        usersex = ((RadioButton)findViewById(sexID)).getText().toString();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("잠시만 기다려주세요 :) ");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton sexbtn = (RadioButton)findViewById(checkedId);
                usersex = sexbtn.getText().toString();
                putsex.setText(usersex);
            }
        });

        overlapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_ID = putid.getText().toString().trim();
                if(User_ID.length() <=0) {
                    putid.setError("아이디를 입력하세요");
                } else {
                    presenter.requestCheckID(User_ID);
                }
            }
        });

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String User_ID = putid.getText().toString().trim();
                String User_PW = putpw.getText().toString().trim();
                String User_PWok = putpw.getText().toString().trim();
                String User_NAME = putname.getText().toString().trim();
                String User_SEX = putsex.getText().toString().trim();
                String User_PHONE = putpn.getText().toString().trim();
                String User_EMAIL = putemail.getText().toString().trim();

                if (!isChecked) {
                    Toast.makeText(JoinActivity.this, "아이디 중복체크를 해주세요.", Toast.LENGTH_SHORT).show();
                } else if (User_PW.length() <= 0) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 작성해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!User_PWok.equals(User_PW)) {
                    Toast.makeText(JoinActivity.this, " 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                } else if (User_NAME.length() <= 0) {
                    Toast.makeText(JoinActivity.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (User_SEX.length() <= 0) {
                    Toast.makeText(JoinActivity.this, "성별을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                } else if (User_PHONE.length() <= 0) {
                    Toast.makeText(JoinActivity.this, "핸드폰 번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (User_EMAIL.length() <= 0) {
                    Toast.makeText(JoinActivity.this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.requestSignup(User_ID, User_PW, User_NAME, User_SEX, User_PHONE, User_EMAIL);
                    onToastMessage("회원가입에 성공하였습니다");
                    finish();
                }

            }
        });
    }

    public void onRadioButtonClicked(View v){
        boolean checked = ((RadioButton)v).isChecked();

        switch ((v.getId())) {
            case R.id.radioman :
                if(checked){
                    putsex.setText("남자");
                    Toast.makeText(JoinActivity.this, "남자", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.radiowoman :
                if(checked){
                    putsex.setText("여자");
                    Toast.makeText(JoinActivity.this, "여자", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onToastMessage(String message) {
        Toast.makeText(JoinActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void blockUserID() {
        putid.setFocusable(false);
        putid.setEnabled(false);
        overlapbtn.setEnabled(false);
        isChecked = true;
    }
}
