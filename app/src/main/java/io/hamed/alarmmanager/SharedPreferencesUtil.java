package io.hamed.alarmmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesUtil {

    private static final String KEY_TIME = "time";
    private static final String KEY_MESSAGE = "message";
    private static SharedPreferences preferences = null;

    public static void saveTime(Context context, String value) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_TIME, value);
        editor.apply();
    }

    public static String getTime(Context context) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(KEY_TIME, "");
    }

    public static void saveMessage(Context context, String value) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_MESSAGE, value);
        editor.apply();
    }

    public static String getMessage(Context context) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(KEY_MESSAGE, "");
    }

}
