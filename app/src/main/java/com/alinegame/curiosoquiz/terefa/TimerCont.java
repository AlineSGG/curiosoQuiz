package com.alinegame.curiosoquiz.terefa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.alinegame.curiosoquiz.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aline Silva Gonzaga on 25/02/2018.
 */

public class TimerCont extends AsyncTask<Object, Object, String> {
    private ProgressBar progressBar;
    private int total = 0;
    private static int PROGRESSO = 0;
    Context context;
    Activity activity;

    public TimerCont(Context context, Activity activity, ProgressBar progressBar) {
        this.progressBar = progressBar;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        try {

            Thread.sleep(4000);

            for (int i=0; i <=2; i++) {
                publishProgress();
                Thread.sleep(4000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        PROGRESSO = 25;
        this.total += PROGRESSO;
        progressBar.incrementProgressBy(PROGRESSO);
        Log.i("progress", String.valueOf(total));
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        View vieww = activity.getLayoutInflater().inflate(R.layout.timer_over, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(vieww);

        final AlertDialog alert = builder.create();

        alert.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alert.dismiss();
                    }
                });
            }
        }, 1000);
        super.onPostExecute(result);
    }
}
