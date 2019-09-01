package com.example.mygitubapp.model.datasource.remote;

import android.util.Log;
import com.example.mygitubapp.model.Repo;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HttpUrlConnectionHelper {
    public static final String USER_PROFILE_URL = "https://api.github.com/users/ssmobile";
    public static final String USER_REPOS_URL = USER_PROFILE_URL.concat("/repos");
    private static final String TAG = "TAG_ConnectionHelper";

    public static void getReposResponse(HttpCallback callback) throws IOException {
        URL url = new URL(USER_REPOS_URL);
        ArrayList<Repo> jsonResponse = new ArrayList<>();
        InputStreamReader in =
                new InputStreamReader(url.openStream(),StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().create();
        try (JsonReader reader = new JsonReader(in)) {
            reader.beginArray();
            while (reader.hasNext()) {
                Log.d(TAG, "getReposResponse: reader" + reader);
                Repo repo = gson.fromJson(reader,Repo.class);
                Log.d(TAG, "getReposResponse: repo:" + repo.toString());
                jsonResponse.add(repo);
            }
        } catch (Exception e) {
            Log.e(TAG, "getReposResponse: ", e);
        }

        callback.onHttpUrlConnectionResponse(jsonResponse);
    }


    public static void getUserResponse(HttpCallback callback) throws IOException {
        URL url = new URL(USER_PROFILE_URL);
        InputStreamReader inputStreamReader =
                new InputStreamReader(url.openStream(),StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(inputStreamReader);
        StringBuilder jsonBuilder = new StringBuilder();
        int currentRead;

        while ((currentRead = in.read()) != -1) {
            char currentChar = (char)currentRead;
            jsonBuilder.append(currentChar);
        }

        callback.onHttpUrlConnectionResponse(jsonBuilder.toString());
    }

    public interface HttpCallback { void onHttpUrlConnectionResponse(Object json); }

}
