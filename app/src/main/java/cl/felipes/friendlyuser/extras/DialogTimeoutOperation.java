package cl.felipes.friendlyuser.extras;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Felipes on 23-07-2015.
 */
public class DialogTimeoutOperation extends AsyncTask<String, Void, Void> {
    Class activity;
    Context context;
    int duration;
    Dialog dialog;
    Intent intent;
    ArrayList<Activity> actividades;

    public DialogTimeoutOperation(Context context, Dialog d, int duration, Class activity) {
        this.activity = activity;
        this.context = context;
        this.duration = duration;
        this.dialog = d;
    }

    public DialogTimeoutOperation(Context context, Dialog d, int duration, ArrayList<Activity> acts) {
        this.actividades = acts;
        this.context = context;
        this.duration = duration;
        this.dialog = d;
    }

    public DialogTimeoutOperation(Context context, Dialog d, int duration) {
        this.context = context;
        this.duration = duration;
        this.dialog = d;
    }


    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (activity != null) {
            intent = new Intent(context, activity);
            context.startActivity(intent);
        } else {
            if (actividades != null) {
                for (Activity a : actividades) {
                    if(a != null)
                        a.finish();
                }
            }
            ((Activity) context).finish();

        }
        dialog.dismiss();
    }
}
