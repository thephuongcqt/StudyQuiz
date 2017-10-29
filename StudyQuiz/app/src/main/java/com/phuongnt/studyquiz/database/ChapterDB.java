package com.phuongnt.studyquiz.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.utils.MyDateFormater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class ChapterDB {
    public static String TABLE_CHAPTER = "dboChapter";

    public static String COLUMN_CHAPTER_ID = "ChapterId";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_CREATE_DATE = "CreateDate";
    public static String COLUMN_SUBJECT_ID = "SubjectId";

    public static String createTable(){
        String sql = "CREATE TABLE " + TABLE_CHAPTER
                + " (" + COLUMN_CHAPTER_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_CREATE_DATE + " TEXT, "
                + COLUMN_SUBJECT_ID + " INTEGER)";
        return sql;
    }

    public static String dropTable(){
        String sql = "DROP TABLE IF EXISTS " + TABLE_CHAPTER;
        return sql;
    }

    public static long insert(SearchChapterResponse chapter){
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_CHAPTER_ID, chapter.getChapterId());
            values.put(COLUMN_NAME, chapter.getName());
            values.put(COLUMN_CREATE_DATE, MyDateFormater.getStringFromDate(chapter.getCreatedDate()));
            values.put(COLUMN_SUBJECT_ID, chapter.getSubjectId());

            return db.insert(TABLE_CHAPTER, null, values);
        } catch(Exception e){
            Log.e("Chapter_Insert", e.getMessage());
            return -1;
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }



    public static List<SearchChapterResponse> getChaptersBySubjectId(long subjectId){
        String[] columns = {"*"};
        String where = COLUMN_SUBJECT_ID + " = ?";
        String[] args = {subjectId + ""};
        List<SearchChapterResponse> list = new ArrayList<>();
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openReadableDatabase();
            Cursor cursor = db.query(TABLE_CHAPTER, columns, where, args, null, null, null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    long chapterId = cursor.getLong(0);
                    String name = cursor.getString(1);
                    String dateStr = cursor.getString(2);
                    SearchChapterResponse item = new SearchChapterResponse(chapterId, name, MyDateFormater.getDateFromString(dateStr), subjectId);
                    list.add(item);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e){
            Log.e("Chapter_getChapters", e.getMessage());
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
        return list;
    }

    public static SearchChapterResponse getChapterById(long chapterId){
        String[] columns = {"*"};
        String where = COLUMN_CHAPTER_ID + " = ?";
        String[] args = {chapterId + ""};
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openReadableDatabase();
            Cursor cursor = db.query(TABLE_CHAPTER, columns, where, args, null, null, null);
            if(cursor.moveToFirst()){
                String name = cursor.getString(1);
                String dateStr = cursor.getString(2);
                long subjectId = cursor.getLong(3);
                SearchChapterResponse item = new SearchChapterResponse(chapterId, name, MyDateFormater.getDateFromString(dateStr), subjectId);
                return item;
            }
        } catch (Exception e){
            Log.e("Chapter_getChapters", e.getMessage());
        }finally {
            DatabaseManager.getInstance().closeDatabase();
        }
        return null;
    }
}
