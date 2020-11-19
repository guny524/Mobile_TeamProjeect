package com.example.teamproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends Activity {

    ArrayList<DayData> dayList = new ArrayList<DayData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // 오늘 날짜를 세팅 해준다.
        Calendar calendar = Calendar.getInstance();
        final Date currentTime = calendar.getTime();
        //연,월,일을 따로 저장
        final String curDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(currentTime);
        final int curYear = Integer.parseInt(curDate.substring(0, 4));
        final int curMonth = Integer.parseInt(curDate.substring(4, 6));
        final int curDay = Integer.parseInt(curDate.substring(6, 8));

        //현재 날짜 텍스트뷰에 뿌려줌
        TextView tvDate = (TextView)findViewById(R.id.tvTitle);
        tvDate.setText(curYear + "/" + curMonth);

        //weekday gridView 요일 표시
        //ArrayList<String> dayList = new ArrayList<String>(Arrays.asList("일","월","화","수","목","금","토"));
        //그냥 xml로 때려 박음

        //이번달 1일 무슨 요일인지 계산 0이 1월로 취급됨
        calendar.set(curYear, calendar.get(Calendar.MONTH), 1);
        final int weekday = calendar.get(Calendar.DAY_OF_WEEK)-1;

        //이번 달 마지막 날 계산
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)); //다시 원래 날짜로 바꿈 //Calendar 객체가 싱글턴이라서 다른데서 쓸 때 헷갈림
        final int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        DBHelper dbHelper = new DBHelper(this);

        //공백 빈칸 음수에서 시작하면 그날 day랑 index 맞음
        //일별로 DB에서 내용 받아와서 추가
        for (int dayIndex = 1-weekday; dayIndex <= maxDay; dayIndex++) {
            ArrayList<PlanData> plans = dbHelper.queryDay(curYear, curMonth, dayIndex);
            if(dayIndex > 0) {
                dayList.add(new DayData(dayIndex, plans));
            } else {
                dayList.add(new DayData(dayIndex, null));
            }
        }

        GridView gridView = findViewById(R.id.gvContent);
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                //layout에서 descendantFocusability를 설정하지 않으면 작동 안 함
                DayData monthData = dayList.get(position);
                startActivity(new Intent(CalendarActivity.this, DayActivity.class));
            }
        });
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), R.layout.item_gridview, dayList, calendar);
        gridView.setAdapter(gridAdapter);
    }
}