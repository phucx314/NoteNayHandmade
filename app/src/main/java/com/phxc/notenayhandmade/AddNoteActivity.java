package com.phxc.notenayhandmade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.phxc.notenayhandmade.MainActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNoteActivity extends AppCompatActivity {

    private EditText edittxt_title;
    private EditText edittxt_contents;
    private TextView txt_date;
    private Button btn_addnote;
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
    }

    // hàm này ánh xạ ID
    void initUI() {
        edittxt_title = findViewById(R.id.edittxt_title);
        edittxt_contents = findViewById(R.id.edittxt_contents);
        txt_date = findViewById(R.id.txt_date);
        btn_addnote = findViewById(R.id.btn_addnote);
//        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
    }


}