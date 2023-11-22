package com.phxc.notenayhandmade;

import androidx.cardview.widget.CardView;

import com.phxc.notenayhandmade.Models.Note;

public interface NoteClickListener {
    void onClick(Note note);
    void onLongClick(Note note, CardView cvNoteCard);
}
