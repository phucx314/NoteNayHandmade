package com.phxc.notenayhandmade.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phxc.notenayhandmade.Models.Note;
import com.phxc.notenayhandmade.NoteClickListener;
import com.phxc.notenayhandmade.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Note> noteList;
    NoteClickListener listener;

    public NotesAdapter(Context context, List<Note> noteList, NoteClickListener listener) {
        this.context = context;
        this.noteList = noteList;
        this.listener = listener;
    }

    private void setData(List<Note> list) {
        this.noteList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notesview, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = noteList.get(position);
        if(note == null) {
            return;
        }

        holder.tvTitle.setText(note.getTitle());

        holder.tvContent.setText(note.getContent());

        holder.tvDate.setText(note.getDate());

        if(noteList.get(position).isPinned()) {
            holder.ic_pin.setImageResource(R.drawable.ic_pinned);
        }
        else {
            holder.ic_pin.setImageResource(0);
        }


        holder.cvNoteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(noteList.get(holder.getAdapterPosition()));
            }
        });

        holder.cvNoteCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(noteList.get(holder.getAdapterPosition()), holder.cvNoteCard);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(noteList != null) {
            return noteList.size();
        }
        return 0;
    }

    public void filteredList(List<Note> filteredList) {
        noteList = filteredList;
        notifyDataSetChanged();
    }

}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView cvNoteCard;
    TextView tvTitle;
    TextView tvContent;
    TextView tvDate;
    ImageView ic_pin;


    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        cvNoteCard = itemView.findViewById(R.id.card_view);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvContent = itemView.findViewById(R.id.tv_content);
        tvDate = itemView.findViewById(R.id.tv_date);
        ic_pin = itemView.findViewById(R.id.ic_pin);
    }
}