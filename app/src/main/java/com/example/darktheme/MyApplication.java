package com.example.darktheme;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {

    private static Context context;
    private static Application applicaion;
    private static String NIGHT_MODE;
    private int systemUIMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    private static MyApplication singleton = null;



    public static Context getContext() {
        return MyApplication.context;
    }

    public static MyApplication getInstance() {

        if(singleton == null)
        {
            singleton = new MyApplication();
        }
        return singleton;
    }

    public static Application getApplicaion() {
        return MyApplication.applicaion;
    }

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        MyApplication.applicaion = getApplicaion();
        singleton = this;
        this.systemUIMode = PreferenceUtils.getPreferenceIntValueForTheme(this,NIGHT_MODE);
    }

    public int getActiveMode() {
        return systemUIMode;
    }

    public void setActiveMode(int mode) {
        this.systemUIMode = mode;
        PreferenceUtils.writePreferenceValue(this, NIGHT_MODE, systemUIMode);
    }
}
