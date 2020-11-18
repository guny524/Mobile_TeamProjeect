package com.example.teamproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;

//그리드뷰 어댑터
public class GridAdapter extends ArrayAdapter {

    private final ArrayList<String> list;
    private final Calendar calendar;

    public GridAdapter(Context context,int resource, ArrayList<String> list, Calendar calendar) {
        super(context, resource, list);
        this.list = list;
        this.calendar = calendar;
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
        }

        TextView tvDay = convertView.findViewById(R.id.tvDay);
        tvDay.setText(getItem(position));

        //오늘 날짜 텍스트 컬러,배경 변경
        String sToday = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
            tvDay.setTextColor(context.getResources().getColor(R.color.colorBlack));
        }else if(position % 7 == 0){
            tvDay.setTextColor(context.getResources().getColor(R.color.colorRedLite));
        }else if(position % 7 == 6){
            tvDay.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        return convertView;
    }
}