package xuenn.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by SsuChi on 12/30/2015.
 */
public class ServiceHello extends Service {
    //get simple name from this class (ActivityMain)
    private final String TAG=getClass().getSimpleName();
    private Context context;
    private MyReceiver myReceiver=new MyReceiver();

    public static final String RECEIVER_ACTION="xuenn.serviceHello";
    public static final String RECEIVER_INTENT_WEATHER="weather";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate");
        init();
        super.onCreate();
    }

    private void init(){
        this.context=this;
        this.myReceiver=new MyReceiver();
    }

    //register Myeceiver
    private void registerMyReceiver(){
        //set actiion filter
        IntentFilter intentFilter=new IntentFilter(RECEIVER_ACTION);
        //register receiver
        registerReceiver(myReceiver, intentFilter);
    }


    //unregister Myeceiver
    private void unregisterMyReceiver(){
        if(myReceiver!=null){
            try {
                unregisterReceiver(myReceiver);
            } catch (Exception e) {
                //if MyReveiver already unregister, it will be here
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        //Register My Receiver when service onStartCommand
        registerMyReceiver();


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");

        //unregister My Receiver when service onDestroy
        unregisterMyReceiver();

        //re start service when service killed by system or user
        //reStartService();

        super.onDestroy();
    }

    //create new Service when it onDesotroy
    //ensure your Service won't be killed by system
    private void reStartService(){
        Intent i=new Intent(context,ServiceHello.class);
        startService(i);
    }


    private class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //filter action from broadcast
            if(intent.getAction().equals(RECEIVER_ACTION)){
                //get intent extra from broadcast
                String weather =intent.getStringExtra(RECEIVER_INTENT_WEATHER);
                Log.i(TAG,"get broadcast");
                Log.i(TAG,"weather is "+weather);
            }
        }
    }
}
