package com.phxc.notenayhandmade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.phxc.notenayhandmade.Adapters.NotesAdapter;
import com.phxc.notenayhandmade.Models.Notes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_newnote;
    EditText et_search;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    SearchView searchView;
    StaggeredGridLayoutManager layoutManager;
    private List<Notes> notes = new ArrayList<>();
    ExtendedFloatingActionButton extendedFloatingActionButton;

    // đổi màu status bar trên android (đen)
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        NotesAdapter noteAdapter = new NotesAdapter();
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        changeStatusbarColor_black();

        btn_newnote = findViewById(R.id.btn_newnote);
        btn_newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
    }
}