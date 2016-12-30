package com.moon.niceweather.utils;

import android.content.Context;

import com.moon.niceweather.R;

/**
 * Created by Administrator on 2016/12/30.
 */
public class IdToResource {

    /**
     *
     * @param context
     * @param id 图片ID
     * @return
     */
   public static int getPicResource(Context context, String id){
       int resId;//资源ID
       if(id.length()<2){
           resId = context.getResources().getIdentifier("ic_0"+id,"drawable",context.getPackageName());
       }else{
           resId = context.getResources().getIdentifier("ic_"+id,"drawable",context.getPackageName());
       }

        return resId;
    }
}
