package io.hamed.alarmmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "@MY_APP";
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    private TimePicker timePicker;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.time_picker);
        etMessage = findViewById(R.id.et_message);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                checkPermissions();
                break;
            case R.id.btn_stop:
                stopService(new Intent(this, TimeChangeService.class));
                break;
        }
    }

    private void startService() {
        setReminder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, TimeChangeService.class));
        }
        startService(new Intent(this, TimeChangeService.class));
    }

    private void setReminder() {
        String message = etMessage.getText().toString();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        if (hour >= 13)
            hour -= 12;
        String time = hour + ":" + minute;
        SharedPreferencesUtil.saveTime(this, time);
        SharedPreferencesUtil.saveMessage(this, message);
        Log.i(TAG, "setReminder: " + time);
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            startService();
        } else {
            int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.FOREGROUND_SERVICE);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.FOREGROUND_SERVICE);
                if (!listPermissionsNeeded.isEmpty()) {
                    ActivityCompat.requestPermissions(this,
                            listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                }
            } else {
                startService();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.FOREGROUND_SERVICE);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            startService();
        } else {
            Toast.makeText(this, "need permissions ", Toast.LENGTH_SHORT).show();
        }
    }
}
