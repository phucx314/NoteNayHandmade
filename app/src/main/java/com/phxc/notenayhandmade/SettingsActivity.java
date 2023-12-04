package com.phxc.notenayhandmade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class SettingsActivity extends AppCompatActivity {

    CardView theme;
    CardView trash;
    CardView login;


    // đổi màu status bar trên android (đen)
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    // ham anh xa id
    void anhXaID() {
        theme = findViewById(R.id.opt_theme);
//        trash = findViewById(R.id.opt_trash);
        login = findViewById(R.id.opt_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changeStatusbarColor_black();

        anhXaID();

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            }
        });
    }
}