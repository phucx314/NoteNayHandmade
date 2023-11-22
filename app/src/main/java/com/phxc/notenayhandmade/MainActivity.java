package com.phxc.notenayhandmade;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.phxc.notenayhandmade.Adapters.NotesAdapter;
import com.phxc.notenayhandmade.Database.NotesDB;
import com.phxc.notenayhandmade.Models.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    List<Note> notes = new ArrayList<>();
    NotesDB notesDB;
//    FloatingActionButton fab_add;
    Button btn_newnote;
    ImageButton btn_notification;
    ImageButton btn_menu;
    MenuItem item1;
    MenuItem item2;
    MenuItem item3;
    MenuItem item4;
    SearchView et_search;

    StaggeredGridLayoutManager layoutManager;
    ExtendedFloatingActionButton extendedFloatingActionButton;

    // đổi màu status bar trên android (đen)
    void changeStatusbarColor_black() {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    // hàm ánh xạ ID
    void anhXaID() {
        recyclerView = findViewById(R.id.recyclerView);
        btn_newnote = findViewById(R.id.btn_newnote);
        btn_menu = findViewById(R.id.btn_menu);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        et_search = findViewById(R.id.et_search);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXaID();
        changeStatusbarColor_black();

        notesDB = NotesDB.getInstance(this);
        notes = notesDB.notesDAO().getListNotes();

        updateRecycler(notes);

        btn_newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy - HH:mm a");
//                Date date = new Date();

                startActivityForResult(new Intent(MainActivity.this, AddNoteActivity.class), 101);
            }
        });

        et_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String newText) {
        List<Note> filteredList = new ArrayList<>();
        for(Note singleNote : notes) {
            if(singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
            || singleNote.getContent().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        notesAdapter.filteredList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101) {
            if(resultCode == Activity.RESULT_OK) {
                Note newNote = (Note) data.getSerializableExtra("note");
                notesDB.notesDAO().insertNotes(newNote);
                notes.clear();
                notes.addAll(notesDB.notesDAO().getListNotes());
                notesAdapter.notifyDataSetChanged();
            }
        }
        else if(requestCode == 102) {
            if(resultCode == Activity.RESULT_OK) {
                Note newNote = (Note) data.getSerializableExtra("note");
                notesDB.notesDAO().update((int) newNote.getID(), newNote.getTitle(), newNote.getContent(), newNote.getDate());
                notes.clear();
                notes.addAll(notesDB.notesDAO().getListNotes());
                notesAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<Note> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notes, noteClickListener);
        recyclerView.setAdapter(notesAdapter);
    }

    private final NoteClickListener noteClickListener = new NoteClickListener() {
        @Override
        public void onClick(Note note) {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            intent.putExtra("old_note", note);
            startActivityForResult(intent, 102);

        }

        @Override
        public void onLongClick(Note note, CardView cvNoteCard) {

        }
    };

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.item_menu);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1) {
            Toast.makeText(this, "item 1 clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.item2) {
            Toast.makeText(this, "item 2 clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.item3) {
            Toast.makeText(this, "item 3 clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.item4) {
            Toast.makeText(this, "item 4 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}