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
public class ServiceScreen extends Service {
    private final String TAG=getClass().getSimpleName();
    private Context context;
    private MyReceiver myReceiver;


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

    private void init() {
        this.context=this;
        this.myReceiver=new MyReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        registerMyReceiver();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
        unregisterMyReceiver();
        reStartService();
        super.onDestroy();
    }

    //register Myeceiver
    private void registerMyReceiver(){
        //set actiion filter
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
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

    //create new Service when it onDesotroy
    //ensure your Service won't be killed by system
    private void reStartService(){
        Intent i=new Intent(context,ServiceScreen.class);
        startService(i);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //filter action from broadcast
            if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                Log.i(TAG,"ACTION_SCREEN_ON");
            }
            if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                Log.i(TAG,"ACTION_SCREEN_OFF");
            }
        }
    }
}
