package com.yee.launch.lunchorder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.FoodItemControl;
import Model.FoodItem;
import lib_self.MyBaseAdapter;

public class ActivityCustomList extends AppCompatActivity {

    private Context context;
    private ListView listView;
    private ListView listView2;
    private ListView listView3;
    private MyBaseAdapter myAdapter;
    private ArrayList<FoodItem> foodItems;

    private Button btnLaunch;
    private FoodItemControl fdctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        init();
        initUI();
        initAction();
//        initListView(listView);
//        initListView(listView2);
//        initListView(listView3);
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        this.listView = (ListView) findViewById(R.id.listView);
        this.listView2 = (ListView) findViewById(R.id.listView2);
        this.listView3 = (ListView) findViewById(R.id.listView3);
        this.btnLaunch = (Button) findViewById(R.id.btnLaunch);
    }

    private void initAction() {

        this.fdctrl = new FoodItemControl(context);
        this.fdctrl.LoadFoodListByVolley();
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(context, cardItems.get(i).getName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, foodItems.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        this.btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView2);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView3);
                    }
                }).start();
            }
        });
    }

    private void ControlViewToScroll(ListView lv) {
        int listViewSize = lv.getAdapter().getCount();
        boolean lv_stop = false;
        int lv_move = 2;

        boolean move_slow = false;
        int round_count = 0;
        int expected_stop_round = 5;
        int expected_stop_position = 3;

        while (true) {

            int lv_last_position = lv.getLastVisiblePosition();

            //check expected stop round is coming
            if (round_count >= expected_stop_round) {

                if (move_slow == false) {
                    lv_move = 1;
                    move_slow = true;
                    Log.d("debug", lv.getId() + ", In final round");
                }
                Log.d("debug", lv.getId() + ",round" + round_count + ", lv_last_position" + lv_last_position);
                if (lv_last_position == expected_stop_position) {
                    lv_stop = true;
                    Log.d("debug", lv.getId() + ",Break");
                    break;
                }
            }

            //keep scroll
            if (lv_last_position == listViewSize - 1) {
                lv_move = 0 - lv_move;
                round_count++;
            }
            if (lv_last_position == 2) {
                lv_move = Math.abs(lv_move);
            }

            if (!lv_stop) {
                lv.smoothScrollToPosition(lv_last_position + lv_move);
            }

            try {
                // it helps scrolling to stay smooth as possible (by experiment)
                Thread.sleep(60);
            } catch (InterruptedException e) {

            }
        }


    }


}
