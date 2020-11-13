package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);


        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int)(display.getWidth()*0.8);
        int height= (int)(display.getHeight()*0.8);

        getWindow().getAttributes().width=width;
        getWindow().getAttributes().height=height;

        final ArrayList<String> items= new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ListAdapter adapter = new plannerAdapter(this,items);
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button) ;

        button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                TextView textView=(TextView)findViewById(R.id.editText);
                items.add(null);
                listView.setAdapter(adapter);
            }
        }) ;




    }

}