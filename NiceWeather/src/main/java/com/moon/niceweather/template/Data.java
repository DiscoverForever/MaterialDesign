package com.moon.niceweather.template;

import java.util.ArrayList;

/**
 * Created by adminr on 2016/11/16.
 */
public class Data {
    private String date;
    private F3h f3h;
    private String isForeign;
    private String jingqu;
    private String jingqutq;
    private Life life;
    private PM25 pm25;
    private String pubdate;
    private String pubtime;
    private Realtime realtime;
    private ArrayList<WeatherItem> weather;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public F3h getF3h() {
        return f3h;
    }

    public void setF3h(F3h f3h) {
        this.f3h = f3h;
    }

    public String getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(String isForeign) {
        this.isForeign = isForeign;
    }

    public String getJingqu() {
        return jingqu;
    }

    public void setJingqu(String jingqu) {
        this.jingqu = jingqu;
    }

    public String getJingqutq() {
        return jingqutq;
    }

    public void setJingqutq(String jingqutq) {
        this.jingqutq = jingqutq;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public PM25 getPm25() {
        return pm25;
    }

    public void setPm25(PM25 pm25) {
        this.pm25 = pm25;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public Realtime getRealtime() {
        return realtime;
    }

    public void setRealtime(Realtime realtime) {
        this.realtime = realtime;
    }

    public ArrayList<WeatherItem> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherItem> weather) {
        this.weather = weather;
    }
}
