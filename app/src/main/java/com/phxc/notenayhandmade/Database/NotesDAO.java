package com.phxc.notenayhandmade.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.phxc.notenayhandmade.Models.Notes;

import java.util.List;

@Dao
public interface NotesDAO {
    @Insert
    void insertNotes(Notes notes);

    @Query("SELECT * FROM notes")
    List<Notes> getListNotes();
}
