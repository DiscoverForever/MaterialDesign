package com.moon.niceweather.template;

import java.util.ArrayList;

/**
 * Created by adminr on 2016/11/16.
 */
public class WeatherInfo {
    private ArrayList<String> day;
    private ArrayList<String> night;

    public ArrayList<String> getDay() {
        return day;
    }

    public void setDay(ArrayList<String> day) {
        this.day = day;
    }

    public ArrayList<String> getNight() {
        return night;
    }

    public void setNight(ArrayList<String> night) {
        this.night = night;
    }
}
