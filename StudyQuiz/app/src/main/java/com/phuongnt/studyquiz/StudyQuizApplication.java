package com.phuongnt.studyquiz;

import android.app.Application;
import android.content.Context;

import com.phuongnt.studyquiz.database.DBHelper;
import com.phuongnt.studyquiz.database.DatabaseManager;

/**
 * Created by PhuongNT on 10/17/17.
 */

public class StudyQuizApplication extends Application {
    private static Context context = null;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DBHelper dbHelper = new DBHelper(context);
        DatabaseManager.initializeInstance(dbHelper);
    }

    public static Context getContext(){
        return context;
    }
}
