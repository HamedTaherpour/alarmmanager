package io.hamed.alarmmanager;

import androidx.annotation.NonNull;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 11/10/2019
 */
public class TimeModel {

    private String date;
    private String time;
    private String unit;

    public TimeModel(String date, String time, String unit) {
        this.date = date;
        this.time = time;
        this.unit = unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @NonNull
    @Override
    public String toString() {
        return date + " | " + time + " | " + unit;
    }
}
