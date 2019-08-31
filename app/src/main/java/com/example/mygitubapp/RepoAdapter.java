package com.example.mygitubapp;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygitubapp.model.Repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private static final String TAG = "TAG_RepoAdapter";
    private ArrayList<Repo> repoList;

    public RepoAdapter(ArrayList<Repo> repoList) { this.repoList = repoList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_list_item, parent, false);
        return new ViewHolder(inflatedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Repo curRepo = repoList.get(position);
        Log.d(TAG, "onBindViewHolder: curRepo: " + curRepo);
        holder.nameView.setText(curRepo.getName());
        holder.langView.setText(curRepo.getLanguage());
        holder.updateView.setText(getAgo(curRepo.getPushedAt()));
    }

    @Override
    public int getItemCount() { return repoList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private TextView langView;
        private TextView updateView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameTV);
            langView = itemView.findViewById(R.id.languageTV);
            updateView = itemView.findViewById(R.id.lastUpdateTV);
        }
    }

    private static String getAgo(String repoDate) {

        final long[] timemillis = {31536000000L, 2628002880L, 86400000L, 3600000, 60000, 1000};
        final String[] timeStrings = {"year", "month", "day", "hour", "minute", "second"};
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        String result = "";

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(repoDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return repoDate;
        }

        assert date != null;
        long repoMillis = date.getTime();
        long nowMillis = new Date().getTime();
        long diff = nowMillis - repoMillis;

        for (int i=0 ; i<timemillis.length ; i++) {
            long interval = diff/timemillis[i];
            if (interval !=0) {
                String suffix = (interval == 1) ? "" : "s";
                result = String.format(Locale.US,
                        "%d %s%s ago", interval, timeStrings[i], suffix);
                return result;
            }
        }

        return result;
    }
}
