package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class plannerAdapter extends ArrayAdapter<String> {
    plannerAdapter(Context context, ArrayList<String> items){
        super(context, R.layout.popuplayout, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater imageInflater = LayoutInflater.from(getContext());
        View view = imageInflater.inflate(R.layout.popuplayout, parent,false);
        String item = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.editText);
        textView.setText(item);
        return view;
    }
}