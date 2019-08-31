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
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements HttpUrlConnectionHelper.HttpCallback {

    public static final String TAG = "TAG_MainActivity";
    private ImageView profileIV;
    private TextView loginTV;
    private TextView nameTV;
    private TextView companyTV;
    private TextView locationTV;
    private TextView bioTV;
    private TextView reposNumTV;

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
        new ProfileAsyncTask().execute(this);
    }

    @Override
    public void onHttpUrlConnectionResponse(String json) {
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
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
        reposNumTV = findViewById(R.id.reposNumTV);
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
                reposNumTV.setText(repos);
            }
        });

    }
}
