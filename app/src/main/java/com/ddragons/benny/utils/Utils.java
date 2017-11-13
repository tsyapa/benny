package com.ddragons.benny.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import  android.support.design.widget.Snackbar;

/**
 * Created by Vitaliy Tsyapa on 11/6/2017.
 */

public class Utils {

    private Context mContext;

    public Utils(Context context) {
        mContext = context;
    }

    public static Snackbar showSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        return snackbar;
    }


}
