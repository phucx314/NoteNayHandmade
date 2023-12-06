package com.phxc.notenayhandmade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phxc.notenayhandmade.Models.Note;

public class UploadActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        mAuth = FirebaseAuth.getInstance();

        addNote();
        finish();
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    public void addNote() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notes");
        //String id = myRef.push().getKey();
        String title = "Test title";
        String content = "Test content";
        String date = "2023 12 04 17 57 38 70";
        Boolean pinned = true;
        String pattern = "Dec 04, 2023 - 17:57";
        myRef.push().setValue(new Note(title,content,date,pinned,pattern)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d("DEBUG","poss data successful");
                }
                else {
                    Log.d("DEBUG","poss data fail");
                }
            }
        });
    }
    //Da'y du lieu len database
    private void possDataToRealtimeDB(String data) {
        // Write a message to the database
        myRef.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("Debug","poss data "+data+" successful");
                }else {
                    Log.d("Debug","poss data fail");
                }
            }
        });
    }
}