package io.hamed.alarmmanager;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 11/10/2019
 */
public class TimeChangeService extends Service {

    private TimeChangeReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        IntentFilter mTime = new IntentFilter();
        mTime.addAction(Intent.ACTION_TIME_TICK);
        receiver = new TimeChangeReceiver();
        registerReceiver(receiver, mTime);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
