package material.com.cn.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.lidroid.xutils.BitmapUtils;
import java.util.ArrayList;
import material.com.cn.myapplication.R;

/**
 * Created by adminr on 2016/8/12.
 */
public class ImagListAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater mInflater;
    private ArrayList<String> list = new ArrayList<String>();
    private Drawable drawable;
    private Handler handler;
    private ViewHolder viewHolder;
    private String imgUrl;

    public ImagListAdapter(Context context, ArrayList<String> list) {
        this.mInflater = LayoutInflater.from(context);
        this.mcontext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = null;
        viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.imglist_item, null);
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(mcontext);
        bitmapUtils.display(viewHolder.imgView, list.get(position));
        return convertView;
    }

    public final class ViewHolder {
        private ImageView imgView;
    }

}
