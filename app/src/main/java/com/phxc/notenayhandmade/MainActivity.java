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

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.phxc.notenayhandmade.Adapters.Test1Adapter;
import com.phxc.notenayhandmade.Models.Notes;
import com.phxc.notenayhandmade.Models.Test1;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_addnote;
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

        btn_addnote = findViewById(R.id.btn_savenote);
        List<Test1> testList = new ArrayList<Test1>();
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));
        testList.add(new Test1("Title 1", R.drawable.img));

        recyclerView = findViewById(R.id.recyclerView);

        Test1Adapter test1Adapter = new Test1Adapter(testList);
        recyclerView.setAdapter(test1Adapter);
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