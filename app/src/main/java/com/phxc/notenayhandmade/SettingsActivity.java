package com.phxc.notenayhandmade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class SettingsActivity extends AppCompatActivity {

    CardView theme;
    CardView trash;
    CardView logout;

    CardView clone;
    CardView upload;
    FirebaseAuth mAuth;
    // đổi màu status bar trên android (đen)
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    // ham anh xa id
    void anhXaID() {
        theme = findViewById(R.id.opt_theme);
//        trash = findViewById(R.id.opt_trash);
        logout = findViewById(R.id.opt_logout);
        clone = findViewById(R.id.cv_clonedata);
        upload = findViewById(R.id.cv_uploaddata);

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
            }
        });
    }
    private void signOut(){
        mAuth.signOut();
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
    }
}