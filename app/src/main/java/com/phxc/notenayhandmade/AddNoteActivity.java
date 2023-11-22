package com.phxc.notenayhandmade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phxc.notenayhandmade.Adapters.NotesAdapter;
import com.phxc.notenayhandmade.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    EditText edittxt_title, edittxt_contents;
    TextView txt_date;
    Button btn_savenote;
    Notes notes;

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

                notes = new Notes();

                notes.setTitle(title);
                notes.setContent(content);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    // hàm này ánh xạ ID
    void anhXaID() {
        edittxt_title = findViewById(R.id.edittxt_title);
        edittxt_contents = findViewById(R.id.edittxt_contents);
        txt_date = findViewById(R.id.txt_date);
        btn_savenote = findViewById(R.id.btn_savenote);
    }


}