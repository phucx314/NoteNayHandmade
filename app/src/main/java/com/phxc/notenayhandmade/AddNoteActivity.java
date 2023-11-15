package com.phxc.notenayhandmade;

import androidx.appcompat.app.AppCompatActivity;
import com.phxc.notenayhandmade.MainActivity;

import android.os.Bundle;
import android.view.Window;

public class AddNoteActivity extends AppCompatActivity {

    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        changeStatusbarColor_black();
    }
}