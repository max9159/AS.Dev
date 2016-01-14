package com.yee.launch.lunchorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import Controller.FoodItemControl;
import lib_self.JsonManager;

public class ActivityMain extends AppCompatActivity {
    private Context context;
    private Button btnCustomList;
    private Button btnTestConnectByAsyncHttp;
    private Button btnTestVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initUI();
        initAction();
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        btnCustomList = (Button) findViewById(R.id.btnCustomList);
        btnTestConnectByAsyncHttp = (Button) findViewById(R.id.btnTestJson);
        btnTestVolley = (Button) findViewById(R.id.btnTestVolley);
    }

    private void initAction() {
        btnCustomList.setOnClickListener(onClick);
        btnTestConnectByAsyncHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonManager jsonManager = new JsonManager();
                jsonManager.AsyncHttpClientConnectTest(context, "http://jdata.azurewebsites.net/test.json");
            }
        });
        btnTestVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodItemControl fdctrl = new FoodItemControl(context);
                fdctrl.LoadFoodListByVolley();
            }
        });
    }

    private View.OnClickListener onClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i = null;
            switch (v.getId()) {
                case R.id.btnCustomList:
                    i = new Intent(context, ActivityCustomList.class);
                    break;

            }

            if (i != null) {
                startActivity(i);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        }
    };

}
