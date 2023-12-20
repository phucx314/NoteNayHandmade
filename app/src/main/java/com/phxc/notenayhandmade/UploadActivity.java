package com.phxc.notenayhandmade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phxc.notenayhandmade.Database.NotesDB;
import com.phxc.notenayhandmade.Models.Note;
import java.util.List;
public class UploadActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notes");

        // Khởi chạy AsyncTask để lấy dữ liệu từ Room Database và đưa vào một mảng
        new LoadNotesTask().execute();

        finish();
        startActivity(new Intent(UploadActivity.this, HomePage.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    //Thêm tất cả các Note từ danh sách vào Realtime Database
    private void possDataToRealtimeDB(List<Note> notes) {
        for (Note note : notes) {
            myRef.push().setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d("Debug", "Upload data successful for Note" + note.getTitle());
                        Toast.makeText(getApplicationContext(), "Upload data successful for Note", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Error", "Upload data unsuccessful for Note" + note.getTitle(), task.getException());
                    }
                }
            });
        }
    }

    private class LoadNotesTask extends AsyncTask<Void, Void, List<Note>> {
        @Override
        protected List<Note> doInBackground(Void... voids) {
            // Lấy instance của NotesDB
            NotesDB notesDB = NotesDB.getInstance(UploadActivity.this);

            // Gọi phương thức truy vấn từ DAO để lấy danh sách các Note
            return notesDB.notesDAO().getListNotes();
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            // Ở đây, bạn có thể làm gì đó với mảng 'notes', chẳng hạn hiển thị nó lên RecyclerView hoặc ListView
            possDataToRealtimeDB(notes);
        }
    }
}