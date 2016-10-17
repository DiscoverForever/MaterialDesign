package com.moon.niceweather.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.List;

/**
 * Created by adminr on 2016/10/12.
 */
public class APKInstallUtil {
    /**
     * 安装.apk文件
     *
     * @param context
     */
    public static void install(Context context, String fileName) {
        if (TextUtils.isEmpty(fileName) || context == null) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isInstalledApp(Context context, String packageName)
    {
        Boolean flag = false;

        try
        {
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> pkgs = pm.getInstalledPackages(0);
            for (PackageInfo pkg : pkgs)
            {
                // 当找到了名字和该包名相同的时候，返回
                if ((pkg.packageName).equals(packageName))
                {
                    return flag = true;
                }
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return flag;
    }

}
