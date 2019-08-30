package com.example.mygitubapp.model.datasource.remote;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionHelper {
    public static final String USER_PROFILE_URL = "https://api.github.com/users/ssmobile";
    private static HttpURLConnection httpURLConnection;
    private static URL url;

    public static void getMyProfile(HttpCallback callback) throws IOException {
//
//        String my_jwt = "eyJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE1NjcxOTYyNDYsImV4cCI6MTU2NzE5Njg0NiwiaXN" +
//                "zIjozOTkzOX0.QLC7lEK6LI2FeXTE4YYikZksYdH2M3Bclf-vurtv95S7wBkHQu044GpAz5qskZdOQQl_" +
//                "axfXNVT7qUwwq5L6G9p6ZCkbmW5nOZCm52arDnY-rXHbQe0XFR18MezE5w6yfruhehBb72wcQ3WsoQL1-" +
//                "bWU9P7n540bhosUZfOwR_uuadNGb8RDgI1_ID67i0g3sKdFwkQm2ogETRZ3T2G8BBO7AT05Y3TygWfgC8" +
//                "3l6N3TqqoJ9Q_GpcTJ8jw4hcn-iZCFNpY1v7A2mHFq5H1OvPntzY1vnQFupmThH9CYdiu88ztgsD8yaDt" +
//                "yEqL2M9ZhGFEDi16Okju7X-NIuSaPUA";
//        String accept = "application/vnd.github.machine-man-preview+json";
//
//        url = new URL(USER_PROFILE_URL);
//        httpURLConnection = (HttpURLConnection)url.openConnection();

//
//        httpURLConnection.setRequestProperty("Authorization BEARER", my_jwt);
//        httpURLConnection.setRequestProperty("Accept", accept);
//        httpURLConnection = (HttpURLConnection)url.openConnection();
//        httpURLConnection.setDoInput(true);
//        httpURLConnection.setDoInput(true);
        String jsonResponse = getJSONResponse();

//        httpURLConnection.toString();
//        InputStream inputStream = httpURLConnection.getInputStream();
//        int currentRead;
//        while ((currentRead = inputStream.read()) != -1) {
//            char currentChar = (char)currentRead;
//            jsonResponse = jsonResponse + currentChar;
//
//        }
//
//        httpURLConnection.disconnect();
//        callback.onHttpUrlConnectionResponse(jsonResponse);

        callback.onHttpUrlConnectionResponse(jsonResponse);
    }

    public interface HttpCallback {
        void onHttpUrlConnectionResponse(String json);
    }

    public static String getJSONResponse() {
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
