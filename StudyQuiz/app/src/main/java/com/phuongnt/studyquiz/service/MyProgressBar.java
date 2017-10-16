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
    public synchronized static ProgressDialog getInstance(Context context){
        if(_instance == null){
            _instance = new ProgressDialog(context);
        }
        _instance.setIndeterminate(false);
        _instance.setCancelable(true);
        return _instance;
    }
    public synchronized static void show(Context context){
        if(_instance == null){
            _instance = new ProgressDialog(context);
        }
        _instance.setIndeterminate(false);
        _instance.setCancelable(true);
        _instance.show();
    }

    public synchronized static void dismiss(){
        _instance.dismiss();
        _instance = null;
    }
}
