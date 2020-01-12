package com.example.reservas;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import android.graphics.Typeface;
import android.location.Location;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.savvi.rangedatepicker.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {

    private List<Alojamieno> userModelList;


    GoogleMap map;
    Double lat ;
    Double lon;

    public MyAdapter(List<Alojamieno> userModelList) {
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

        String name = userModelList.get(position).getNombre();
        String tel = userModelList.get(position).getTelefono();
        String webb = userModelList.get(position).getLocalidad();
        final String descripcion = userModelList.get(position).getDescripcion();
         lat = userModelList.get(position).getLat();
         lon = userModelList.get(position).getLon();


        holder.name.setText(name);
        holder.telefono.setText(tel);
        holder.web.setText(webb);
        holder.reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                LayoutInflater layoutinflater = LayoutInflater.from(view.getContext());
                View promptUserView = layoutinflater.inflate(R.layout.reservar, null);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());

                alertDialogBuilder.setView(promptUserView);

                Calendar pastYear = Calendar.getInstance();
                pastYear.add(Calendar.YEAR, 0);
                Calendar nextYear = Calendar.getInstance();
                nextYear.add(Calendar.DATE,265);



                final CalendarPickerView calendar = (CalendarPickerView) promptUserView.findViewById(R.id.calendar_view);

                calendar.init(pastYear.getTime(), nextYear.getTime()) //
                        .inMode(CalendarPickerView.SelectionMode.RANGE)
                        .withSelectedDate(new Date());
                calendar.setTypeface(Typeface.SANS_SERIF);

                alertDialogBuilder.setTitle("Seleccione fechas");

                alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(view.getContext(),""+calendar.getSelectedDates(),Toast.LENGTH_LONG).show();
                        // and display the username on main activity layout

                        new SweetAlertDialog(view.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Reservado!")
                                
                                .show();
                       dialog.dismiss();
                    }
                });

                // all set and time to build and show up!
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

     }

    @Override
    public int getItemCount() {

        return userModelList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView telefono;
        private TextView web;
        private Button reservar;

        public ViewHolder(View v) {
            super(v);

            name = (TextView) v.findViewById(R.id.textView8);
            telefono = (TextView) v.findViewById(R.id.textView9);
            image = (ImageView) v.findViewById(R.id.imageView2);
            web = (TextView) v.findViewById(R.id.textView);
            reservar=(Button)v.findViewById(R.id.button2);




        }
    }
}
