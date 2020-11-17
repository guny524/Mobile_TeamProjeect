package com.example.teamproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import static android.app.PendingIntent.getActivity;
import static com.example.teamproject.R.layout.popuplayout;


public class PlannerAdapter extends ArrayAdapter<String> {
    PlannerAdapter(Context context, ArrayList<String> items){
        super(context, R.layout.popuplayout, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(popuplayout, parent, false);
        }


        String item = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.editText);
        textView.setText(item);


        return convertView;
    }
}


