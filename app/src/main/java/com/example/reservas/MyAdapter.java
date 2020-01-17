package com.example.reservas;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import android.graphics.Typeface;
import android.location.Location;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
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

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Alojamieno> userModelList;


    GoogleMap map;
    Double lat;
    Double lon;
    ConexionBBDD con = new ConexionBBDD();

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String name = userModelList.get(position).getNombre();
        String tel = userModelList.get(position).getTelefono();
        String webb = userModelList.get(position).getLocalidad();
        final String descripcion = userModelList.get(position).getDescripcion();
        lat = userModelList.get(position).getLat();
        lon = userModelList.get(position).getLon();
        Alojamieno aloj= new Alojamieno();
        aloj.setNombre(name);
        aloj.setLat(lat);
        aloj.setLon(lon);

        userModelList.add(aloj);


        holder.name.setText(name);
        holder.telefono.setText(tel);
        holder.web.setText(lat+" "+lon);
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
                nextYear.add(Calendar.DATE, 265);


                final CalendarPickerView calendar = (CalendarPickerView) promptUserView.findViewById(R.id.calendar_view);

                calendar.init(pastYear.getTime(), nextYear.getTime()) //
                        .inMode(CalendarPickerView.SelectionMode.RANGE)
                        .withSelectedDate(new Date());
                calendar.setTypeface(Typeface.SANS_SERIF);

                alertDialogBuilder.setTitle("Seleccione fechas");

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(view.getContext(), "" + calendar.getSelectedDates(), Toast.LENGTH_LONG).show();
                        // and display the username on main activity layout

                        new SweetAlertDialog(view.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Reservado!").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent intent = new Intent(view.getContext(), ReservaActivity.class);
                                intent.putExtra("Nombre", userModelList.get(position).getNombre());
                                intent.putExtra("FechaE",calendar.getSelectedDates().get(0).toString());
                                intent.putExtra("FechaS",calendar.getSelectedDates().get(calendar.getSelectedDates().size()-1).toString() );

                                view.getContext().startActivity(intent);
                                ConexionBBDD connectMySql = new ConexionBBDD();

                                connectMySql.execute(calendar.getSelectedDates().get(0).toString(),calendar.getSelectedDates().get(calendar.getSelectedDates().size()-1).toString(),"1","1");
                                //connectMySql.insertar(calendar.getSelectedDates().get(0),calendar.getSelectedDates().get(calendar.getSelectedDates().size()-1),1,1);

                                sweetAlertDialog.dismiss();

                            }
                        })

                                .show();
                        dialog.dismiss();

                    }
                });

                // all set and time to build and show up!
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        holder.verMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"latlon"+userModelList.get(position).getLat()+"  "+userModelList.get(position).getLon()+"  "+  holder.getLayoutPosition(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MapsActivity2.class);
                intent.putExtra("LATITUDE", userModelList.get(position).getLat());
                intent.putExtra("LONGITUDE", userModelList.get(position).getLon());
                intent.putExtra("nombre",userModelList.get(position).getNombre());
                view.getContext().startActivity(intent);

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
        private Button verMapa;



        public ViewHolder(View v) {
            super(v);

            name = (TextView) v.findViewById(R.id.textView8);
            telefono = (TextView) v.findViewById(R.id.textView9);
            image = (ImageView) v.findViewById(R.id.imageView2);
            web = (TextView) v.findViewById(R.id.textView);
            reservar = (Button) v.findViewById(R.id.button2);
            verMapa=v.findViewById(R.id.button4);





        }


    }
}
