package xuenn.lesson_2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {
    private Context context;


    private Button btnStart;
    private Button btnStop;

    private TextView tvTimer;

    private Thread thread;

    private int timeCurrent=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initUI();
        initAction();
    }

    private void init() {
        this.context=this;
    }

    private void initUI() {
        btnStart= (Button) findViewById(R.id.btnStart);
        btnStop= (Button) findViewById(R.id.btnStop);
        tvTimer= (TextView) findViewById(R.id.tvTimer);
    }

    private void initAction() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startThread();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopThread();
            }
        });
    }

    //using Handler to turn thread back to UI thread
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tvTimer.setText("current time : " + msg.arg1);
            super.handleMessage(msg);
        }
    };


    private void startThread(){
        stopThread();
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //do something you want without UI relative

                while (true){
                    //await for 1 second
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //must use "break" here, or the Thread won't stopped by Thread.interrupt()
                        break;
                    }

                    timeCurrent+=1;

                    //There are 2 method to go back to UI thread

                    //Method 1
                    //set Message, put some params you want at (what arg1 arg2 obj)
                    //Message will send to Handler
                    Message message=new Message();
                    message.what=0;
                    message.arg1=timeCurrent;
                    handler.sendMessage(message);

                    //Method 1
                    //use Handler Post
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            tvTimer.setText("current time : " + mtimeCurrent);
//                        }
//                    });

                    //you can also use PostDelayed to do something after few second
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            tvTimer.setText("current time : " + timeCurrent);
//                        }
//                    }, 5000);
                }

            }
        });
        thread.start();
    }

    private void stopThread(){
        if(thread!=null){
            if(thread.isAlive()){
                thread.interrupt();
            }
            thread=null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //thread must stop when the app closed
        //or it will keep working
        stopThread();
    }
}
