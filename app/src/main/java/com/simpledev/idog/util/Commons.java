package com.simpledev.idog.util;

import android.widget.EditText;

import com.simpledev.idog.BuildConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

public class Commons {

    private Commons(){

    }

    public static String BASE_API_URL = BuildConfig.API_BASE_URL;

    public static boolean isNullOrEmpty(Object input) {
        boolean isNullOrEmpty;
        if (input == null) {
            isNullOrEmpty = true;
        } else if (input instanceof String) {
            isNullOrEmpty = input.toString().trim().isEmpty();
        } else if (input instanceof EditText) {
            isNullOrEmpty = ((EditText) input).getText().toString().trim().isEmpty();
        } else if (input instanceof List) {
            isNullOrEmpty = ((List) input).isEmpty();
        } else if (input instanceof HashMap) {
            isNullOrEmpty = ((HashMap) input).isEmpty();
        } else {
            isNullOrEmpty = input instanceof ArrayList && ((ArrayList) input).isEmpty();
        }

        return isNullOrEmpty;
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            Timber.e(e);
        } catch (InterruptedException e) {
            Timber.e(e);
        }
        return false;
    }
}
