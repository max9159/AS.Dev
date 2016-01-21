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
                if (hasConsequence) {
                    ArrayList<FoodItem> foodItems1 = getFoodItems(listView);
                    ArrayList<FoodItem> foodItems2 = getFoodItems(listView2);
                    ArrayList<FoodItem> foodItems3 = getFoodItems(listView3);
                    listViewOneExpectedPosition = getRandomIndex(foodItems1);
                    FoodItem targetItem = foodItems1.get(listViewOneExpectedPosition);
                    listViewTwoExpectedPosition = getTargetIndexByTargetItem(foodItems2, targetItem);
                    listViewThreeExpectedPosition = getTargetIndexByTargetItem(foodItems3, targetItem);
                } else {
                    listViewOneExpectedPosition = listViewTwoExpectedPosition = listViewThreeExpectedPosition = 1;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView, listViewOneExpectedPosition);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView2, listViewTwoExpectedPosition);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ControlViewToScroll(listView3, listViewThreeExpectedPosition);
                    }
                }).start();
            }
        });
    }


    private int getTargetIndexByTargetItem(ArrayList<FoodItem> foodItems, FoodItem targetItem) {
        int result = -1;
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).equals(targetItem)) {
                result = i;
            }
        }
        return result;
    }

    private ArrayList<FoodItem> getFoodItems(ListView lv) {
        return ((MyBaseAdapter) lv.getAdapter()).getFoodItems();
    }

    private int getRandomIndex(ArrayList<FoodItem> foodItemList) {
        int itemCount = foodItemList.size();
        int randomIndex = (int) (Math.random() * (itemCount - 2) + 1);//-1:for position start with 0 // -1:do not randomly get last item // +1:do not randomly get first item

        Log.d("getRandomIndex", "randomIndex:" + randomIndex);
        return randomIndex;
    }

    private boolean hasConsequence() {
        return Math.random() < 0.33;
    }

    private void ControlViewToScroll(ListView lv, int expected_stop_position) {
        int listViewSize = lv.getAdapter().getCount();
        boolean lv_stop = false;
        int lv_move = 2;

        boolean final_round = false;
        int round_count = 0;
        int expected_stop_round = 5;


        Log.i("ControlViewToScroll", lv.getId() + ", expected_stop_position: " + expected_stop_position);

        int check_position_change = -1;
        while (true) {

            int lv_last_position = lv.getLastVisiblePosition();

            //check expected stop round is coming
            if (round_count >= expected_stop_round) {

                if (final_round == false) {
                    lv_move = 1;
                    final_round = true;
                    Log.i("ControlViewToScroll", lv.getId() + ", In final round");
                }
                Log.d("ControlViewToScroll", lv.getId() + ", round:" + round_count + ", lv_last_position:" + lv_last_position);
                if (lv_last_position == expected_stop_position) {
                    lv_stop = true;
                    Log.d("ControlViewToScroll", lv.getId() + ", Break, lv_last_position:" + lv_last_position);
                    break;
                }
            }


            if (!lv_stop) {


                //keep scroll
                if (lv_last_position == listViewSize - 1) {
                    if (lv_move > 0) {
                        lv_move = 0 - lv_move;
                    }
                    //lv.invalidateViews();
                    round_count++;
                    Log.d("ControlViewToScroll", lv.getId() + ", round_count++," + lv_last_position + ", " + (listViewSize - 1) + " ," + round_count);
                }
                if (lv_last_position == 1) {
                    if (lv_move < 0) {
                        lv_move = Math.abs(lv_move);
                    }
                    Log.d("ControlViewToScroll", lv.getId() + ", Math.abs(lv_move)," + lv_last_position + ", lv_move:" + lv_move);
                }

                //trigger scroll
                lv.smoothScrollToPosition(lv_last_position + lv_move);
                Log.d("ControlViewToScroll", lv.getId() + ", Scroll," + lv_last_position + ", " + lv_move);
            }

            try {
                // it helps scrolling to stay smooth as possible (by experiment)
                Thread.sleep(60);
            } catch (InterruptedException e) {

            }
        }


    }


}
