package com.phxc.notenayhandmade.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phxc.notenayhandmade.Models.Test1;
import com.phxc.notenayhandmade.R;

import java.util.List;

public class Test1Adapter extends RecyclerView.Adapter<Test1Adapter.MyViewHolder> {

    private List<Test1> listTest1;

    public Test1Adapter(List<Test1> listTest1) {
        this.listTest1 = listTest1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_testview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Test1 test1 = listTest1.get(position);
        holder.imgView.setImageResource(test1.getImgID());
        holder.txtView.setText(test1.getTitle());
    }

    @Override
    public int getItemCount() {
        return listTest1.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView crdView;
        private ImageView imgView;
        private TextView txtView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            crdView = itemView.findViewById(R.id.card_view);
            imgView = itemView.findViewById(R.id.item_image_view);
            txtView = itemView.findViewById(R.id.content);
        }


    }
}
