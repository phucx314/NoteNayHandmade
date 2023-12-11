package com.phxc.notenayhandmade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phxc.notenayhandmade.Database.NotesDB;
import com.phxc.notenayhandmade.Models.Note;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
public class CloneActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    NotesDB notesDB;

    // Chuyển biến notes lên cấp độ lớp
    private List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clone);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notes");
        notesDB = NotesDB.getInstance(this);
        // Khởi chạy phương thức để đọc dữ liệu từ Realtime Database
        readDataFromRealtimeDB();
    }


    private void readDataFromRealtimeDB() {
        // Lắng nghe sự thay đổi trên nút "notes"
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Xóa các phần tử cũ để tránh việc thêm vào danh sách cũ khi có sự thay đổi
                notes.clear();

                // Lấy dữ liệu từ DataSnapshot và đưa vào danh sách
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Note note = noteSnapshot.getValue(Note.class);
                    notesDB.notesDAO().insertNotes(note);
                }
                startActivity(new Intent(CloneActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu
                Log.e("Error", "Failed to read value.", databaseError.toException());
            }
        });
    }

}
