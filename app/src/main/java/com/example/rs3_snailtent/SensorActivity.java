package com.example.rs3_snailtent;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.rs3_snailtent.EventBus.BusEvent;
import com.example.rs3_snailtent.EventBus.BusProvider;
import com.example.rs3_snailtent.Sensor.AngleActivity;
import com.example.rs3_snailtent.Sensor.FanActivity;
import com.example.rs3_snailtent.Sensor.LedActivity;
import com.example.rs3_snailtent.Sensor.MotorActivity;
import com.squareup.otto.Subscribe;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class SensorActivity extends AppCompatActivity {

    TextView textStatus, temp, humb, gasbbm, angleview;
    ImageButton btnParied;
    ImageButton Led, Fan, angle, motor;

    BluetoothSPP bt;

    int getgas, gethum, gettem, getangle;
    String[] array;
    String myString;

    Bundle bundle = new Bundle();
    AngleActivity fragment4 = new AngleActivity();

    @Subscribe
    public void getBus(BusEvent.Bus Bus){
        myString = Bus.getEventnum();

        bt.send(myString,false);
        Log.d("Sensor",myString);

        onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textStatus = (TextView) findViewById(R.id.text_status);
        temp = (TextView)findViewById(R.id.temper);
        humb = (TextView)findViewById(R.id.humd);
        gasbbm = (TextView)findViewById(R.id.gas);
        angleview = (TextView)findViewById(R.id.angledgree);

        Led = (ImageButton)findViewById(R.id.led);
        Fan = (ImageButton)findViewById(R.id.fan);
        angle = (ImageButton)findViewById(R.id.anglebtn);
        motor = (ImageButton)findViewById(R.id.motor);

        btnParied = (ImageButton) findViewById(R.id.btn_paired);

        bt = new BluetoothSPP(this);

        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        LedActivity fragment1 = new LedActivity();
        transaction1.replace(R.id.main_frame, fragment1);
        transaction1.commit();

        angleview.setText(" ");

        if (!bt.isBluetoothAvailable()) { //블루          투스 사용 불가

        }

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            @Override
            public void onDataReceived(byte[] data, String message) {

                array = message.split(" ");

                getgas = Integer.parseInt(array[0]);
                gethum = Integer.parseInt(array[1]);
                gettem = Integer.parseInt(array[2]);
                getangle = Integer.parseInt(array[3]);

                for(int i=0; i<array.length;i++){
                    gasbbm.setText(getgas + "ppm");
                    humb.setText(gethum + "%");
                    temp.setText(gettem + "˚C");
                    angleview.setText(getangle + "˚");
                }

                Log.d("데이터",message);
            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {
                textStatus.setText("Connected to " + name);

            }

            @Override
            public void onDeviceDisconnected() {
                textStatus.setText("Connection lost");

            }

            @Override
            public void onDeviceConnectionFailed() {
                textStatus.setText("Unable to connect");

            }
        });

        btnParied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });

        Led.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                LedActivity fragment1 = new LedActivity();
                transaction1.replace(R.id.main_frame, fragment1).commit();
            }
        });

        Fan.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                FanActivity fragment2 = new FanActivity();
                transaction2.replace(R.id.main_frame, fragment2).commit();
            }
        });

        motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                MotorActivity fragment3 = new MotorActivity();
                transaction3.replace(R.id.main_frame, fragment3).commit();
            }
        });

        angle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                transaction4.replace(R.id.main_frame, fragment4).commit();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
        BusProvider.getInstance().unregister(this);
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER); //DEVICE_ANDROID는 안드로이드 기기 끼리

                BusProvider.getInstance().register(this);
                setup();
            }
        }
    }
    public void setup() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext(),"Bluetooth was not enabled.",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
