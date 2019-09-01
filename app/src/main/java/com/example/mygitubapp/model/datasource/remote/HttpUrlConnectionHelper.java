package com.example.mygitubapp.model.datasource.remote;

import android.util.Log;
import com.example.mygitubapp.model.Repo;
import com.example.mygitubapp.model.User;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HttpUrlConnectionHelper {
    public static final String USER_PROFILE_URL = "https://api.github.com/users/ssmobile";
    public static final String USER_REPOS_URL = USER_PROFILE_URL.concat("/repos");
    private static final String TAG = "TAG_ConnectionHelper";

    public static void getMyProfile(HttpCallback callback) throws IOException {
        String jsonResponse = getDummyUserJSON();
        callback.onHttpUrlConnectionResponse(jsonResponse);
    }

    public static void getMyRepos(HttpCallback callback) throws IOException {
        URL url = new URL(USER_REPOS_URL);
        ArrayList<Repo> jsonResponse = new ArrayList<>();
        InputStreamReader in =
                new InputStreamReader(url.openStream(),StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().create();
        try (JsonReader reader = new JsonReader(in)) {
            reader.beginArray();
            while (reader.hasNext()) {
                Log.d(TAG, "getMyRepos: reader" + reader);
                Repo repo = gson.fromJson(reader,Repo.class);
                Log.d(TAG, "getMyRepos: repo:" + repo.toString());
                jsonResponse.add(repo);
            }
        } catch (Exception e) {

        }

        callback.onHttpUrlConnectionResponse(jsonResponse);
    }


    public static void getJSONResponse(HttpCallback callback) throws IOException {
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

    private static String getDummyUserJSON() {
        return "{\n" +
                "  \"login\": \"ssmobile\",\n" +
                "  \"id\": 54039487,\n" +
                "  \"node_id\": \"MDQ6VXNlcjU0MDM5NDg3\",\n" +
                "  \"avatar_url\": \"https://avatars2.githubusercontent.com/u/54039487?v=4\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/ssmobile\",\n" +
                "  \"html_url\": \"https://github.com/ssmobile\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/ssmobile/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/ssmobile/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/ssmobile/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/ssmobile/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/ssmobile/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/ssmobile/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/ssmobile/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/ssmobile/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/ssmobile/received_events\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"site_admin\": false,\n" +
                "  \"name\": \"Σπυρίδων Μαγκλιβέρας\",\n" +
                "  \"company\": \"MobileAppsCompany\",\n" +
                "  \"blog\": \"\",\n" +
                "  \"location\": \"Atlanta, Georgia\",\n" +
                "  \"email\": null,\n" +
                "  \"hireable\": null,\n" +
                "  \"bio\": \"A repository with all the repositories from my training with MobileApps.\",\n" +
                "  \"public_repos\": 17,\n" +
                "  \"public_gists\": 0,\n" +
                "  \"followers\": 0,\n" +
                "  \"following\": 1,\n" +
                "  \"created_at\": \"2019-08-12T18:11:25Z\",\n" +
                "  \"updated_at\": \"2019-08-30T17:19:43Z\"\n" +
                "}";
    }

}
