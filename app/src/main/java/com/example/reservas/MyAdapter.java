package com.example.reservas;


import android.graphics.Color;

import android.location.Location;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements OnMapReadyCallback {

    private List<Alojamieno> userModelList;


    GoogleMap map;

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
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0, 0);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String name = userModelList.get(position).getNombre();
        String tel = userModelList.get(position).getTelefono();
        String webb = userModelList.get(position).getWeb();
        Double lat = userModelList.get(position).getLat();
        Double lon = userModelList.get(position).getLon();


        holder.name.setText(name);
        holder.telefono.setText(tel);
        holder.web.setText(webb);
        LatLng sydney = new LatLng(lat, lon);

        /*map.addMarker(new MarkerOptions().position(sydney).title("Marker"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        holder.mapp.onCreate(null);
        holder.mapp.getMapAsync(this);
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
        private MapView mapp ;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.textView8);
            telefono=(TextView) v.findViewById(R.id.textView9);
            image = (ImageView) v.findViewById(R.id.imageView2);
            web=(TextView) v.findViewById(R.id.textView);
            mapp=(MapView) v.findViewById(R.id.mapView);




        }
    }

}
