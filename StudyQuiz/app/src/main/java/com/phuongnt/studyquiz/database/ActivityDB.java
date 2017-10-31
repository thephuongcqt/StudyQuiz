package com.phuongnt.studyquiz.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.phuongnt.studyquiz.model.viewmodel.Activity;
import com.phuongnt.studyquiz.utils.MyDateFormater;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class ActivityDB {
    public static String TABLE_ACTIVITY = "dboActivity";

    public static String COLUMN_ACTIVITY_ID = "ActivityId";
    public static String COLUMN_CHAPTER_ID = "ChapterId";
    public static String COLUMN_SUBJECT_ID = "SubjectId";
    public static String COLUMN_TYPE = "Type";
    public static String COLUMN_DATE = "Date";
    public static String COLUMN_USER_ID = "UserId";

    public static String createTable(){
        String sql = "CREATE TABLE " + TABLE_ACTIVITY
                + " (" + COLUMN_ACTIVITY_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_CHAPTER_ID + " INTEGER, "
                + COLUMN_SUBJECT_ID + " INTEGER, "
                + COLUMN_TYPE + " INTEGER, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_USER_ID + " INTEGER)";
        return sql;
    }

    public static String dropTable(){
        String sql = "DROP TABLE IF EXISTS " + TABLE_ACTIVITY;
        return sql;
    }

    public static long insert(Activity activity){
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_CHAPTER_ID, activity.getChapterId());
            values.put(COLUMN_SUBJECT_ID, activity.getSubjectId());
            values.put(COLUMN_TYPE, activity.getType());
            values.put(COLUMN_DATE, MyDateFormater.getStringFromDate(activity.getDate()));
            values.put(COLUMN_USER_ID, activity.getUserId());

            return db.insert(TABLE_ACTIVITY, null, values);
        } catch(Exception e){
            Log.e("Chapter_Insert", e.getMessage());
            return -1;
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    public static boolean update(Activity item){
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_DATE, MyDateFormater.getStringFromDate(item.getDate()));
            values.put(COLUMN_TYPE, item.getType());

            String where = COLUMN_USER_ID + " = ? AND (" + COLUMN_SUBJECT_ID + " = ? OR " + COLUMN_CHAPTER_ID + " = ?)";
            String[] args = {item.getUserId() + "", item.getSubjectId() < 0 ? "" : item.getSubjectId() + "", item.getChapterId() < 0 ? "" : item.getChapterId() + ""};

            return  db.update(TABLE_ACTIVITY, values, where, args) > 0;
        } catch(Exception e){
            Log.e(TABLE_ACTIVITY , e.getMessage());
            return false;
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    public static List<Activity> getAll(long userId){
        List<Activity> list = new ArrayList<>();
        String[] columns = {"*"};
        String where = COLUMN_USER_ID + " = ?";
        String[] args = {userId + ""};
        String orderBy = COLUMN_DATE + " DESC";
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openReadableDatabase();
            Cursor cursor = db.query(TABLE_ACTIVITY, columns, where, args, null, null, orderBy);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    long activityId = cursor.getLong(cursor.getColumnIndex(COLUMN_ACTIVITY_ID));
                    long chapterId = cursor.getLong(cursor.getColumnIndex(COLUMN_CHAPTER_ID));
                    long subjectId = cursor.getLong(cursor.getColumnIndex(COLUMN_SUBJECT_ID));
                    int type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
                    String dateStr = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                    Date date = MyDateFormater.getDateFromString(dateStr);

                    Activity item = new Activity(activityId, chapterId, subjectId, type,date, userId);
                    list.add(item);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e){
            Log.e(TABLE_ACTIVITY, e.getMessage());
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
        return list;
    }
}
