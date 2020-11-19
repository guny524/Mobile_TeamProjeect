package com.example.teamproject;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class DayActivity extends AppCompatActivity {

    final ArrayList<String> planList = new ArrayList<String>();

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

        final ListView lvDay = findViewById(R.id.lvDay);
        final PlannerAdapter adapter = new PlannerAdapter(this, R.layout.item_listview, planList);
        lvDay.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.btAdd);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView=(TextView)findViewById(R.id.etContent);
                planList.add("");//아무것도 안보이게 만듦
                adapter.notifyDataSetChanged();
            }
        });
    }

}