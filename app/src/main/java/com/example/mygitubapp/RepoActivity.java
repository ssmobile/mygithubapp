package com.example.mygitubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mygitubapp.model.Repo;
import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper.USER_REPOS_URL;

public class RepoActivity extends AppCompatActivity implements HttpUrlConnectionHelper.HttpCallback {

    private static final String TAG = "TAG_RepoActivity";
    private static ArrayList<Repo> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Log.d(TAG, "onResume: repos:" + repos.toString());
        } catch (NullPointerException e) {
            Log.e(TAG, "onResume: repos is null");
        }

        if (repos == null) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpUrlConnectionHelper.getMyRepos(RepoActivity.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            populateRecyclerView();
        }


    }

    @Override
    public void onHttpUrlConnectionResponse(final Object response) {

        repos = (ArrayList<Repo>)response;
        populateRecyclerView();

    }

    private void populateRecyclerView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView = findViewById(R.id.reposRecyclerView);
                RepoAdapter adapter = new RepoAdapter(repos);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RepoActivity.this));
            }
        });
    }
}
