package com.phuongnt.studyquiz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PhuongNT on 10/17/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudyQuiz.db";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDB.createTable());
        db.execSQL(SearchHistoryDB.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IS EXISTS " + UserDB.TABLE_USER);
        db.execSQL("DROP TABLE IS EXISTS " + SearchHistoryDB.TABLE_SEARCH_HISTORY);
    }
}
