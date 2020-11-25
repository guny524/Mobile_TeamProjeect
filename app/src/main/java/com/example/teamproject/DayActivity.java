package com.example.teamproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class DayActivity extends AppCompatActivity {

    //리스너에서 접근해야해서 멤버
    private ArrayList<PlanData> planList;
    DBHelper dbHelper;
    PlannerAdapter plannerAdapter;

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

        //인텐트로 받아와서
        Intent intent = getIntent();
        final int year = intent.getIntExtra("year",0);
        final int month = intent.getIntExtra("month",0);
        final int day = intent.getIntExtra("day",0);

        //날짜 표시
        final TextView tvDay = findViewById(R.id.tvDay);
        tvDay.setText(year+"년 "+month+"월 "+day+"일");

        //DB에서 띄운 날짜에 해당하는 plan들 쿼리해오기
        dbHelper = new DBHelper(this);
        planList = dbHelper.queryDay(year, month, day);

        final ListView lvPlans = findViewById(R.id.lvPlans);
        plannerAdapter = new PlannerAdapter(this, R.layout.item_listview, planList);
        lvPlans.setAdapter(plannerAdapter);

        final Button button = (Button)findViewById(R.id.btAdd);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView=(TextView)findViewById(R.id.etContent);
                dbHelper.insert(year, month, day, null, null, null, null);
                planList.add(new PlanData(dbHelper.getMaxId(), year, month, day, null, null, null, null));//아무것도 없는 것 추가
                plannerAdapter.notifyDataSetChanged();
            }
        });

        Button finishBtn = findViewById(R.id.finish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(DayActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

}