package com.example.darktheme;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {

    private static Context context;
    private static Application application;
    private static final String ACTIVE_MODE = "active_mode";
    private int currentlyActiveMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    private static MyApplication singleton = null;



    public static Context getContext() {
        return MyApplication.context;
    }

    public static MyApplication getInstance() {
        if(singleton == null) {
            singleton = new MyApplication();
        }
        return singleton;
    }

    public static Application getApplicaion() {
        return MyApplication.application;
    }

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        MyApplication.application = getApplicaion();
        singleton = this;
        this.currentlyActiveMode = PreferenceUtils.getPreferenceIntValueForTheme(this, ACTIVE_MODE);
    }

    public int getActiveMode() {
        return currentlyActiveMode;
    }

    public void setActiveMode(int mode) {
        this.currentlyActiveMode = mode;
        PreferenceUtils.writePreferenceValue(this, ACTIVE_MODE, currentlyActiveMode);
    }
}
