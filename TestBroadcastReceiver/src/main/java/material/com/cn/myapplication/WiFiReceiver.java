package material.com.cn.myapplication;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adminr on 2016/7/27.
 */
public class WiFiReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("action",intent.getAction());
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            Log.e("wifi","wifi状态");
        }
    }

}
