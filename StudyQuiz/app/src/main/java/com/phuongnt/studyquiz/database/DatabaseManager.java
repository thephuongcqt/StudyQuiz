package com.phuongnt.studyquiz.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PhuongNT on 10/17/17.
 */

public class DatabaseManager {
    private Integer mOpenCounter = 0;

    private static DatabaseManager _instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (_instance == null) {
            _instance = new DatabaseManager();
            mDatabaseHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (_instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return _instance;
    }


    public synchronized SQLiteDatabase openWritableDatabase(){
        mOpenCounter++;
        mDatabase = mDatabaseHelper.getWritableDatabase();
        return mDatabase;
    }

    public synchronized  SQLiteDatabase openReadableDatabase(){
        mOpenCounter++;
        mDatabase = mDatabaseHelper.getReadableDatabase();
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        mOpenCounter--;
        if(mOpenCounter == 0)
            mDatabase.close();
    }
}
