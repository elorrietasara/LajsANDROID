package com.example.reservas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterReservas extends RecyclerView.Adapter<MyAdapterReservas.ViewHolder> {

    private List<Reserva> datoReserva=new ArrayList<Reserva>();

    // Pass in the contact array into the constructor
    public MyAdapterReservas(List<Reserva> datos) {
        datoReserva = datos;
    }


    @NonNull
    @Override
    public MyAdapterReservas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.reservasusuario, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterReservas.ViewHolder holder, int position) {



            holder.aloj.setText(holder.aloj.getText()+"\n"+datoReserva.get(position).getNombre());
            holder.fE.setText(holder.fE.getText()+"\n"+datoReserva.get(position).getFe().substring(0,10));
            holder.fS.setText(holder.fS.getText()+"\n"+datoReserva.get(position).getFs().substring(0,10));

    }

    @Override
    public int getItemCount() {
        return datoReserva.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aloj;
        TextView fE;
        TextView fS;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            aloj=itemView.findViewById(R.id.textView24);
            fE=itemView.findViewById(R.id.textView25);
            fS=itemView.findViewById(R.id.textView26);
        }
    }
}
