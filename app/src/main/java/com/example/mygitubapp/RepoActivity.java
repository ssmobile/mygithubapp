package com.example.mygitubapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import com.example.mygitubapp.model.Repo;
import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RepoActivity extends AppCompatActivity implements HttpUrlConnectionHelper.HttpCallback {

    private static ArrayList<Repo> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (repos == null) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpUrlConnectionHelper.getReposResponse(RepoActivity.this);
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
        //no unchecked cast
        repos = (ArrayList<Repo>)response;
        populateRecyclerView();

    }

    private void populateRecyclerView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView = findViewById(R.id.reposRecyclerView);
                Collections.sort(repos);
                RepoAdapter adapter = new RepoAdapter(repos);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RepoActivity.this));
            }
        });
    }
}
