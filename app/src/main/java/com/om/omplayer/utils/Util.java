package com.om.omplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.om.omplayer.AppCore;
import com.om.omplayer.pojo.DataBean;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class Util {

    private static Gson GSON;

    public static Gson getParser() {
        if (GSON == null)
            GSON = new GsonBuilder().create();
        return GSON;
    }

    private static String loadJSONFromAsset(Context context, @NonNull String jsonFileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static DataBean loadBannerJSONFromAsset() {
        String json = loadJSONFromAsset(AppCore.getContext(), "banner.json");
        DataBean bean = getParser().fromJson(json, DataBean.class);
        return bean;
    }

    public static DataBean loadNewSongsJSONFromAsset() {
        String json = loadJSONFromAsset(AppCore.getContext(), "new_songs.json");
        DataBean bean = getParser().fromJson(json, DataBean.class);
        return bean;
    }

    public static DataBean loadTrendingSongsJSONFromAsset() {
        String json = loadJSONFromAsset(AppCore.getContext(), "trending_songs.json");
        DataBean bean = getParser().fromJson(json, DataBean.class);
        return bean;
    }

    public static int getColumnCount(Activity activity) {
        if(activity == null)
            return 2;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float density = activity.getResources().getDisplayMetrics().density;
        float dpWidth = outMetrics.widthPixels / density;
        return Math.round(dpWidth / 200);
    }
}
