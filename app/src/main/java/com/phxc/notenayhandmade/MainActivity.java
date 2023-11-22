package com.phxc.notenayhandmade;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.phxc.notenayhandmade.Adapters.NotesAdapter;
import com.phxc.notenayhandmade.Database.NotesDB;
import com.phxc.notenayhandmade.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    List<Notes> notes = new ArrayList<>();
    NotesDB notesDB;
//    FloatingActionButton fab_add;
    Button btn_newnote;
    EditText et_search;
    SearchView searchView;
    StaggeredGridLayoutManager layoutManager;
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
        btn_newnote = findViewById(R.id.btn_newnote);

        notesDB = NotesDB.getInstance(this);
        notes = notesDB.notesDAO().getListNotes();

        updateRecycler(notes);
        changeStatusbarColor_black();

        btn_newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy - HH:mm a");
//                Date date = new Date();

                startActivityForResult(new Intent(MainActivity.this, AddNoteActivity.class), 101);
            }
        });
    }

    @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101) {
            if(resultCode == Activity.RESULT_OK) {
                Notes newNotes = (Notes) data.getSerializableExtra("note");
                notesDB.notesDAO().insertNotes(newNotes);
                notes.clear();
                notes.addAll(notesDB.notesDAO().getListNotes());
                notesAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notes, notesClickListener);
        recyclerView.setAdapter(notesAdapter);
    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes notes) {

        }

        @Override
        public void onLongClick(Notes notes, CardView cvNoteCard) {

        }
    };
}