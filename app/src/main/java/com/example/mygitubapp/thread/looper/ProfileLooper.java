package com.example.mygitubapp.thread.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mygitubapp.MainActivity;
import com.example.mygitubapp.model.User;
import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;
import com.google.gson.Gson;

import java.io.IOException;

public class ProfileLooper extends Thread implements HttpUrlConnectionHelper.HttpCallback {

    private static final String TAG = "TAG_ProfileLooper";

    public Handler myHandler;
    private Handler mainHandler;
    private static Message message;
    private User user;


    public ProfileLooper(Handler mainHandler) {
        this.mainHandler = mainHandler;

        myHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "CHILD THREAD: RECEIVING MESSAGE FROM MAIN");
                Message message = new Message();
                message.what = msg.what;
                Bundle b = new Bundle();
                b.putString("key", "from child handler");
                message.setData(b);
                myHandler.sendMessage(message);
            }
        };
    }

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        Looper.loop();

    }

    public Handler getMyHandler() { return myHandler; }

    public void setMyHandler(Handler myHandler) { this.myHandler = myHandler; }


    @Override
    public void onHttpUrlConnectionResponse(String json) {
//        Bundle b = new Bundle();
//        Log.i(TAG, "handleMessage: " + json );
//        b.putString("key", json);
//        message.setData(b);
//        myHandler.sendMessage(message);
    }
}
