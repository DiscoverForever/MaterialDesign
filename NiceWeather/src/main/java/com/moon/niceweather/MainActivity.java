package com.moon.niceweather;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.moon.niceweather.activity.LoginActivity;
import com.moon.niceweather.adapter.WeatherListAdapter;
import com.moon.niceweather.template.MyLocation;
import com.moon.niceweather.template.ResponseTemplate;
import com.moon.niceweather.template.Temperature;
import com.moon.niceweather.utils.IdToResource;
import com.moon.niceweather.utils.VolleyUtil;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewInject(R.id.hs_day_details)
    private LinearLayout hs_day_details;
    @ViewInject(R.id.pull_to_refresh)
    private PullToRefreshView mPullToRefreshView;
    @ViewInject(R.id.imageView)
    private NetworkImageView netImageView;
    @ViewInject(R.id.lv_weather)
    private ListView lv_weather;
    @ViewInject(R.id.tv_city_name)
    private TextView tv_city_name;
    @ViewInject(R.id.tv_week)
    private TextView tv_week;
    @ViewInject(R.id.tv_temperature)
    private TextView tv_temperature;
    @ViewInject(R.id.tv_weather)
    private TextView tv_weather;
    @ViewInject(R.id.tv_weather_description)
    private TextView tv_weather_description;
    @ViewInject(R.id.tv_sunrise)
    private TextView tv_sunrise;
    @ViewInject(R.id.tv_sunset)
    private TextView tv_sunset;
    @ViewInject(R.id.tv_today_temperature)
    private TextView tv_today_temperature;
    @ViewInject(R.id.tv_humidity)
    private TextView tv_humidity;
    @ViewInject(R.id.tv_wind_speed)
    private TextView tv_wind_speed;
    @ViewInject(R.id.tv_temperature_min)
    private TextView tv_temperature_min;
    @ViewInject(R.id.tv_temperature_max)
    private TextView tv_temperature_max;


    /* GPS Constant Permission */
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;

    /* Position */
    private static final int MINIMUM_TIME = 1000;  // 1s 定位时间触发间隔(ms)
    private static final int MINIMUM_DISTANCE = 1; // 1m 定位位置触发精度(m)

    private Context context;
    private LocationManager locationManager;
    private String locationProvider;
    private Location location;
    /* 当前城市名 */
    private String cityName = "";
    /* 当前温度 */
    private String temperatureNow = "";
    /* 白天最高温度 */
    private String temperatureMin = "";
    /* 晚上最高温度 */
    private String temperatureMax = "";
    /* 当前天气状况 */
    private String weather = "";
    /* 星期 */
    private String week = "";
    /* 风速 */
    private String windSpeed = "";
    private LayoutInflater mLayoutInflater;
    private View galleryItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        init();

    }

    /**
     * 初始化
     */
    private void init() {
        context = this;
        initLocation();//初始化定位
        initPullToRefreshView();//初始化下拉刷新
    }

    /**
     * 初始化当天温度详细
     *
     * @param response
     */
    private void initHorizontalScrollView(ResponseTemplate response) {
        mLayoutInflater = LayoutInflater.from(context);

        ArrayList<Temperature> temperatureList = response.getResult().getData().getF3h().getTemperature();

        for (int i = 0; i < temperatureList.size(); i++) {

            galleryItem = mLayoutInflater.inflate(R.layout.gallery_item, null);
            TextView hour = (TextView) galleryItem.findViewById(R.id.tv_hour);
            ImageView weatherImg = (ImageView) galleryItem.findViewById(R.id.iv_weather);
            TextView temperature = (TextView) galleryItem.findViewById(R.id.tv_temperature);
            hour.setText(getTime(temperatureList.get(i).getJg()));
            temperature.setText(temperatureList.get(i).getJb());
            weatherImg.setBackgroundResource(IdToResource.getPicResource(context,response.getResult().getData().getRealtime().getWeather().getImg()));
            hs_day_details.addView(galleryItem);
        }

    }

    /**
     * 获取时间上下午
     *
     * @param time
     * @return
     */
    public String getTime(String time) {

        if (Integer.parseInt(time.substring(8, 10)) < 12) {

            time = "上午" + time.substring(8, 10);
        } else {
            time = "下午" + time.substring(8, 10);
        }

        return time;
    }

    /**
     * 阿拉伯数字转中文星期几
     *
     * @param weekNum
     * @return
     */
    public String getWeek(String weekNum) {
        switch (weekNum) {
            case "1":
                weekNum = "星期一";
                break;
            case "2":
                weekNum = "星期二";
                break;
            case "3":
                weekNum = "星期三";
                break;
            case "4":
                weekNum = "星期四";
                break;
            case "5":
                weekNum = "星期五";
                break;
            case "6":
                weekNum = "星期六";
                break;
            case "7":
                weekNum = "星期日";
                break;

            default:
                weekNum = "未知";
                break;
        }
        return weekNum;
    }

    /**
     * 初始化近一周天气
     */
    private void initNearlyWeekWeather(ResponseTemplate data) {
        WeatherListAdapter adapter = new WeatherListAdapter(this, data);
        lv_weather.setAdapter(adapter);
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        locationManager = (LocationManager) this.getSystemService(context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        locationProvider = locationManager.getBestProvider(criteria, true);//选择最优定位
        // API 23: we have to check if ACCESS_FINE_LOCATION and/or ACCESS_COARSE_LOCATION permission are granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // No one provider activated: prompt GPS
            if (locationProvider == null || locationProvider.equals("")) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }

            // At least one provider activated. Get the coordinates
            locationManager.requestLocationUpdates(locationProvider, MINIMUM_TIME, MINIMUM_DISTANCE, locationListener);
            switch (locationProvider) {
                case "passive":
                    location = locationManager.getLastKnownLocation(locationProvider);
                    if (location != null) {
                        VolleyUtil volleyUtil = new VolleyUtil(this, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Gson gson = new Gson();
                                MyLocation myLocation = gson.fromJson(jsonObject.toString(), MyLocation.class);
                                cityName = myLocation.getResult().getAddressComponent().getCity();
                                Message msg = new Message();
                                msg.what = 1;
                                mhandler.sendMessage(msg);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        volleyUtil.doGet(volleyUtil.getLocationUrl("" + location.getLatitude(), "" + location.getLongitude(), "wgs84ll"));
                    } else {
                        Toast.makeText(MainActivity.this, "请打开网络或GPS开关", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "network":
                    location = locationManager.getLastKnownLocation(locationProvider);
                    if (location != null) {
                        VolleyUtil volleyUtil = new VolleyUtil(this, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Gson gson = new Gson();
                                MyLocation myLocation = gson.fromJson(jsonObject.toString(), MyLocation.class);
                                cityName = myLocation.getResult().getAddressComponent().getCity();
                                Message msg = new Message();
                                msg.what = 1;
                                mhandler.sendMessage(msg);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        volleyUtil.doGet(volleyUtil.getLocationUrl("" + location.getLatitude(), "" + location.getLongitude(), "wgs84ll"));
                    } else {
                        Toast.makeText(MainActivity.this, "请打开网络开关", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "gps":
                    location = locationManager.getLastKnownLocation(locationProvider);
                    Toast.makeText(context, "" + location, Toast.LENGTH_SHORT).show();
                    if (location != null) {
                        VolleyUtil volleyUtil = new VolleyUtil(this, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Gson gson = new Gson();
                                MyLocation myLocation = gson.fromJson(jsonObject.toString(), MyLocation.class);
                                cityName = myLocation.getResult().getAddressComponent().getCity();
                                Message msg = new Message();
                                msg.what = 1;
                                mhandler.sendMessage(msg);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        volleyUtil.doGet(volleyUtil.getLocationUrl("" + location.getLatitude(), "" + location.getLongitude(), "gps"));
                        String locationStr = "维度：" + location.getLatitude() + "\n"
                                + "经度：" + location.getLongitude();
                    } else {
                        Toast.makeText(MainActivity.this, "请打开GPS开关", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:

                    break;

            }

            // One or both permissions are denied.
        } else {
            Toast.makeText(MainActivity.this, "拒绝定位", Toast.LENGTH_SHORT).show();

            // The ACCESS_COARSE_LOCATION is denied, then I request it and manage the result in
            // onRequestPermissionsResult() using the constant MY_PERMISSION_ACCESS_FINE_LOCATION
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSION_ACCESS_COARSE_LOCATION);
            }
            // The ACCESS_FINE_LOCATION is denied, then I request it and manage the result in
            // onRequestPermissionsResult() using the constant MY_PERMISSION_ACCESS_FINE_LOCATION
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_ACCESS_FINE_LOCATION);
            }

        }
    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {


        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            String locationStr = "维度：" + location.getLatitude() + "\n"
                    + "经度：" + location.getLongitude();
            Message msg = new Message();
            msg.what = 1;
            mhandler.sendMessage(msg);

        }
    };


    /**
     * 获取天气信息
     */
    private void getWeatherData() {
        VolleyUtil volley = new VolleyUtil(this, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                Gson gson = new Gson();
                ResponseTemplate response = gson.fromJson(jsonObject.toString(), ResponseTemplate.class);
                updateDataToUI(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        if (!cityName.equals("")) {

            volley.doGet(volley.getWeatherUrl(cityName));
        }

    }

    /**
     * 更新数据&&UI
     */
    private void updateDataToUI(ResponseTemplate response) {
        cityName = response.getResult().getData().getRealtime().getCity_name();
        temperatureNow = response.getResult().getData().getRealtime().getWeather().getTemperature();
        weather = response.getResult().getData().getRealtime().getWeather().getInfo();
        week = response.getResult().getData().getRealtime().getWeek();
        temperatureMin = response.getResult().getData().getWeather().get(0).getInfo().getNight().get(2);
        temperatureMax = response.getResult().getData().getWeather().get(0).getInfo().getDay().get(2);
        windSpeed = response.getResult().getData().getRealtime().getWind().getPower();
        tv_temperature_min.setText(temperatureMin);
        tv_temperature_max.setText(temperatureMax);
        tv_weather.setText(weather);
        tv_city_name.setText(cityName);
        tv_temperature.setText(temperatureNow + "°");
        tv_week.setText(getWeek(week));
        tv_wind_speed.setText(windSpeed);
        initNearlyWeekWeather(response);
        initHorizontalScrollView(response);
        initWeatherDescription(response);
        initTodayWeather(response);
    }

    /**
     * 初始化当天天气具体信息
     *
     * @param response
     */
    private void initTodayWeather(ResponseTemplate response) {
        tv_sunrise.setText("上午" + response.getResult().getData().getWeather().get(0).getInfo().getDay().get(5));
        tv_sunset.setText("下午" + response.getResult().getData().getWeather().get(0).getInfo().getNight().get(5));
        tv_today_temperature.setText(response.getResult().getData().getWeather().get(0).getInfo().getDay().get(0) + "°");
        tv_humidity.setText(response.getResult().getData().getRealtime().getWeather().getHumidity());
    }

    /**
     * 初始化当天天气描述和推荐
     *
     * @param response
     */
    private void initWeatherDescription(ResponseTemplate response) {
        tv_weather_description.setText("空气质量" + response.getResult().getData().getPm25().getPm25().getQuality() + "," + response.getResult().getData().getPm25().getPm25().getDes());
    }


    /**
     * 初始化下拉刷新
     */
    private void initPullToRefreshView() {
        mPullToRefreshView.setOnRefreshListener(refreshListener);
    }

    /**
     * 下拉刷新监听器
     */
    private PullToRefreshView.OnRefreshListener refreshListener = new PullToRefreshView.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPullToRefreshView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getWeatherData();
                    mPullToRefreshView.setRefreshing(false);
                }
            }, 1000);
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                } else {
                    // permission denied
                }
                break;
            }
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                } else {
                    // permission denied
                }
                break;
            }

        }
    }

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    getWeatherData();
                    break;
                default:
                    break;
            }
        }
    };
}
