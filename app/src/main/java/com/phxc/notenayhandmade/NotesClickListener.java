package com.phxc.notenayhandmade;

import androidx.cardview.widget.CardView;

import com.phxc.notenayhandmade.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cvNoteCard);
}
