package com.yee.launch.lunchorder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                        int listViewSize = listView.getAdapter().getCount();

                        for (int index = 0; index < listViewSize; index++) {
                            listView.smoothScrollToPositionFromTop(listView.getLastVisiblePosition() + 100, 5, 1000);
                            listView2.smoothScrollToPositionFromTop(listView2.getLastVisiblePosition() + 100, 5, 1000);
                            listView3.smoothScrollToPositionFromTop(listView3.getLastVisiblePosition() + 100, 5, 1000);
                            try {
                                // it helps scrolling to stay smooth as possible (by experiment)
                                Thread.sleep(60);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }).start();

            }
        });
    }


}
