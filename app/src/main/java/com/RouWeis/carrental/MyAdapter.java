package com.RouWeis.carrental;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewholder> {
    Context context;
    List<Cars> carslist;

    public MyAdapter(List<Cars> carsList) {
        // this.context=ct;
        this.carslist = carsList;

    }

    public static void addItem(int groupId) {

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_home, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        // holder.name.setText(cars.getName());
        //holder.image.setImageResource(cars.getPhoto());

    }

    @Override
    public int getItemCount() {
        return carslist.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView name;
        ImageView image;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title_home);
            image = itemView.findViewById(R.id.img);

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
