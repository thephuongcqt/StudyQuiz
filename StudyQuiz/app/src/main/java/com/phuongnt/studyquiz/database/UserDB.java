package com.phuongnt.studyquiz.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.phuongnt.studyquiz.model.viewmodel.User;

import java.util.ArrayList;

/**
 * Created by PhuongNT on 10/17/17.
 */

public class UserDB {
    public static final String TABLE_USER = "dboUser";

    public static final String COLUMN_USERID = "UserId";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_ROLE = "Role";

    public static String createTable(){
        String sql = "CREATE TABLE " + TABLE_USER + " ("
                + COLUMN_USERID + " INTEGER PRIMARY KEY, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_ROLE + " INTEGER)";
        return  sql;
    }

    public boolean insert(User user){
        SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERID, user.getUserId());
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_EMAIL, user.getEmail());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_ROLE, user.getRole());

        return db.insert(TABLE_USER, null, contentValues) > 0;
    }

    public void truncate(){
        SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER + " IF EXISTS");
    }

    public User getCurrentUser(){
        User user = null;
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
            String sql = "SELECT * FROM " + TABLE_USER + " LIMIT 1";
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor != null && cursor.moveToFirst()){
                long userId = cursor.getLong(cursor.getColumnIndex(COLUMN_USERID));
                String username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                long role = cursor.getLong(cursor.getColumnIndex(COLUMN_ROLE));
                user = new User(userId, username, password, email, name, role);
            }
            return user;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

//    public ArrayList<Word> getAllWord(){
//        ArrayList<Word> results = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * From " + WORD_TABLE_NAME, null);
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//                String word = cursor.getString(cursor.getColumnIndex(WORD_ID));
//                String definition = cursor.getString(cursor.getColumnIndex(WORD_DEFINITION));
//                int type = cursor.getInt(cursor.getColumnIndex(WORD_TYPE));
//                int favorite = cursor.getInt(cursor.getColumnIndex(WORD_FAVORITE));
//                Word currentWord = new Word(word, definition, type, favorite);
//                results.add(currentWord);
//                cursor.moveToNext();
//            }
//        }
//        return results;
//    }
//
//
//    public boolean updateWord(Word word){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues content = new ContentValues();
//        content.put(WORD_DEFINITION, word.getDefinition());
//        content.put(WORD_TYPE, word.getType());
//        content.put(WORD_FAVORITE, word.getFavorite());
//        return db.update(WORD_TABLE_NAME, content, WORD_ID + " = ?", new String[]{word.getWord()}) > 0;
//    }
//
//    public boolean deleteWord(String id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(WORD_TABLE_NAME, WORD_ID + " = ?", new String[]{id}) > 0;
//    }
}
