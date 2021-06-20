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

public class FanActivity extends Fragment {

    SensorActivity activity;

    ImageButton fan;
    int count;

    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SensorActivity) getActivity();
    }

    public void onDetach() {
        super.onDetach();

        activity = null;

    }

    public FanActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.activity_fan, container, false);

        fan = (ImageButton)rootview.findViewById(R.id.fanoff);

        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                if(count%2==1){
                    fan.setImageResource(R.drawable.fanon);
                    BusProvider.getInstance().post(new BusEvent.Bus("3"));
                } else if (count%2==0){
                    fan.setImageResource(R.drawable.fanoff);
                    BusProvider.getInstance().post(new BusEvent.Bus("4"));
                }
            }
        });
        return  rootview;
    }
}
