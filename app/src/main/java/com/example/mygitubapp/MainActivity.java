package com.example.mygitubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygitubapp.model.User;
import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;
import com.example.mygitubapp.thread.asynctask.ProfileAsyncTask;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements HttpUrlConnectionHelper.HttpCallback {

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
        new ProfileAsyncTask().execute(this);
    }


    @Override
    public void onHttpUrlConnectionResponse(String json) {
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        Log.d(TAG, "onHttpUrlConnectionResponse: user: " + user.toString());

    }
}
