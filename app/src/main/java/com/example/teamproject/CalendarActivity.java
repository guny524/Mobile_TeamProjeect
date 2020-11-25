package com.example.teamproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends Activity {

    final ArrayList<DayData> dayList = new ArrayList<DayData>();
    //gridView에 setOnClickItem에서 접근해야해서 멤버
    Calendar calendar;//setCalendar에 접근해야해서 멤버
    //표시할 날짜 preNth, NextMth 리스너에서 접근해야 해서 멤버
    int displayYear;
    int displayMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // 오늘 날짜를 가져온다.
        this.calendar = Calendar.getInstance();
        final Date currentTime = calendar.getTime();
        //연,월,일을 따로 저장
        final String curDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(currentTime);
        final int curYear = Integer.parseInt(curDate.substring(0, 4));
        final int curMonth = Integer.parseInt(curDate.substring(4, 6));
        final int curDay = Integer.parseInt(curDate.substring(6, 8));
        //현재 날짜는 따로 저장, 표시할 날짜와 다름

        //표시할 날짜를 현재 날짜로 업데이트
        displayYear = curYear;
        displayMonth = curMonth;
        //현재날짜 보여주기
        setCalendar(displayYear, displayMonth);

        //날짜 변경 리스너 달아주기
        //리스너에서 접근해야해서 display날짜 변수들을 엑티비티 멤버로 선언함, oncreate 끝나고도 살아있어야해서 멤버로
        Button nextMth = findViewById(R.id.next);
        nextMth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (displayMonth < 12) {
                    displayMonth++;
                } else {
                    displayYear++;
                    displayMonth = 1;
                }
                setCalendar(displayYear, displayMonth);
            }
        });

        Button prevMth = findViewById(R.id.prev);
        prevMth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (displayMonth > 1) {
                    displayMonth--;
                } else {
                    displayYear--;
                    displayMonth = 12;
                }
                setCalendar(displayYear, displayMonth);
            }
        });
    }

    public void setCalendar(final int year, final int month) {
        //날짜 텍스트뷰에 뿌려줌
        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText(year + "/" + month);

        //이번 달 1일 무슨 요일인지 계산 0이 1월로 취급됨
        calendar.set(year, month-1, 1);
        final int weekday = calendar.get(Calendar.DAY_OF_WEEK)-1;

        //이번 달 마지막 날 계산
        //다시 원래 날짜로 바꿈 //Calendar 객체가 싱글턴이라서
        calendar.set(Calendar.MONTH, month-1);
        final int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        DBHelper dbHelper = new DBHelper(this);
        dayList.clear();

        //일별로 DB에서 내용 받아와서 추가
        //공백 빈칸 음수에서 시작하면 그날 day랑 index 맞음
        for (int dayIndex = 1-weekday; dayIndex <= maxDay; dayIndex++) {
            ArrayList<PlanData> plans = dbHelper.queryDay(year, month, dayIndex);
            if(dayIndex > 0) {
                dayList.add(new DayData(dayIndex, plans));
            } else {
                dayList.add(new DayData(dayIndex, null));
            }
        }

        final GridView gridView = findViewById(R.id.gvContent);
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                DayData dayData = dayList.get(position);

                Intent intent = new Intent(CalendarActivity.this, DayActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", dayData.getDay());
                startActivity(intent);
            }
        });
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), R.layout.item_gridview, dayList, calendar);
        gridView.setAdapter(gridAdapter);
    }
}