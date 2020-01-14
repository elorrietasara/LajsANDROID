package com.example.reservas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    List<Alojamieno> mDataset;
    List<Alojamieno> aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creamos la vista de la lista
        recyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new FadeInDownAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        mDataset = new ArrayList<Alojamieno>();
        try {
            JSONArray jArray = new JSONArray(readJSONFromAsset());
            for (int i = 0; i < jArray.length(); ++i) {
                String name = jArray.getJSONObject(i).getString("nombre");// name of the country
                String telf = jArray.getJSONObject(i).getString("telefono");// name of the country
                String web = jArray.getJSONObject(i).getString("web");
                String descrip = jArray.getJSONObject(i).getString("descripcion");
                String localidad = jArray.getJSONObject(i).getString("localidad");
                Double lat = Double.parseDouble(jArray.getJSONObject(i).getString("latitud"));
                Double lon = Double.parseDouble(jArray.getJSONObject(i).getString("longitud"));
                String tipo=jArray.getJSONObject(i).getString("tipo");
                Integer capaz = Integer.parseInt(jArray.getJSONObject(i).getString("capacidad"));
                Alojamieno alojamiento = new Alojamieno(name, telf, web, lat, lon, descrip, localidad,tipo,capaz);
                mDataset.add(alojamiento);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mAdapter = new MyAdapter(mDataset);
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
    }






    public String readJSONFromAsset() {
        String json = null;
        String jason = "alojR.json";
        try {
            InputStream is = getAssets().open(jason);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.mapa:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.filtros:
                LayoutInflater layoutinflater = LayoutInflater.from(this);
                final View promptUserView = layoutinflater.inflate(R.layout.activity_filter, null);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                Button boton = (Button) promptUserView.findViewById(R.id.button);
                alertDialogBuilder.setView(promptUserView);
                alertDialogBuilder.setTitle("Filtros");
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
                SeekBar seekbar = (SeekBar)promptUserView.findViewById(R.id.seekBar2);
                final  TextView capacidad = (TextView) promptUserView.findViewById(R.id.capaz);
                seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                        capacidad.setText(""+i);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Spinner provincias = (Spinner) promptUserView.findViewById(R.id.spinner);
                        Spinner tipos = (Spinner) promptUserView.findViewById(R.id.spinner2);
                        String tipo = tipos.getSelectedItem().toString();
                        String provincia = provincias.getSelectedItem().toString();





                        mDataset.clear();
                        try {
                            aux = new ArrayList<Alojamieno>();
                            JSONArray jArray = new JSONArray(readJSONFromAsset());
                            for (int i = 0; i < jArray.length(); ++i) {
                                String name = jArray.getJSONObject(i).getString("nombre");// name of the country
                                String telf = jArray.getJSONObject(i).getString("telefono");// name of the country
                                String web = jArray.getJSONObject(i).getString("web");
                                String descrip = jArray.getJSONObject(i).getString("descripcion");
                                String localidad = jArray.getJSONObject(i).getString("localidad");
                                Double lat = Double.parseDouble(jArray.getJSONObject(i).getString("latitud"));
                                Double lon = Double.parseDouble(jArray.getJSONObject(i).getString("longitud"));
                                String tipe=jArray.getJSONObject(i).getString("tipo");
                                Integer capaz = Integer.parseInt(jArray.getJSONObject(i).getString("capacidad"));
                                Alojamieno alojamiento = new Alojamieno(name, telf, web, lat, lon, descrip, localidad,tipe, capaz);
                                aux.add(alojamiento);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < aux.size(); i++) {
                            String loc= aux.get(i).getLocalidad().toString();

                            if(provincia.equals("Elegir...") && tipo.equals("Elegir...") && Integer.parseInt(capacidad.getText().toString())==0 ){
                                mDataset.add(aux.get(i));
                            }else {

                                mDataset.add(aux.get(i));
                                if (!tipo.equals("Elegir...")  && !provincia.equals("Elegir...")&& Integer.parseInt(capacidad.getText().toString()) == 0) {
                                    //mDataset.add(aux.get(i));
                                    if (!loc.replace(" ", "").contains(provincia.replace(" ", ""))|| mDataset.size() != 0  && !mDataset.get(mDataset.size() - 1).getTipo().toString().equals(tipo) ) {
                                        mDataset.remove(mDataset.size() - 1);
                                    }
                                  /*  if ( mDataset.size() != 0 && !mDataset.get(mDataset.size() - 1).getTipo().toString().equals(tipo) ) {
                                        mDataset.remove(mDataset.size() - 1);

                                    }*/
                                    /*if (Integer.parseInt(aux.get(i).getCapacidad().toString()) > Integer.parseInt(capacidad.getText().toString()) && mDataset.size() != 0) {
                                        mDataset.remove(mDataset.size() - 1);

                                    }*/
                                }
                                else if (tipo.equals("Elegir...") && mDataset.size() != 0 && Integer.parseInt(capacidad.getText().toString()) != 0 && provincia.equals("Elegir...")) {
                                    //mDataset.add(aux.get(i));
                                    if (Integer.parseInt(aux.get(i).getCapacidad().toString()) > Integer.parseInt(capacidad.getText().toString())) {
                                        mDataset.remove(mDataset.size() - 1);

                                    }

                                }
                                else if (!tipo.equals("Elegir...") && mDataset.size() != 0 && Integer.parseInt(capacidad.getText().toString()) == 0 && provincia.equals("Elegir...")) {
                                    //mDataset.add(aux.get(i));
                                    if (!mDataset.get(mDataset.size() - 1).getTipo().toString().equals(tipo)) {
                                        mDataset.remove(mDataset.size() - 1);

                                    }
                                }
                                else if (tipo.equals("Elegir...") && mDataset.size() != 0 && Integer.parseInt(capacidad.getText().toString()) == 0 && !provincia.equals("Elegir...")) {
                                   // mDataset.add(aux.get(i));
                                    if (!loc.replace(" ", "").contains(provincia.replace(" ", ""))) {
                                        mDataset.remove(mDataset.size() - 1);
                                    }
                                }
                                else if (!tipo.equals("Elegir...") && mDataset.size() != 0 && Integer.parseInt(capacidad.getText().toString()) != 0 && !provincia.equals("Elegir...")) {
                                    // mDataset.add(aux.get(i));
                                    if (!loc.replace(" ", "").contains(provincia.replace(" ", ""))|| !mDataset.get(mDataset.size() - 1).getTipo().toString().equals(tipo)|| Integer.parseInt(aux.get(i).getCapacidad().toString()) > Integer.parseInt(capacidad.getText().toString())) {
                                        mDataset.remove(mDataset.size() - 1);
                                    }
                                }
                                else if (!tipo.equals("Elegir...") && mDataset.size() != 0 && Integer.parseInt(capacidad.getText().toString()) != 0 && provincia.equals("Elegir...")) {
                                    // mDataset.add(aux.get(i));
                                    if (!mDataset.get(mDataset.size() - 1).getTipo().toString().equals(tipo)||Integer.parseInt(aux.get(i).getCapacidad().toString()) > Integer.parseInt(capacidad.getText().toString())) {
                                        mDataset.remove(mDataset.size() - 1);
                                    }
                                }
                                else if (tipo.equals("Elegir...") || mDataset.size() != 0 || Integer.parseInt(capacidad.getText().toString()) != 0 || !provincia.equals("Elegir...")) {
                                    // mDataset.add(aux.get(i));
                                    if (!loc.replace(" ", "").contains(provincia.replace(" ", ""))&& Integer.parseInt(aux.get(i).getCapacidad().toString()) > Integer.parseInt(capacidad.getText().toString())) {
                                        mDataset.remove(mDataset.size() - 1);
                                    }
                                }

                            }

                        }

                        mAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();




                    }
                });


                break;
            case R.id.user:
                LayoutInflater layoutinflater2 = LayoutInflater.from(this);
                final View promptUserView2 = layoutinflater2.inflate(R.layout.login, null);

                final AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(this);

                alertDialogBuilder2.setView(promptUserView2);
                alertDialogBuilder2.setTitle("Login");
                final AlertDialog alertDialog2 = alertDialogBuilder2.create();
                alertDialog2.setCancelable(false);
                alertDialog2.show();
                Button boton2 = (Button) promptUserView2.findViewById(R.id.button3);
                final EditText usu = (EditText) promptUserView2.findViewById(R.id.editText);
                final EditText contra = (EditText) promptUserView2.findViewById(R.id.editText2);
                boton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (usu.getText().toString().equals("") && contra.getText().toString().equals("")) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(promptUserView2.getContext());
                            builder.setTitle("ERROR");
                            builder.setMessage("¡Introduzca los datos!");
                            final AlertDialog ad = builder.create();
                            ad.show();

                        } else {
                            alertDialog2.dismiss();
                        }

                    }
                });
                break;


        }
        return super.onOptionsItemSelected(item);
    }

//LEER DE JSON


}


