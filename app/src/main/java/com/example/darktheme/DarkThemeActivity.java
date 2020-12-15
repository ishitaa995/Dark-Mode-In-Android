package com.example.darktheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DarkThemeActivity extends AppCompatActivity {

    private RadioButton rbLightMode, rbDarkMode,rbSystemMode;
    private TextView tvSubTitle;
    private RadioGroup rgTheme;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_theme);
        initViews();
        setUpApplicationTheme();
        setListener();
    }

    //Load previously selected theme from Preference Utils
    private void setUpApplicationTheme() {
        if (MyApplication.getInstance().getActiveMode()==AppCompatDelegate.MODE_NIGHT_YES){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            rbLightMode.setChecked(false);
            rbDarkMode.setChecked(true);
            rbSystemMode.setChecked(false);

        }else if(MyApplication.getInstance().getActiveMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            rbLightMode.setChecked(false);
            rbDarkMode.setChecked(false);
            rbSystemMode.setChecked(true);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            rbLightMode.setChecked(true);
            rbDarkMode.setChecked(false);
            rbSystemMode.setChecked(false);
        }
    }

    //initialize view
    private void initViews() {
        rbLightMode  = findViewById(R.id.rb_light_mode);
        rbDarkMode   = findViewById(R.id.rb_dark_mode);
        rbSystemMode = findViewById(R.id.rb_system_mode);
        rgTheme      = findViewById(R.id.rg_dark_mode);
        //Visible only in Dark mode cause textcolor is white in Light Mode
        tvSubTitle       = findViewById(R.id.textView);
    }



    // set mode for application on selection of radio button
    private void setListener() {
        rgTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                int themeMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                if(rbLightMode.getId() == checkedId){
                    themeMode = AppCompatDelegate.MODE_NIGHT_NO;

                }else if(rbDarkMode.getId() == checkedId){
                    themeMode = AppCompatDelegate.MODE_NIGHT_YES;
                }
                AppCompatDelegate.setDefaultNightMode(themeMode);
                MyApplication.getInstance().setActiveMode(themeMode);
            }
        });
    }






}