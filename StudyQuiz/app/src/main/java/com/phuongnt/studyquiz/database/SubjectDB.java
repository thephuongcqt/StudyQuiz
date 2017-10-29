package com.phuongnt.studyquiz.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.utils.MyDateFormater;

import java.io.Serializable;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class SubjectDB {
    public static String TABLE_SUBJECT = "dboSubject";

    public static String COLUMN_SUBJECT_ID = "SubjectId";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_CREATE_DATE = "CreatedDate";

    public static String createTable(){
        String sql = "CREATE TABLE " + TABLE_SUBJECT
                + "( " + COLUMN_SUBJECT_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_CREATE_DATE + " TEXT)";
        return sql;
    }

    public static String dropTable(){
        String sql = "DROP TABLE IF EXISTS " + TABLE_SUBJECT;
        return sql;
    }

    public long insert(SearchSubjectResponse subject){
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_SUBJECT_ID, subject.getSubjectId());
            values.put(COLUMN_NAME, subject.getName());
            values.put(COLUMN_CREATE_DATE, MyDateFormater.getStringFromDate(subject.getCreatedDate()));

            return db.insert(TABLE_SUBJECT, null, values);
        } catch(Exception e){
            Log.e("Subject_Insert", e.getMessage());
            return -1;
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }
}
