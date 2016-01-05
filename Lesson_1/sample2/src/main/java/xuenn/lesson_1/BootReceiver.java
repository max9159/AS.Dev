package xuenn.lesson_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import xuenn.service.ServiceScreen;

/**
 * Created by SsuChi on 12/30/2015.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, ServiceScreen.class);
            //it must use this flag to ensure the service will be start normally
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(i);
        }
    }
}
