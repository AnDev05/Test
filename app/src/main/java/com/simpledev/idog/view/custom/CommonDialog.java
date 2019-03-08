package com.simpledev.idog.view.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.simpledev.idog.R;


public class CommonDialog {

    private CommonDialog(){

    }

    public static Dialog createProgressDialog(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_progress_bar_layout, null);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.setCancelable(false);
        return dialog;
    }
}