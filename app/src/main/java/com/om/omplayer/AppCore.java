package com.om.omplayer;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class AppCore extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/GothamRoundedMedium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static Context getContext() {
        return mContext;
    }
}
