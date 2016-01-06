package com.yee.launch.lunchorder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMain extends AppCompatActivity {
    private Context context;
    private Button btnCustomList;


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
    }

    private void initAction() {
        btnCustomList.setOnClickListener(onClick);
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
