package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static com.example.teamproject.R.layout.item_listview;


public class PlannerAdapter extends ArrayAdapter<String> {

    private final ArrayList<String> list;

    PlannerAdapter(Context context, int resource, ArrayList<String> list){
        super(context, resource, list);
        this.list = list;
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_listview, parent, false);
        }
        final View finalConvertView = convertView;

        String list = this.getItem(position);
        TextView textView = convertView.findViewById(R.id.etContent);
        textView.setText(list);

        final Button btHigh = convertView.findViewById(R.id.btHigh);
        btHigh.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), finalConvertView);
                popup.inflate(R.menu.menu_high);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별
                            case R.id.a: btHigh.setText("A"); return true;
                            case R.id.b: btHigh.setText("B"); return true;
                            case R.id.c: btHigh.setText("C"); return true;
                            case R.id.d: btHigh.setText("D"); return true;
                            default: return false;
                        }
                    }
                });
                popup.show();
            }
        });

        final Button btProgress = convertView.findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, finalConvertView);
                popup.inflate(R.menu.menu_progress);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별
                            case R.id.skip: btHigh.setText("-"); return true;
                            case R.id.finish: btHigh.setText("V"); return true;
                            case R.id.now: btHigh.setText("*"); return true;
                            case R.id.next: btHigh.setText("->"); return true;
                            default: return false;
                        }
                    }
                });
                popup.show();
            }
        });

        return convertView;
    }
}
