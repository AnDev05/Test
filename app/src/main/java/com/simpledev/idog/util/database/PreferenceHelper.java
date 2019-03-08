package com.simpledev.idog.util.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.simpledev.idog.util.Constants;

import javax.inject.Inject;

public class PreferenceHelper implements RxPreferenceHelper {

    private SharedPreferences mPrefs;
    private static PreferenceHelper sPreferencesHelper;

    public static PreferenceHelper getInstance(Context context) {
        if (sPreferencesHelper == null) {
            sPreferencesHelper = new PreferenceHelper(context);
        }
        return sPreferencesHelper;
    }

    @Inject
    PreferenceHelper(Context context) {
        mPrefs = context.getSharedPreferences(Constants.Prf.RF_HELPER, Context.MODE_PRIVATE);
    }

    @Override
    public void saveString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    @Override
    public String getString(String key) {
        return mPrefs.getString(key, "");
    }

    @Override
    public void saveBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    @Override
    public boolean getBoolean(String key) {
        return mPrefs.getBoolean(key, false);
    }

    @Override
    public boolean contains(String key) {
        return mPrefs.contains(key);
    }

    @Override
    public int getInt(String key) {
        return mPrefs.getInt(key, 0);
    }

    @Override
    public void saveInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }
}
