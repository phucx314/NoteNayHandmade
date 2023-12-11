package com.phxc.notenayhandmade.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.phxc.notenayhandmade.Models.Note;

@Database(entities = {Note.class}, version = 2, exportSchema = false) // update sau
public abstract class NotesDB extends RoomDatabase {
    private static final String DATABASE_NAME = "database_auroranotes.db";
    private static NotesDB notesDatabase;

    public static synchronized NotesDB getInstance(Context context) {
        if(notesDatabase == null) {
            notesDatabase = Room.databaseBuilder(context.getApplicationContext(), NotesDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return notesDatabase;
    }
    public abstract NotesDAO notesDAO();
}
