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

public class LedActivity extends Fragment {

    SensorActivity activity;

    ImageButton led;
    int count;

    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SensorActivity) getActivity();

    }

    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    public LedActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.activity_led, container, false);

        led = (ImageButton)rootview.findViewById(R.id.ledoff);

        led.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                if(count%2==1){
                    led.setImageResource(R.drawable.ledon);
                    BusProvider.getInstance().post(new BusEvent.Bus("1"));

                } else if (count%2==0){
                    led.setImageResource(R.drawable.ledoff);
                    BusProvider.getInstance().post(new BusEvent.Bus("0"));
                }

            }

        });
        return rootview;
    }

}
