package com.moon.niceweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.moon.niceweather.R;
import com.moon.niceweather.template.ResponseTemplate;
import com.moon.niceweather.template.WeatherItem;
import com.moon.niceweather.utils.IdToResource;

import java.util.ArrayList;

/**
 * Created by adminr on 2016/11/17.
 */
public class WeatherListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<WeatherItem> data;
    private LayoutInflater mInflater = null;

    public WeatherListAdapter(Context context, ResponseTemplate data) {
        this.context = context;
        this.data = data.getResult().getData().getWeather();
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_list_weather, null);
            ViewUtils.inject(viewHolder, convertView);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_week.setText("星期" + data.get(position).getWeek());
        viewHolder.tv_temperature_min.setText(data.get(position).getInfo().getDay().get(2));
        viewHolder.tv_temperature_max.setText(data.get(position).getInfo().getNight().get(2));
        viewHolder.iv_weather.setBackgroundResource(IdToResource.getPicResource(context,data.get(position).getInfo().getDay().get(0)));
        convertView.setTag(viewHolder);
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.tv_week)
        private TextView tv_week;
        @ViewInject(R.id.tv_temperature_min)
        private TextView tv_temperature_min;
        @ViewInject(R.id.tv_temperature_max)
        private TextView tv_temperature_max;
        @ViewInject(R.id.iv_weather)
        private ImageView iv_weather;
    }
}
