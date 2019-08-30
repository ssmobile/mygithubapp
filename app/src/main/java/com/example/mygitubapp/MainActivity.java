package com.example.mygitubapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;
import com.example.mygitubapp.thread.looper.ProfileLooper;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        looperExecution();
    }



    public void looperExecution() {
        ProfileLooper profileLooper = new ProfileLooper(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d(TAG, "handleMessage: ");
                super.handleMessage(msg);
                Bundle b = msg.getData();
                Toast.makeText(MainActivity.this,b.get("json_profile").toString(),Toast.LENGTH_SHORT)
                        .show();
            }
        });

        profileLooper.start();
        profileLooper.getHandler().sendMessage(new Message());
    }
}
