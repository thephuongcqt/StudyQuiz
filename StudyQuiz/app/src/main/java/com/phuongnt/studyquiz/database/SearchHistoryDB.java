package com.phuongnt.studyquiz.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.phuongnt.studyquiz.model.viewmodel.SearchHistory;
import com.phuongnt.studyquiz.utils.MyDateFormater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhuongNT on 10/28/17.
 */

public class SearchHistoryDB {
    public static String TABLE_SEARCH_HISTORY = "dboSearchHistory";

    public static String COLUMN_ID = "ID";
    public static String COLUMN_SEARCH_VALUE = "SearchValue";
    public static String COLUMN_USERID = "UserId";
    public static String COLUMN_DATE = "Date";

    public static String createTable(){
        String sql = "CREATE TABLE " + TABLE_SEARCH_HISTORY + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SEARCH_VALUE + " TEXT, "
                + COLUMN_USERID + " INTEGER, "
                + COLUMN_DATE + " TEXT)";
        return  sql;
    }

    public static String dropTable(){
        String sql = "DROP TABLE IF EXISTS " + TABLE_SEARCH_HISTORY;
        return sql;
    }

    public void truncate(){
        SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SEARCH_HISTORY);
    }

    public static long insert(SearchHistory item){
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_SEARCH_VALUE, item.getSearchValue());
            values.put(COLUMN_USERID, item.getUserId());
            values.put(COLUMN_DATE, MyDateFormater.getStringFromDate(item.getDate()));
            return  db.insert(TABLE_SEARCH_HISTORY, null, values);
        } catch(Exception e){
            Log.e(TABLE_SEARCH_HISTORY , e.getMessage());
            return -1;
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    public static List<SearchHistory> getUserSearchHistory(long userId){
        String[] columns = {"*"};
        String where = COLUMN_USERID + " = ?";
        String[] args = {userId + ""};
        String orderBy = COLUMN_DATE + " DESC";
        SQLiteDatabase db = DatabaseManager.getInstance().openReadableDatabase();
        Cursor cursor = db.query(TABLE_SEARCH_HISTORY, columns, where, args, null, null, orderBy);
        List<SearchHistory> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                long id = cursor.getLong(0);
                String search = cursor.getString(1);
                long user = cursor.getLong(2);
                String date = cursor.getString(3);
                SearchHistory item = new SearchHistory(id, search, user, date);
                list.add(item);
                cursor.moveToNext();
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return list;
    }

    public static boolean update(SearchHistory item){
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_SEARCH_VALUE, item.getSearchValue());
            values.put(COLUMN_USERID, item.getUserId());
            values.put(COLUMN_DATE, MyDateFormater.getStringFromDate(item.getDate()));

            String where = "UPPER(" + COLUMN_USERID + ") = ? AND UPPER(" + COLUMN_SEARCH_VALUE + ") = ? ";
            String[] args = {new String(item.getUserId() + "").toUpperCase(), item.getSearchValue().toUpperCase()};

            return  db.update(TABLE_SEARCH_HISTORY, values, where, args) > 0;
        } catch(Exception e){
            Log.e(TABLE_SEARCH_HISTORY , e.getMessage());
            return false;
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }
}
