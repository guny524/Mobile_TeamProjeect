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


public class PlannerAdapter extends ArrayAdapter<String> implements PopupMenu.OnMenuItemClickListener{

    PlannerAdapter(Context context, ArrayList<String> items){
        super(context, R.layout.item_listview, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_listview, parent, false);
        }


        String item = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.editText);
        textView.setText(item);

        final Button btHigh = (Button) convertView.findViewById(R.id.btHighOrder);
        final Button btPg = (Button) convertView.findViewById(R.id.btProgress);

        final View finalConvertView = convertView;
        btHigh.setOnClickListener(new Button.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          PopupMenu popup = new PopupMenu(getContext(), finalConvertView);
                                          popup.inflate(R.menu.menu_high);

                                          popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                              @Override
                                              public boolean onMenuItemClick(MenuItem menuItem) {

                                                  switch (menuItem.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별
                                                      case R.id.a:
                                                          btHigh.setText("A");
                                                          break;
                                                      case R.id.b:
                                                          btHigh.setText("B");
                                                          break;
                                                      case R.id.c:
                                                          btHigh.setText("C");
                                                          break;
                                                      case R.id.d:
                                                          btHigh.setText("D");

                                                          break;
                                                  }
                                                  return false;
                                              }
                                          });
                                          popup.show();

                                      }
                                  });


        btPg.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    PopupMenu popup = new PopupMenu(getContext(), finalConvertView);
                                    popup.inflate(R.menu.menu_pg);

                                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                        @Override
                                        public boolean onMenuItemClick(MenuItem menuItem) {

                                            switch (menuItem.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별
                                                case R.id.skip:
                                                    btPg.setText("-");
                                                    break;
                                                case R.id.finish:
                                                    btPg.setText("V");
                                                    break;
                                                case R.id.now:
                                                    btPg.setText("*");
                                                    break;
                                                case R.id.next:
                                                    btPg.setText("->");

                                                    break;
                                            }
                                            return false;
                                        }
                                    });
                                    popup.show();

                                }
                            });

        return convertView;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {


        return false;
    }
}
