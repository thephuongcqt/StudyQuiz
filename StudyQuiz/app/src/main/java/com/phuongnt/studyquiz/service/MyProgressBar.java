package com.phuongnt.studyquiz.service;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by PhuongNT on 10/17/17.
 */

public class MyProgressBar {
    private static ProgressDialog _instance = null;
    private MyProgressBar(){

    }
    public synchronized static void show(Context context){
        if(_instance == null){
            _instance = new ProgressDialog(context);
        }
        _instance.setIndeterminate(false);
        _instance.setCancelable(false);
        _instance.setMessage("Loading...");
        _instance.show();
    }

    public synchronized static void dismiss(){
        if(_instance != null){
            _instance.dismiss();
            _instance = null;
        }
    }
}
