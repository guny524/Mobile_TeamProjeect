package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlannerAdapter extends ArrayAdapter<String> {
    public PlannerAdapter(Context context, ArrayList<String> items){
        super(context, R.layout.popuplayout, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.popuplayout, parent,false);
        }

        String item = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.editText);
        textView.setText(item);
        return convertView;
    }
}