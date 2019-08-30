package com.example.mygitubapp.thread.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mygitubapp.model.User;
import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;
import com.google.gson.Gson;

import java.io.IOException;

public class ProfileLooper extends Thread implements HttpUrlConnectionHelper.HttpCallback {

    private static final String TAG = "TAG_ProfileLooper";

    private Handler mainHandler;
    private Handler workHandler;
    private static Message msg;
    private User user;


    public ProfileLooper(final Handler myHandler) {
        this.workHandler = myHandler;

        this.mainHandler = new Handler(Looper.myLooper()) {

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                ProfileLooper.msg = msg;
                Thread networkThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HttpUrlConnectionHelper.getMyProfile(ProfileLooper.this);
                        } catch (IOException e) {
                            Log.e(TAG, "Looper.handleMessage(): ", e);
                        }
                    }
                });
                networkThread.start();
            }
        };
    }

    @Override
    public void onHttpUrlConnectionResponse(String json) {
        user = new Gson().fromJson(json, User.class);
        Log.d(TAG, "onHttpUrlConnectionResponse: user: " + user.toString());

    }

    @Override
    public void run() {
        super.run();
        Log.d(TAG, "run: ");
        Looper.prepare();
        Looper.loop();
    }

    public Handler getHandler() { return mainHandler; }

    public void setMainHandler(Handler mainHandler) { this.mainHandler = mainHandler; }
}
