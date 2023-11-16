package com.phxc.notenayhandmade;

import androidx.cardview.widget.CardView;

import com.phxc.notenayhandmade.Models.Notes;

public interface NotesClick {
    void onClick(Notes notes, int position);

    void onLongClick(Notes notes, CardView cardView, int position);
}
