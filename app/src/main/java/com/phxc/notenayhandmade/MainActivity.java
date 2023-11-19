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

    Button btn_addnote;
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

//        et_search = findViewById(R.id.et_search);
//        private void filterStr(String newText) {
//            List<Notes> filterList = new ArrayList<>();
//            for (Notes singleNote : notes) {
//                if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
//                        || singleNote.getContent().toLowerCase().contains(newText.toLowerCase())) {
//                    filterList.add(singleNote);
//                }
//            }
//            NotesAdapter.filteredList(filterList);
//        }
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterStr(newText);
//                return false;
//            }
//        });


        recyclerView = findViewById(R.id.recyclerView);

        NotesAdapter noteAdapter = new NotesAdapter();
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        changeStatusbarColor_black();

        btn_addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

//        recyclerViewNotes.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                notesAdapter.setScrollUp(dy > 10);
//                if (dy != 0) {
//                    searchView.clearFocus();
//                }
//            }
//
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (layoutManager != null) {
//                    int[] arr = layoutManager.findFirstVisibleItemPositions(null);
//                    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
//                    if (list.contains(0) || notes.isEmpty()) {
//                        extendedFloatingActionButton.extend();
//                    } else {
//                        extendedFloatingActionButton.shrink();
//                    }
//                }
//            }
//        });
    }
}