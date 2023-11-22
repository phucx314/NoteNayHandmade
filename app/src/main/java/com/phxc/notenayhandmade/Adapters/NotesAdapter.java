package com.phxc.notenayhandmade.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phxc.notenayhandmade.Models.Notes;
import com.phxc.notenayhandmade.NotesClickListener;
import com.phxc.notenayhandmade.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Notes> notesList;
    NotesClickListener listener;

    public NotesAdapter(Context context, List<Notes> notesList, NotesClickListener listener) {
        this.context = context;
        this.notesList = notesList;
        this.listener = listener;
    }

    private void setData(List<Notes> list) {
        this.notesList = list;
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
        Notes notes = notesList.get(position);
        if(notes == null) {
            return;
        }

        holder.tvTitle.setText(notes.getTitle());

        holder.tvContent.setText(notes.getContent());

        holder.tvDate.setText(notes.getDate());

        // Lỗi ở cái đb này (để sửa sau)

//        holder.cvNoteCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onClick(notesList.get(holder.getAdapterPosition()));
//            }
//        });
//
//        holder.cvNoteCard.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                listener.onLongClick(notesList.get(holder.getAdapterPosition()), holder.cvNoteCard);
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(notesList != null) {
            return notesList.size();
        }
        return 0;
    }


}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView cvNoteCard;
    TextView tvTitle;
    TextView tvContent;
    TextView tvDate;


    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvContent = itemView.findViewById(R.id.tv_content);
        tvDate = itemView.findViewById(R.id.tv_date);
    }
}