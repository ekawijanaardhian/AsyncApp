package com.example.asyncapp;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {
    TextView mTextView;
    ProgressBar progressBar;

    public MyAsyncTask(TextView mTextView, ProgressBar progressBar) {
        this.mTextView = mTextView;
        this.progressBar = progressBar;

    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(20);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 400;
        for (int i = 0; i <= n; i++) {
            publishProgress(i*400);
            // Sleep for the random amount of time
            try {
                Thread.sleep(400);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }


    protected void onPostExecute(String result) {
        mTextView.setText(result);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        System.out.println("Running" + values[0]);
        progressBar.setProgress(values[0]);

    }

}
