package com.example.rs3_snailtent.Sensor;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rs3_snailtent.R;
import com.example.rs3_snailtent.SensorActivity;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class AngleActivity extends Fragment {

    SensorActivity activity;

    ImageButton motor;
    int count;


    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SensorActivity) getActivity();

    }

    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    public AngleActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.activity_angle, container, false);

        TextView angleview = (TextView)rootview.findViewById(R.id.angledgree);


        return rootview;
    }
}
