package com.phxc.notenayhandmade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class SettingsActivity extends AppCompatActivity {
    private SwitchCompat modeSwitch;
    private boolean nightMode = false;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private ThemeChangeListener themeChangeListener;
    CardView theme;
    CardView trash;
    CardView logout;

    CardView clone;
    CardView upload;
    FirebaseAuth mAuth;
    TextView tv_theme,tv_logout,tv_trash,tv_clone,tv_upload;
    // đổi màu status bar trên android (đen)
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    // ham anh xa id
    void anhXaID() {
        theme = findViewById(R.id.opt_theme);
        trash = findViewById(R.id.opt_trash);
        logout = findViewById(R.id.opt_logout);
        clone = findViewById(R.id.cv_clonedata);
        upload = findViewById(R.id.cv_uploaddata);
        modeSwitch =findViewById(R.id.mode_switch);
        tv_theme = findViewById(R.id.tv_theme);
        tv_clone = findViewById(R.id.tv_clonedata);
        tv_logout = findViewById(R.id.tv_logoutaccount);
        tv_trash = findViewById(R.id.tv_trash);
        tv_upload = findViewById(R.id.tv_uploaddata);
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changeStatusbarColor_black();

        anhXaID();
        mAuth = FirebaseAuth.getInstance();
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, UploadActivity.class));
                Toast.makeText(getApplicationContext(), "Upload.....", Toast.LENGTH_SHORT).show();
            }
        });
        clone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, CloneActivity.class));
                Toast.makeText(getApplicationContext(), "Clone.....", Toast.LENGTH_SHORT).show();
            }
        });


        modeSwitch = findViewById(R.id.mode_switch);
        sharedPreferences = getSharedPreferences("MODE",Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", true);

        if (nightMode) {
            modeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            updateColors(nightMode);
        }

        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);// truoc de la true
                }

                editor = sharedPreferences.edit();
                editor.putBoolean("night", state);
//                notifyThemeChange(state);
                editor.apply();

            }
        });
        updateColors(nightMode);

    }
    private void signOut(){
        mAuth.signOut();
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
    }

    private void notifyThemeChange(boolean isDarkThemeEnabled) {
        ThemeHelper.setDarkThemeEnabled(isDarkThemeEnabled);

        // Notify the listener (MainActivity) about the theme change
        if (themeChangeListener != null) {
            themeChangeListener.onThemeChanged(isDarkThemeEnabled);
        }
    }

    public void setThemeChangeListener(ThemeChangeListener listener) {
        this.themeChangeListener = listener;
    }

    private void updateColors(boolean isNightModeEnabled){
        int textColor;
        int cardColor;
        if (isNightModeEnabled){
            textColor = getResources().getColor(R.color.white);
            cardColor = getResources().getColor(R.color.grey);
        }else {
            textColor = getResources().getColor(R.color.black);
            cardColor = getResources().getColor(R.color.white);
        }

        tv_theme.setTextColor(textColor);
        theme.setCardBackgroundColor(cardColor);
        tv_clone.setTextColor(textColor);
        clone.setCardBackgroundColor(cardColor);
        tv_trash.setTextColor(textColor);
        trash.setCardBackgroundColor(cardColor);
        tv_logout.setTextColor(textColor);
        logout.setCardBackgroundColor(cardColor);
        tv_upload.setTextColor(textColor);
        upload.setCardBackgroundColor(cardColor);
    }




}