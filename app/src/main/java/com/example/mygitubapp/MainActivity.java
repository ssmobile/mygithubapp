package com.example.mygitubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygitubapp.model.User;
import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements HttpUrlConnectionHelper.HttpCallback {

    public static final String TAG = "TAG_MainActivity";
    private ImageView profileIV;
    private TextView loginTV;
    private TextView nameTV;
    private TextView companyTV;
    private TextView locationTV;
    private TextView bioTV;
    private TextView reposNumBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUrlConnectionHelper.getMyProfile(MainActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onHttpUrlConnectionResponse(String json) {
        Log.d(TAG, "response: json: " + json);
        User user = new Gson().fromJson(json, User.class);
        Log.d(TAG, "onHttpUrlConnectionResponse: user: " + user.toString());
        populateViews(user);
    }

    private void bindViews() {
        profileIV = findViewById(R.id.profileIV);
        loginTV = findViewById(R.id.loginTV);
        nameTV = findViewById(R.id.nameTV);
        companyTV = findViewById(R.id.companyTV);
        locationTV = findViewById(R.id.locationTV);
        bioTV = findViewById(R.id.bioTV);
        reposNumBTN = findViewById(R.id.reposNumTV);
    }

    private void populateViews(final User user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Picasso.get().load(user.getAvatarUrl()).into(profileIV);

                loginTV.setText(user.getLogin());
                nameTV.setText(user.getName());
                locationTV.setText(user.getLocation());
                companyTV.setText(user.getCompany());
                bioTV.setText(user.getBio());
                String repos = "" + user.getPublicRepos();
                reposNumBTN.setText(repos);
            }
        });
    }

    public void onClick(View v) {
        startActivity(new Intent(this,RepoActivity.class));
    }


}
