package com.example.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "infoDB.db", null, 1);
    }

    private String[] projection = {"_id", "year", "month", "day", "content", "firstOrder", "secondOrder", "progress"};

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
                "secondOrder TEXT," +
                "progress TEXT);");
    }

    //사용하진 않지만 SQLiteOpenHelper 상속하면 무조건 구현해야 됨
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS plans");
        onCreate(db);
    }

    public ArrayList<PlanData> queryDay(int year, int month, int day) {
        ArrayList<PlanData> plans = new ArrayList<PlanData>();
        if(year>0 && month>0 && day>0) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("plans", projection, "year=? and month=? and day=?", new String[]{Integer.toString(year), Integer.toString(month), Integer.toString(day)}, null, null, "firstOrder, secondOrder");
            if (cursor != null) {
                while(cursor.moveToNext()) {
                    int _idVal = cursor.getInt(cursor.getColumnIndex("_id"));
                    int yearVal = cursor.getInt(cursor.getColumnIndex("year"));
                    int monthVal = cursor.getInt(cursor.getColumnIndex("month"));
                    int dayVal = cursor.getInt(cursor.getColumnIndex("day"));
                    String progressVal = cursor.getString(cursor.getColumnIndex("progress"));
                    String firstOrderVal = cursor.getString(cursor.getColumnIndex("firstOrder"));
                    String secondOrderVal = cursor.getString(cursor.getColumnIndex("secondOrder"));
                    String contentVal = cursor.getString(cursor.getColumnIndex("content"));

                    plans.add(new PlanData(_idVal, yearVal, monthVal, dayVal,  progressVal, firstOrderVal, secondOrderVal, contentVal));
                }
                cursor.close();
            }
            this.close();
        }
        return plans;
    }

    public int getMaxId(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("plans", new String[]{"MAX(_id) as id"}, null, null, null, null, null);
        int id=0;
        if (cursor != null) {
            int idCol = cursor.getColumnIndex("id");
            while(cursor.moveToNext()) {
               id = cursor.getInt(idCol);
            }
            cursor.close();
        }
        this.close();
        return id;
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

    public void update(PlanData plan) {
        if(plan.getYear()>0 && plan.getMonth()>0 && plan.getDay()>0) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("year", plan.getYear());
            values.put("month", plan.getMonth());
            values.put("day", plan.getDay());
            values.put("content", plan.getContent());
            values.put("firstOrder", plan.getFirstOrder());
            values.put("secondOrder", plan.getSecondOrder());
            values.put("progress", plan.getProgress());
            db.update("plans", values, "_id=?", new String[]{Integer.toString(plan.getId())});
            this.close();
        }
    }

    public void delete(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("plans", "_id=?", new String[]{Integer.toString(_id)});
        this.close();
    }
}