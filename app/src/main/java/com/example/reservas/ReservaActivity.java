package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReservaActivity extends AppCompatActivity {
    String nombre;
    String fechaE;
    String fechaS;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        text=findViewById(R.id.Resumen);

        Bundle bundle = getIntent().getExtras();
        nombre=bundle.getString("Nombre");
        fechaE=bundle.getString("FechaE");
        fechaS=bundle.getString("FechaS");

        text.setText("Nombre del alojamiento:"+ nombre+"\n"+"Fecha de entrada: "+fechaE+"\n"+"Fecha de salida: "+fechaS);
    }
}
