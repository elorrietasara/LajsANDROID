package com.example.reservas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        SimpleDateFormat dt1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
        SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy");


            holder.aloj.setText(datoReserva.get(position).getNombre());
        try {
            holder.fE.setText(dt2.format(dt1.parse(datoReserva.get(position).getFe())));
            holder.fS.setText(dt2.format(dt1.parse(datoReserva.get(position).getFs())));
        } catch (ParseException e) {
            e.printStackTrace();
        }


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

            aloj=itemView.findViewById(R.id.textView28);
            fE=itemView.findViewById(R.id.textView29);
            fS=itemView.findViewById(R.id.textView30);
        }
    }
}
