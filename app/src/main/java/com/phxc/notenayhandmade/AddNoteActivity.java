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

import java.util.ArrayList;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private EditText edittxt_title;
    private EditText edittxt_contents;
    private TextView txt_date;
    private Button btn_savenote;
    private Notes notes;
    private List<String> paths = new ArrayList<>();

    public EditText getEdittxt_title() {
        return edittxt_title;
    }

    public void setEdittxt_title(EditText edittxt_title) {
        this.edittxt_title = edittxt_title;
    }

    public EditText getEdittxt_contents() {
        return edittxt_contents;
    }

    public void setEdittxt_contents(EditText edittxt_contents) {
        this.edittxt_contents = edittxt_contents;
    }

    public TextView getTxt_date() {
        return txt_date;
    }

    public void setTxt_date(TextView txt_date) {
        this.txt_date = txt_date;
    }

    public Button getBtn_savenote() {
        return btn_savenote;
    }

    public void setBtn_savenote(Button btn_savenote) {
        this.btn_savenote = btn_savenote;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

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

        initUI();

        btn_savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edittxt_title.getText().toString();
                String contents = edittxt_contents.getText().toString();

            }
        });
    }

    // hàm này ánh xạ ID
    void initUI() {
        edittxt_title = findViewById(R.id.edittxt_title);
        edittxt_contents = findViewById(R.id.edittxt_contents);
        txt_date = findViewById(R.id.txt_date);
        btn_savenote = findViewById(R.id.btn_savenote);
//        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
    }


}