package com.example.darktheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DarkThemeActivity extends AppCompatActivity {

    private RadioButton rbLightMode, rbDarkMode,rbSystemMode;
    private static final String DARK_MODE = "dark_mode", LIGHT_MODE="light_mode", SYSTEM_UI_MODE="system_ui_mode";
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

    //Load previously selected theme from Preference Utils
    private void setUpApplicationTheme() {
        if (MyApplication.getInstance().getActiveMode()==AppCompatDelegate.MODE_NIGHT_YES){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if(MyApplication.getInstance().getActiveMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    //initialize view
    private void initViews() {
        rbLightMode  = findViewById(R.id.rb_light_mode);
        rbDarkMode   = findViewById(R.id.rb_dark_mode);
        rbSystemMode = findViewById(R.id.rb_system_mode);
        rgTheme      = findViewById(R.id.rg_dark_mode);
    }

    //fetch last selected mode from PreferenceUtils
    private void setRbLastState() {
        rbLightMode.setChecked(PreferenceUtils.getPreferenceBoolValueWithDefaultValue(this,  LIGHT_MODE, false));
        rbDarkMode.setChecked(PreferenceUtils.getPreferenceBoolValueWithDefaultValue(this,   DARK_MODE,false));
        rbSystemMode.setChecked(PreferenceUtils.getPreferenceBoolValueWithDefaultValue(this, SYSTEM_UI_MODE,true));
    }

    private void setListener() {
        rgTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(rbLightMode.getId() == checkedId){
                    setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_NO);
                    setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
                }else if(rbDarkMode.getId() == checkedId){
                    setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_YES);
                    setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
                }else{
                    setDarkModeBasedOnUserPreference(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    setRadioButtonState(rbLightMode.isChecked(), rbDarkMode.isChecked(),rbSystemMode.isChecked());
                }
            }
        });
    }


//Save currently active mode
    private void setDarkModeBasedOnUserPreference(int themeMode){
        AppCompatDelegate.setDefaultNightMode(themeMode);
        MyApplication.getInstance().setActiveMode(themeMode);
    }

    //Save selected mode in PreferenceUtils, in order to restore radio button state once the app is killed and launched
    private void setRadioButtonState(boolean isLightMode, boolean isNightMode, boolean isSystemMode){
        PreferenceUtils.writePreferenceValue(this, LIGHT_MODE, isLightMode);
        PreferenceUtils.writePreferenceValue(this, DARK_MODE, isNightMode);
        PreferenceUtils.writePreferenceValue(this, SYSTEM_UI_MODE, isSystemMode);
    }

}