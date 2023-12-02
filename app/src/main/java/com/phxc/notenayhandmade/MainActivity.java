package com.phxc.notenayhandmade;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    MenuItem settings, select, login, trash, pin, unpin, delete;
    SearchView et_search;
    Note selectedNotes;
    ImageView ic_pin;
    SwipeRefreshLayout swipeRefreshLayout;

    StaggeredGridLayoutManager layoutManager;
    ExtendedFloatingActionButton extendedFloatingActionButton;
    ImageView btnProfile;

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
        settings = findViewById(R.id.settings);
        select = findViewById(R.id.select);
        login = findViewById(R.id.login);
        trash = findViewById(R.id.trash);
        et_search = findViewById(R.id.et_search);
        pin = findViewById(R.id.pin);
        unpin = findViewById(R.id.unpin);
        delete = findViewById(R.id.delete);
        ic_pin = findViewById(R.id.ic_pin);
        swipeRefreshLayout = findViewById(R.id.swiperLayout);
        btnProfile = findViewById(R.id.btn_profile);
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

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    notes = notesDB.notesDAO().getListNotes();
                    updateRecycler(notes);
                    swipeRefreshLayout.setRefreshing(false);
                }, 1000);
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
                notesDB.notesDAO().update((int) newNote.getID(), newNote.getTitle(), newNote.getContent(), newNote.getDate(), newNote.getPattern());
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
            selectedNotes = new Note();
            selectedNotes = note;
            showOptionPopup(cvNoteCard);
        }
    };

    private void showOptionPopup(CardView cvNoteCard) {
        PopupMenu popup = new PopupMenu(this, cvNoteCard);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.longclicknote_menu);
        popup.show();
    }

    public void showMenuPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.item_menu);
        popup.show();
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            Toast.makeText(this, "Fn Underdevelopment", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.select) {
            Toast.makeText(this, "Fn Underdevelopment", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.login) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            Toast.makeText(this, "Fn Underdevelopment", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.trash) {
            Toast.makeText(this, "Fn Underdevelopment", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (itemId == R.id.pin) {
            if(!selectedNotes.isPinned()) {
                notesDB.notesDAO().pin(selectedNotes.getID(), true);
                Toast.makeText(this, "Pinned", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Note already pinned", Toast.LENGTH_SHORT).show();
            }
            notes.clear();
            notes.addAll(notesDB.notesDAO().getListNotes());
            notesAdapter.notifyDataSetChanged();
            return true;
        } // cần phát triển thêm (List Pinned riêng)
        else if (itemId == R.id.unpin) {
            if(selectedNotes.isPinned()) {
                notesDB.notesDAO().pin(selectedNotes.getID(), false);
                Toast.makeText(this, "Unpinned", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Note is not pinned", Toast.LENGTH_SHORT).show();
            }
            notes.clear();
            notes.addAll(notesDB.notesDAO().getListNotes());
            notesAdapter.notifyDataSetChanged();
            return true;
        }
        else if (itemId == R.id.delete) {
            
            notesDB.notesDAO().delete(selectedNotes);
            notes.remove(selectedNotes);
            notesAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
            return true;
        } // cần phát triển thêm (Trash)

        else {
            return false;
        }
    }



}