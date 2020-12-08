package com.example.darktheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DarkThemeActivity extends AppCompatActivity {

    private RadioButton rbLightMode, rbDarkMode,rbSystemMode;
    private static String NIGHT_MODE, LIGHT_MODE, SYSTEM_UI_MODE;
    private RadioGroup rgTheme;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_theme);
        setUpApplicationTheme();
        initViews();
        setRbLastState();
        setListener();
    }

    private void setUpApplicationTheme() {
        if (MyApplication.getInstance().getActiveMode()==AppCompatDelegate.MODE_NIGHT_YES){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if(MyApplication.getInstance().getActiveMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initViews() {
        rbLightMode = findViewById(R.id.rb_light_mode);
        rbDarkMode = findViewById(R.id.rb_dark_mode);
        rbSystemMode = findViewById(R.id.rb_system_mode);
        rgTheme = findViewById(R.id.rg_dark_mode);
    }

    private void setRbLastState() {
        rbLightMode.setChecked(PreferenceUtils.getPreferenceBoolValueWithDefaultValue(this, LIGHT_MODE, false));
        rbDarkMode.setChecked(PreferenceUtils.getPreferenceBoolValueWithDefaultValue(this, NIGHT_MODE,false));
        rbSystemMode.setChecked(PreferenceUtils.getPreferenceBoolValueWithDefaultValue(this, SYSTEM_UI_MODE,true));

    }

    private void setListener() {
//        rbDarkMode.setOnClickListener(this);
//        rbLightMode.setOnClickListener(this);
//        rbSystemMode.setOnClickListener(this);
        rgTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(rbLightMode.getId() == checkedId){
                    setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_NO);
                    setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
                }else if(rbDarkMode.getId() == checkedId){
                    setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
                    setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
                    setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
            }
        });
    }

//    private void setApplicationTheme(){
//        if(rbLightMode.isChecked()){
//            setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_NO);
//            setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
//        }else if(rbDarkMode.isChecked()){
//            setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
//            setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_YES);
//        }else{
//            setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
//            setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//        }
//    }

    private void setDarkModeBasedOnUserPreference(int themeMode){
        AppCompatDelegate.setDefaultNightMode(themeMode);
        MyApplication.getInstance().setActiveMode(themeMode);
    }

    private void setRadioButtonState(boolean isLightMode, boolean isNightMode, boolean isSystemMode){
        PreferenceUtils.writePreferenceValue(this,LIGHT_MODE, isLightMode);
        PreferenceUtils.writePreferenceValue(this,NIGHT_MODE, isNightMode);
        PreferenceUtils.writePreferenceValue(this,SYSTEM_UI_MODE, isSystemMode);
    }


}