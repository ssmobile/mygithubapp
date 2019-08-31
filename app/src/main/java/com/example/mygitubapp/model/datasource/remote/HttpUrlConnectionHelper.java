package com.example.mygitubapp.model.datasource.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUrlConnectionHelper {
    private static final String USER_PROFILE_URL = "https://api.github.com/users/ssmobile";
    private static final String TAG = "TAG_ConnectionHelper";

    public static void getMyProfile(HttpCallback callback) throws IOException {
        String jsonResponse = getDummyJSON();
        callback.onHttpUrlConnectionResponse(jsonResponse);
    }

    public interface HttpCallback { void onHttpUrlConnectionResponse(String json); }

    public static String getJSONResponse() throws IOException {
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

        return jsonBuilder.toString();
    }

    private static String getDummyJSON() {
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
