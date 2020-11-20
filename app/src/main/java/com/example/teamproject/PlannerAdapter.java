package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static com.example.teamproject.R.layout.item_listview;


public class PlannerAdapter extends ArrayAdapter<PlanData> {

    private ArrayList<PlanData> list;
    private DBHelper dbHelper;
    private int resource;

    PlannerAdapter(Context context, int resource, ArrayList<PlanData> list){
        super(context, resource, list);
        this.list = list;
        this.resource = resource;
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public PlanData getItem(int position) {
        return list.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }
        final View finalConvertView = convertView;

        //내용 표시
        final PlanData plan = this.getItem(position);
        final Button btProgress = convertView.findViewById(R.id.btProgress);
        btProgress.setText(plan.getProgress());
        final Button btHigh = convertView.findViewById(R.id.btHigh);
        btHigh.setText(plan.getFirstOrder());
        final Button btLow = convertView.findViewById(R.id.btLow);
        btLow.setText(plan.getSecondOrder());
        final TextView etContent = convertView.findViewById(R.id.etContent);
        etContent.setText(plan.getContent());

        //이벤트 리스너들 등록
        btProgress.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), finalConvertView);
                popup.inflate(R.menu.menu_progress);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별
                            case R.id.skip: btProgress.setText("-"); break;
                            case R.id.finish: btProgress.setText("V"); break;
                            case R.id.now: btProgress.setText("*"); break;
                            case R.id.next: btProgress.setText("->"); break;
                        }
                        plan.setProgress(btProgress.getText().toString());
                        dbHelper.update(plan);
                        return true;
                    }
                });
                popup.show();
            }
        });

        btHigh.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), finalConvertView);
                popup.inflate(R.menu.menu_high);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별
                            case R.id.a: btHigh.setText("A"); break;
                            case R.id.b: btHigh.setText("B"); break;
                            case R.id.c: btHigh.setText("C"); break;
                            case R.id.d: btHigh.setText("D"); break;
                        }
                        plan.setFirstOrder(btHigh.getText().toString());
                        dbHelper.update(plan);
                        return true;
                    }
                });
                popup.show();
            }
        });

        btLow.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), finalConvertView);
                popup.inflate(R.menu.menu_low);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별
                            case R.id.a: btLow.setText("1"); break;
                            case R.id.b: btLow.setText("2"); break;
                            case R.id.c: btLow.setText("3"); break;
                            case R.id.d: btLow.setText("4"); break;
                            case R.id.e: btLow.setText("5"); break;
                        }
                        plan.setSecondOrder(btLow.getText().toString());
                        dbHelper.update(plan);
                        return true;
                    }
                });
                popup.show();
            }
        });

        final Button btRemove = convertView.findViewById(R.id.btRemove); //텍스트창을 선택한 상태에서 삭제버튼을 누르고 체크를 누르면 삭제됨
        btRemove.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanData plan = getItem(position);
                list.remove(plan);
                dbHelper.delete(plan.getId());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
