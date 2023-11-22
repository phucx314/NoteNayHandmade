package com.phxc.notenayhandmade.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.phxc.notenayhandmade.Models.Note;

import java.util.List;

@Dao
public interface NotesDAO {
    @Insert (onConflict = REPLACE)
    void insertNotes(Note note);

    @Query("SELECT * FROM Notes ORDER BY ID DESC")
    List<Note> getListNotes();

    @Query("UPDATE notes SET title = :title, content = :content, date = :date WHERE ID = :ID")
    void update(long ID, String title, String content, String date);

    @Delete
    void delete(Note note);
}
