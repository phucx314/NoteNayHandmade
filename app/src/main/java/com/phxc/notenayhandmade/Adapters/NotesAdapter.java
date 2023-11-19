package com.phxc.notenayhandmade.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phxc.notenayhandmade.Models.Notes;
import com.phxc.notenayhandmade.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Notes> notesList;

    private void setData(List<Notes> list) {
        this.notesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notesview, parent, false);
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
    }

    @Override
    public int getItemCount() {
        if(notesList != null) {
            return notesList.size();
        }
        return 0;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvDate;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
