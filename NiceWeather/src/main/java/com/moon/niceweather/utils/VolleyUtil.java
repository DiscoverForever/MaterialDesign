package com.moon.niceweather.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adminr on 2016/10/17.
 */
public class VolleyUtil {
    private RequestQueue mQueue;
    private Context mContext;
    private Response.Listener<JSONObject> SuccessListener;
    private Response.ErrorListener ErrorListener;

    public VolleyUtil(Context context, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        mContext = context;
        mQueue = Volley.newRequestQueue(mContext);
        SuccessListener = successListener;
        ErrorListener = errorListener;
    }

    public void doGet(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, SuccessListener, ErrorListener);
        mQueue.add(jsonObjectRequest);
    }

    public void doPost(String url, final HashMap<String, String> data) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, SuccessListener, ErrorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return data;
            }
        };

    }

    /**
     * 根据经纬度获取地址
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public String getLocationUrl(String latitude, String longitude) {
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=pPGNKs75nVZPloDFuppTLFO3WXebPgXg&location=" + latitude + "," + longitude + "&output=json&pois=0&coordtype=wgs84ll";
        return url;
    }

    /**
     * 根据城市名获取天气
     *
     * @param cityname
     * @return
     */
    public String getWeatherUrl(String cityname) {
        String url = "http://op.juhe.cn/onebox/weather/query?cityname=" + cityname + "&dtype=json&key=9dc35ae43b953f41d7d299933a9a2abb";
        return url;
    }

}
