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
import java.util.List;
import java.util.Random;

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

    int listViewOneExpectedPosition;
    int listViewTwoExpectedPosition;
    int listViewThreeExpectedPosition;

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
                boolean hasConsequence = true;// hasConsequence();
                if(hasConsequence){
                    ArrayList<FoodItem> foodItems1 = getFoodItems(listView);
                    ArrayList<FoodItem> foodItems2 =  getFoodItems(listView2);
                    ArrayList<FoodItem> foodItems3 =  getFoodItems(listView3);
                    listViewOneExpectedPosition = getRandomIndex(foodItems1)+1;
                    FoodItem targetItem = foodItems1.get(listViewOneExpectedPosition);
                    listViewTwoExpectedPosition =  getTargetIndexByTargetItem(foodItems2,targetItem)+1;
                    listViewThreeExpectedPosition =  getTargetIndexByTargetItem(foodItems3,targetItem)+1;
                }else{
                    listViewOneExpectedPosition = listViewTwoExpectedPosition = listViewThreeExpectedPosition = 1;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView,listViewOneExpectedPosition);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView2,listViewTwoExpectedPosition);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView3,listViewThreeExpectedPosition);
                    }
                }).start();
            }
        });
    }

    private int getTargetIndexByTargetItem(ArrayList<FoodItem> foodItems, FoodItem targetItem) {
        int result = -1;
        for(int i=0;i<foodItems.size();i++){
            if(foodItems.get(i).equals(targetItem)){
                result = i;
            }
        }
        return result;
    }

    private ArrayList<FoodItem> getFoodItems(ListView lv){
        return ((MyBaseAdapter)lv.getAdapter()).getFoodItems();
    }

    private int getRandomIndex(ArrayList<FoodItem> foodItemList){
        int itemCount = foodItemList.size();
        int randomIndex = (int)(Math.random()*itemCount);
        return randomIndex;
    }

    private boolean hasConsequence(){
        return Math.random()<0.33;
    }



    private void ControlViewToScroll(ListView lv, int expected_stop_position) {
        int listViewSize = lv.getAdapter().getCount();
        boolean lv_stop = false;
        int lv_move = 2;

        boolean move_slow = false;
        int round_count = 0;
        int expected_stop_round = 2;
        //expected_stop_position = 3;
        Log.d("debug", lv.getId() + ", expected_stop_position: " + expected_stop_position);
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
                if (lv_move > 0) {
                    lv_move = 0 - lv_move;
                }
                round_count++;
                Log.d("debug", lv.getId() + ", round_count++," + lv_last_position + ", " + (listViewSize - 1) + " ," + round_count);
            }
            if (lv_last_position == 2) {
                if (lv_move < 0) {
                    lv_move = Math.abs(lv_move);
                }
                Log.d("debug", lv.getId() + ", Math.abs(lv_move)," + lv_last_position + ", lv_move:" + lv_move);
            }

            if (!lv_stop) {
                lv.smoothScrollToPosition(lv_last_position + lv_move);
                Log.d("debug", lv.getId() + ", Scroll," + lv_last_position + ", " + lv_move);
            }

            try {
                // it helps scrolling to stay smooth as possible (by experiment)
                Thread.sleep(60);
            } catch (InterruptedException e) {

            }
        }


    }


}
