package com.example.rs3_snailtent.Camera;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rs3_snailtent.R;
import com.example.rs3_snailtent.RapsberryActivity;

public class FaceActivity extends Fragment {

    RapsberryActivity activity;

    String url = "http://220.81.195.72:5000";

    private final static int MESSAGE_READ = 2;
    private WebSettings webSettings;

    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (RapsberryActivity) getActivity();
    }

    public void onDetach() {
        super.onDetach();

        activity = null;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.activity_camera, container, false);

        WebView web = (WebView)rootview.findViewById(R.id.webview);

        web.setWebViewClient(new WebViewClient()); //클릭시 새창 안뜨게
        webSettings = web.getSettings(); // 세부 세팅 등록
        webSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클바트 허용 여부
        webSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        webSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        webSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(false); // 화면 줌 허용 여부
        webSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        webSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부

        web.loadUrl("http://220.81.195.72:5000"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작


        return  rootview;
    }
}
