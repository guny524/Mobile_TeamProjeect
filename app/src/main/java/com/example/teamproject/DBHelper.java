package com.example.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private String[] projection = {"_id", "year", "month", "day", "content", "firstOrder", "secondOrder", "progress"};

    public DBHelper(Context context){
        super(context, "infoDB.db", null, 1);
    }

    //사용하진 않지만 SQLiteOpenHelper 상속하면 무조건 구현해야 됨
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS plans (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "year INTEGER NOT NULL," +
                "month INTEGER NOT NULL," +
                "day INTEGER NOT NULL," +
                "content TEXT," +
                "firstOrder TEXT," +
                "seconOrder INTEGER," +
                "progress TEXT);");
    }

    //사용하진 않지만 SQLiteOpenHelper 상속하면 무조건 구현해야 됨
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS plans");
        onCreate(db);
    }

    public void showResult(Cursor cursor) {
        int monthCol = cursor.getColumnIndex("month");
        int dayCol = cursor.getColumnIndex("day");
        while(cursor.moveToNext()) {
            int month = cursor.getInt(monthCol);
            int day = cursor.getInt(dayCol);
            //show;
        }
    }

    public void insert(int year, int month, int day, String content, String firstOrder, String secondOrder, String progress) {
        if(year>0 && month>0 && day>0) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("year", year);
            values.put("month", month);
            values.put("day", day);
            values.put("content", content);
            values.put("firstOrder", firstOrder);
            values.put("secondOrder", secondOrder);
            values.put("progress", progress);
            db.insert("plans", null, values);
            this.close();
        }
    }

    public void queryMonth(int year, int month) {
        if(year>0 && month>0) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("plans", projection, "year=? and month=?", new String[]{Integer.toString(year), Integer.toString(month)}, null, null, null);
            if (cursor != null) {
                showResult(cursor);
                cursor.close();
            }
            this.close();
        }
    }

    public void queryDay(int year, int month, int day) {
        if(year>0 && month>0 && day>0) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("plans", projection, "year=? and month=? and day=?", new String[]{Integer.toString(year), Integer.toString(month), Integer.toString(day)}, null, null, null);
            if (cursor != null) {
                showResult(cursor);
                cursor.close();
            }
            this.close();
        }
    }

    public void updata(int _id, int year, int month, int day, String content, String firstOrder, String secondOrder, String progress) {
        if(year>0 && month>0 && day>0) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("year", year);
            values.put("month", month);
            values.put("day", day);
            values.put("content", content);
            values.put("firstOrder", firstOrder);
            values.put("secondOrder", secondOrder);
            values.put("progress", progress);
            db.update("plans", values, "_id=?", new String[]{Integer.toString(_id)});
            this.close();
        }
    }

    public void delete(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("plans", "_id=?", new String[]{Integer.toString(_id)});
        this.close();
    }
}