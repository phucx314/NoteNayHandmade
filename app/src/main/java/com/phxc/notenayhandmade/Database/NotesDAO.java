package com.phxc.notenayhandmade.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.phxc.notenayhandmade.Models.Note;

import java.util.List;

@Dao
public interface NotesDAO {
    @Insert (onConflict = REPLACE)
    void insertNotes(Note note);

    @Query("SELECT * FROM Notes ORDER BY pinned DESC, date DESC")
    List<Note> getListNotes();

    @Query("UPDATE notes SET title = :title, content = :content, date = :date, pattern = :pattern WHERE ID = :ID")
    void update(long ID, String title, String content, String date, String pattern);

    @Delete
    void delete(Note note);

    @Query("UPDATE notes SET pinned = :pin WHERE ID = :ID")
    void pin(long ID, boolean pin);
}
