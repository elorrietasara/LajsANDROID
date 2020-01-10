package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    List<Alojamieno> mDataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creamos la vista de la lista
        recyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mDataset = new ArrayList<Alojamieno>();
        //LEER JASON
        try {
            JSONArray jArray = new JSONArray(readJSONFromAsset());
            for (int i = 0; i < jArray.length(); ++i) {
                String name = jArray.getJSONObject(i).getString("nombre");// name of the country
                String telf = jArray.getJSONObject(i).getString("telefono");// name of the country
                String web = jArray.getJSONObject(i).getString("web");
                Double lat=Double.parseDouble(jArray.getJSONObject(i).getString("latitud"));
                Double lon=Double.parseDouble(jArray.getJSONObject(i).getString("longitud"));
                Alojamieno alojamiento= new Alojamieno(name,telf,web, lat ,lon);
                mDataset.add(alojamiento);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    // specify an adapter (see also next example)

        mAdapter = new MyAdapter(mDataset);
        recyclerView.setAdapter(mAdapter);
    }
    public String readJSONFromAsset() {
        String json = null;
        String jason = "alojt.json";
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
        switch (item.getItemId()) {
            case R.id.mapa:

                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
