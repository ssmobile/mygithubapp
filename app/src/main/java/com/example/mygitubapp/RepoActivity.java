package com.example.mygitubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;

import java.io.IOException;

public class RepoActivity extends AppCompatActivity implements HttpUrlConnectionHelper.HttpCallback {

    private static final String TAG = "TAG_RepoActivity";
    private TextView logTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        logTV = findViewById(R.id.logTV);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUrlConnectionHelper.getMyRepos(RepoActivity.this, RepoActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onHttpUrlConnectionResponse(String json) {
        logTV.setText(json);
    }
}
