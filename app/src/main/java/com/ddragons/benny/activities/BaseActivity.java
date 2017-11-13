package com.ddragons.benny.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ddragons.benny.utils.Utils;
import com.ddragons.benny.utils.PrefsManager;

public class BaseActivity extends AppCompatActivity {

    public PrefsManager prefsManager;
    public Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        if (prefsManager == null) {
            prefsManager = new PrefsManager(this);
        }
        if (utils == null) {
            utils = new Utils(this);
        }
    }

    public void showDialog(final Activity activity, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        activity.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
