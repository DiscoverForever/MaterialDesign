package material.com.cn.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adminr on 2016/7/29.
 */
public class InOrUnStallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {  // install
            String packageName = intent.getDataString();
            Log.i("homer", "安装了 :" + packageName);
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) { // uninstall
            String packageName = intent.getDataString();
            Log.i("homer", "卸载了 :" + packageName);
        }
    }
}
