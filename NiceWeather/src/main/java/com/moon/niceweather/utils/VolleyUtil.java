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
    private String BaseUrl = "http://wthrcdn.etouch.cn/weather_mini?city=北京";
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

    public void doGet() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl, null, SuccessListener, ErrorListener);
        mQueue.add(jsonObjectRequest);
    }

    public void doPost() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,BaseUrl,null,SuccessListener, ErrorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name1", "value1");
                map.put("name2", "value2");

                return map;
            }
        };

    }
}
