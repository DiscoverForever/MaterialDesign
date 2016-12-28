package com.moon.niceweather.template;

/**
 * Created by adminr on 2016/11/16.
 */
public class PM25 {
    private String cityName;
    private String dateTime;
    private String key;
    private PM25Child pm25;
    private int show_desc;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PM25Child getPm25() {
        return pm25;
    }

    public void setPm25(PM25Child pm25) {
        this.pm25 = pm25;
    }

    public int getShow_desc() {
        return show_desc;
    }

    public void setShow_desc(int show_desc) {
        this.show_desc = show_desc;
    }


}
