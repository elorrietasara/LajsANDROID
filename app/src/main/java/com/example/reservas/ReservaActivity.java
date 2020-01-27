package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservaActivity extends AppCompatActivity {
    TextView nombre;
    TextView fechaE;
    TextView fechaS;

    TextView des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        SimpleDateFormat dt1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy");

        nombre=findViewById(R.id.textView19);
        fechaE=findViewById(R.id.textView20);
        fechaS=findViewById(R.id.textView21);

        des=findViewById(R.id.textView23);

        Bundle bundle = getIntent().getExtras();
        nombre.setText(nombre.getText()+"  "+bundle.getString("Nombre"));
        try {
            fechaE.setText(fechaE.getText()+"  "+dt2.format(dt1.parse(bundle.getString("FechaE"))));
            fechaS.setText(fechaS.getText()+"  "+dt2.format(dt1.parse(bundle.getString("FechaS"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        des.setText(bundle.getString("Descripcion"));


    }
}
