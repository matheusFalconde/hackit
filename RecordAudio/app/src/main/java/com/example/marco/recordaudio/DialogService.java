package com.example.marco.recordaudio;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by mathe on 20/08/2016.
 */
public class DialogService
{
    public  static  void ShowMessage(String title, String message, String button, Context context)
    {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(button,null)
                .show();
    }
}
