package com.phxc.notenayhandmade.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.phxc.notenayhandmade.Models.Notes;

@Database(entities = {Notes.class}, version = 1) // update sau
public abstract class NotesDB extends RoomDatabase {
    private static final String DATABASE_NAME = "database_notes.db";
    private static NotesDB instance;

    public static synchronized NotesDB getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NotesDB.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract NotesDAO notesDAO();
}
