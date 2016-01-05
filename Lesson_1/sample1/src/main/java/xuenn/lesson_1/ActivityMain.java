package xuenn.lesson_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import xuenn.service.ServiceHello;

public class ActivityMain extends AppCompatActivity {

    //get simple name from this class (ActivityMain)
    private final String TAG=getClass().getSimpleName();

    private Context context;
    private Button btnOpen;
    private Button btnFinish;
    private Button btnStartService;
    private Button btnStopService;
    private Button btnSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");


        init();
        initUI();
        initAction();

    }


    private void init() {
        this.context = this;

    }

    //connect views from layout
    private void initUI() {
        btnOpen = (Button) findViewById(R.id.btnOpen);
        btnFinish= (Button) findViewById(R.id.btnFinish);
        btnStartService= (Button) findViewById(R.id.btnStartService);
        btnStopService= (Button) findViewById(R.id.btnStopService);
        btnSendBroadcast= (Button) findViewById(R.id.btnSendBroadcast);
    }

    //init views action
    private void initAction() {
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish activity
                finish();
            }
        });
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openService();
            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeService();
            }
        });
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcastToService();
            }
        });
    }

    //Open Activity 2 with extra data
    private void openActivity2() {
        Intent i=new Intent(context,ActivityMain2.class);
        i.putExtra(ActivityMain2.INTENT_NAME,"Jessica");
        i.putExtra(ActivityMain2.INTENT_AGE, 18);
        i.putExtra(ActivityMain2.INTENT_GNENDER, true);
        startActivity(i);

        //open new activity with animation
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        //open new activity without animation
        //overridePendingTransition(0,0);
    }

    //start service
    private void openService(){
        Intent i=new Intent(context, ServiceHello.class);
        startService(i);
    }

    //stop service
    private void closeService(){
        Intent i=new Intent(context, ServiceHello.class);
        stopService(i);
    }

    //send broadcast with extra data
    private void sendBroadcastToService(){
        Intent i=new Intent(ServiceHello.RECEIVER_ACTION);
        i.putExtra(ServiceHello.RECEIVER_INTENT_WEATHER,"cloudy");
        sendBroadcast(i);
    }





    //life cycle test
    @Override
    protected void onStart() {
        Log.i(TAG,"onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }
}
