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
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    EditText edittxt_title, edittxt_contents;
    TextView txt_date;
    Button btn_savenote;
    Note note;
    boolean isOldNote = false;
    Spannable spannable;
    ImageView ivAddImageFromLocal;
    ActivityResultLauncher<Intent> intentActivityResultLauncher, photoActivityResultLauncher;

    //    private RecyclerView recyclerViewNotes;
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        changeStatusbarColor_black();
        anhXaID();
        verifyStoragePermissions(this);

        note = new Note();
        try { // tránh crash
            note = (Note) getIntent().getSerializableExtra("old_note");
            edittxt_title.setText(note.getTitle());
            edittxt_contents.setText(note.getContent());
            isOldNote = true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        btn_savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edittxt_title.getText().toString();
                String content = edittxt_contents.getText().toString();

                if(content == null) {
                    Toast.makeText(AddNoteActivity.this, "Add some contents my friend.", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy - HH:mm");
                Date date = new Date();

                if(isOldNote != true) {
                    note = new Note();
                }

                note.setTitle(title);
                note.setContent(content);
                note.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", note);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });



//        Spannable spannable = new SpannableString("\n");
//        Drawable android = this.getResources().getDrawable(R.drawable.img_1);
//        android.setBounds(0,0,32,32);
//        ImageSpan imageSpan = new ImageSpan(android, ImageSpan.ALIGN_BASELINE);
//        spannable.setSpan(imageSpan, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        edittxt_contents.setText(spannable);
    }

    // hàm này ánh xạ ID
    void anhXaID() {
        edittxt_title = findViewById(R.id.edittxt_title);
        edittxt_contents = findViewById(R.id.edittxt_contents);
//        txt_date = findViewById(R.id.txt_date);
        btn_savenote = findViewById(R.id.btn_savenote);
        ivAddImageFromLocal = findViewById(R.id.iv_add_image_from_local);
    }


}