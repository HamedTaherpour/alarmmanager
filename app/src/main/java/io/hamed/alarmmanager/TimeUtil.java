package io.hamed.alarmmanager;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 11/10/2019
 */
public class TimeUtil {

    @SuppressLint("WrongConstant")
    public static TimeModel getTimeNow() {
        Calendar localCalendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
        String strDate = sdf.format(localCalendar.getTime());
        SimpleDateFormat sdf1 = new SimpleDateFormat("h:mm");
        String strTime = sdf1.format(localCalendar.getTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("aa");
        String strUnit = sdf2.format(localCalendar.getTime());
        return new TimeModel(strDate, strTime, strUnit);
    }

}
