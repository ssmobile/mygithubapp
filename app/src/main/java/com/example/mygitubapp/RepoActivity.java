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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
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
    public void onHttpUrlConnectionResponse(final Object json) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                @SuppressWarnings("unchecked")
                ArrayList<Repo> repos = (ArrayList<Repo>)json;
                RecyclerView recyclerView = findViewById(R.id.reposRecyclerView);
                RepoAdapter adapter = new RepoAdapter(repos);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RepoActivity.this));
            }
        });

    }
}
