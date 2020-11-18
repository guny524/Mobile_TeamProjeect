package com.example.teamproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends Activity {

    ArrayList<String> dayList = new ArrayList<String>();

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

        //이번달 1일 무슨요일인지 판단 calendar.set(Year,Month,Day)
        calendar.set(curYear, curMonth - 1, 1);
        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }

        //해당 월에 표시할 일 수 구함
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        for (int i = 0; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }

        //다시 원래 날짜로 바꿈
        //Calendar 객체가 싱글턴이라서...
        calendar.set(curYear, curMonth, curDay);

        GridView gridView = findViewById(R.id.gvContent);
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                //layout에서 descendantFocusability를 설정하지 않으면 작동 안 함
                String data = dayList.get(position);

                startActivity(new Intent(CalendarActivity.this, DayActivity.class));
            }
        });
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), R.layout.item_gridview, dayList, calendar);
        gridView.setAdapter(gridAdapter);
    }
}