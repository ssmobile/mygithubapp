package com.example.mygitubapp.thread.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mygitubapp.model.datasource.remote.HttpUrlConnectionHelper;

import java.io.IOException;

public class ProfileAsyncTask extends AsyncTask {

    private static final String TAG = "TAG_ProfileAsyncTask";
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }

    @Override
    protected Object doInBackground(Object... params) {
        Log.d(TAG, "doInBackground: ");
        String response = "";
        try {
            HttpUrlConnectionHelper
                    .getMyProfile((HttpUrlConnectionHelper.HttpCallback)params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: values");
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d(TAG, "onPostExecute: " + o.toString());
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
        Log.d(TAG, "onCancelled: ");
    }
}
