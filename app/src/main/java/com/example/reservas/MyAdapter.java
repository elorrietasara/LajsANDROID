package com.example.reservas;


import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] userModelList;

    public MyAdapter(String[] userModelList) {
        this.userModelList = userModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapteraloj, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = userModelList[position].toString();
        ImageView image;
        holder.name.setText(name);

    }

    @Override
    public int getItemCount() {
        return userModelList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView telefono;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.textView8);
            telefono=(TextView) v.findViewById(R.id.textView9);
            image = (ImageView) v.findViewById(R.id.imageView2);
        }
    }

}
