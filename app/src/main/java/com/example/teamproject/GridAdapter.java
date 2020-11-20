package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class GridAdapter extends ArrayAdapter {

    private final ArrayList<DayData> list;
    private final Calendar calendar;

    public GridAdapter(Context context, int resource, ArrayList<DayData> list, Calendar calendar) {
        super(context, resource, list);
        this.list = list;
        this.calendar = calendar;
    }

    @Override
    public DayData getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
        }

        DayData dayData = this.getItem(position);

        //날짜 표시
        TextView tvDay = convertView.findViewById(R.id.tvDay);
        if(dayData.getDay()<=0) {  //공백으로 넣은 부분
            tvDay.setVisibility(View.INVISIBLE);
        }
        else {  //날짜 있는 부분
            //오늘 날짜 텍스트 컬러 변경
            tvDay.setText(Integer.toString(dayData.getDay()));
            String sToday = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            if (sToday.equals(this.getItem(position))) { //오늘 day 텍스트 컬러 변경
                tvDay.setTextColor(context.getResources().getColor(R.color.colorBlack));
            }else if(position % 7 == 0){
                tvDay.setTextColor(context.getResources().getColor(R.color.colorRedLite));
            }else if(position % 7 == 6){
                tvDay.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
        }

        //내용 표시
        ArrayList<PlanData> plans = dayData.getPlans();
        for( int i=1; i<=3; i++) {  //xml에 있는 3개 tv 찾아서 설정해줄거임
            TextView tv = convertView.findViewById(context.getResources().getIdentifier("tv" + i, "id", context.getPackageName()));
            if(plans==null || plans.isEmpty() == true) {  //내용 없는 경우
                tv.setVisibility(View.INVISIBLE);
            } else if(i <= plans.size()) {
                //7자 넘어가면 줄 넘어서 밑으로 내려가니까 잘라줌
                String content = plans.get(i-1).getContent();
                if(content.length() >= 7) {
                    tv.setText(content.substring(0,7));
                } else {
                    tv.setText(content);
                }
            } else {
                tv.setVisibility(View.INVISIBLE);
            }
        }
        if(plans!=null && plans.size() > 3) {
            TextView tv = convertView.findViewById(R.id.tv3);
            tv.setText("...");
        }

        return convertView;
    }
}