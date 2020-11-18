package com.example.teamproject;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class DayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);   //엑티비티 타이틀바 제거
        setContentView(R.layout.activity_day);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height= display.getHeight();

        getWindow().getAttributes().width=width;
        getWindow().getAttributes().height=height;

        final ArrayList<String> items= new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.lvDay);
        final ListAdapter adapter = new PlannerAdapter(this, items);
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.btAdd) ;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView=(TextView)findViewById(R.id.etContent);
                items.add(null);
                listView.setAdapter(adapter);
            }
        }) ;
    }

}