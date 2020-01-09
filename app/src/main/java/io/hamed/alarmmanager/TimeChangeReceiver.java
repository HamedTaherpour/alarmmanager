package io.hamed.alarmmanager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

import static io.hamed.alarmmanager.MainActivity.TAG;


/**
 * Author: Hamed Taherpour
 * *
 * Created: 11/10/2019
 */
public class TimeChangeReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            String time = SharedPreferencesUtil.getTime(context);
            String message = SharedPreferencesUtil.getMessage(context);
            TimeModel timeModel = TimeUtil.getTimeNow();
            if (time.equalsIgnoreCase(timeModel.getTime())) {
                Log.i(TAG, "onReceive: " + timeModel.toString());
                showNotification(context, message);
            }
        }
    }

    private void showNotification(Context context, String message) {
//        int notificationId = generateRandom();
        int notificationId = 123;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_alarm_on)
                .setContentTitle("Alarm")
                .setContentText(message);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.notify(notificationId, builder.build());
        } else {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificationId, builder.build());
        }
        Log.i(TAG, "showNotification: " + notificationId);
    }

    private int generateRandom() {
        int min = 1;
        int max = 121;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
