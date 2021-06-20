package com.example.rs3_snailtent.Sensor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rs3_snailtent.EventBus.BusEvent;
import com.example.rs3_snailtent.EventBus.BusProvider;
import com.example.rs3_snailtent.R;
import com.example.rs3_snailtent.SensorActivity;

public class MotorActivity extends Fragment {

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

    public MotorActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.activiry_motor, container, false);

        motor = (ImageButton)rootview.findViewById(R.id.motoron);

        motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                if(count%2==1){
                    motor.setImageResource(R.drawable.engineon);
                    BusProvider.getInstance().post(new BusEvent.Bus("5"));

                } else if (count%2==0){
                    motor.setImageResource(R.drawable.engine);
                    BusProvider.getInstance().post(new BusEvent.Bus("6"));
                }

            }

        });
        return rootview;
    }

}
