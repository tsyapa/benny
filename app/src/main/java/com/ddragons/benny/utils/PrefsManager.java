package com.ddragons.benny.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vitaliy Tsyapa on 11/6/2017.
 */

public class PrefsManager {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private static final String PREF_NAME = "prefs";
    private static final String PREFERENCES_REG_ID = "pref_reg_id";

    public PrefsManager(Context context) {
        mContext = context;
        mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }

    public void saveRegId(String regId) {
        mEditor.putString(PREFERENCES_REG_ID, regId);
        mEditor.apply();
    }

    public String getRegId() {
        return mPrefs.getString(PREFERENCES_REG_ID, "");
    }

}
