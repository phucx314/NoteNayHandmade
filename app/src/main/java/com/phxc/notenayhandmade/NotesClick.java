package com.phxc.notenayhandmade;

import androidx.cardview.widget.CardView;

import com.phxc.notenayhandmade.Models.Note;

public interface NotesClick {
    void onClick(Note note, int position);

    void onLongClick(Note note, CardView cardView, int position);
}
