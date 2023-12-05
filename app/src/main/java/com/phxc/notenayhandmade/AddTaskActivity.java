package com.phxc.notenayhandmade;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.phxc.notenayhandmade.PermissionUtils.verifyStoragePermissions;

import com.phxc.notenayhandmade.Models.Note;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public class AddTaskActivity extends AppCompatActivity {
    EditText edittask_title;
    Button btn_savetasklist;
    Button btn_add_new_task;
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todolist);
        changeStatusbarColor_black();
        anhXaID();
        verifyStoragePermissions(this);
        btn_add_new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void anhXaID() {
        edittask_title = findViewById(R.id.edittask_title);
        btn_savetasklist = findViewById(R.id.btn_savetasklist);
        btn_add_new_task = findViewById(R.id.btn_add_new_task);
    }

}
