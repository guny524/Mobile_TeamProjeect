package com.example.teamproject;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import static android.app.PendingIntent.getActivity;
import static com.example.teamproject.R.layout.popuplayout;


public class PlannerAdapter extends ArrayAdapter<String> {

//    SQLiteDatabase sqliteDB;
//    sqliteDB = init_database();
//    init_tables();
//    load_values();
//    save_values();


    PlannerAdapter(Context context, ArrayList<String> items){
        super(context, R.layout.popuplayout, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(popuplayout, parent, false);
        }


        String item = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.editText);
        textView.setText(item);


        return convertView;
    }



}


//    private void init_tables() {
//
//        if (sqliteDB != null) {
//            String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS CONTACT_T (" +
//                    "PROGRESS"           + "TEXT," +
//                    "HIGH"         + "TEXT," +
//                    "LOW "        + "TEXT," +
//                    "TODO"       + "TEXT" + ")" ;
//
//            System.out.println(sqlCreateTbl) ;
//
//            sqliteDB.execSQL(sqlCreateTbl) ;
//        }
//    }
//
//    private void load_values() {
//
//        if (sqliteDB != null) {
//            String sqlQueryTbl = "SELECT * FROM CONTACT_T" ;
//            Cursor cursor = null ;
//
//            // 쿼리 실행
//            cursor = sqliteDB.rawQuery(sqlQueryTbl, null) ;
//
//            if (cursor.moveToNext()) { // 레코드가 존재한다면,
//                // 진행상황 (TEXT) 값 가져오기.
//                String pg = cursor.getString(0) ;
//                EditText editTextpg = (EditText) findViewById(R.id.editTextpg) ;
//                editTextpg.setText(pg) ;
//
//                // 상위 (TEXT) 값 가져오기
//                String high = cursor.getString(1) ;
//                EditText editTexthigh = (EditText) findViewById(R.id.editTexthigh) ;
//                editTexthigh.setText(high) ;
//
//                // 하위 (TEXT) 값 가져오기
//                String low = cursor.getString(2) ;
//                EditText editTextlow = (EditText) findViewById(R.id.editTextlow) ;
//                editTextlow.setText(low) ;
//
//                // 할일 (TEXT) 값 가져오기
//                String todo = cursor.getString(3) ;
//                EditText editTexttodo = (EditText) findViewById(R.id.editTexttodo) ;
//                editTexttodo.setText(todo) ;
//            }
//        }
//    }
//
//    private void save_values() {
//        if (sqliteDB != null) {
//
//            // delete
//            sqliteDB.execSQL("DELETE FROM CONTACT_T") ;
//
//            EditText editTextpg = (EditText) findViewById(R.id.editTextpg) ;
//            String pg = editTextpg.getText().toString() ;
//
//            EditText editTexthigh = (EditText) findViewById(R.id.editTexthigh) ;
//            String high = editTexthigh.getText().toString() ;
//
//            EditText editTextlow = (EditText) findViewById(R.id.editTextlow) ;
//            String low = editTextlow.getText().toString() ;
//
//            EditText editTexttodo = (EditText) findViewById(R.id.editTexttodo) ;
//            String todo = editTexttodo.getText().toString() ;
//
//            String sqlInsert = "INSERT INTO CONTACT_T " +
//                    "(PG, HIGH, LOW, TODO) VALUES (" +
//                    pg + "," +
//                    "'" + high + "'," +
//                    "'" + low + "'," +
//                    "'"+ todo +"'" +")" ;
//
//            System.out.println(sqlInsert) ;
//
//            sqliteDB.execSQL(sqlInsert) ;
//        }
//    }
