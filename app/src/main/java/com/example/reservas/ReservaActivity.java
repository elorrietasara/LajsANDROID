package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReservaActivity extends AppCompatActivity {
    TextView nombre;
    TextView fechaE;
    TextView fechaS;

    TextView des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        SimpleDateFormat dt1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy");

        nombre=findViewById(R.id.textView19);
        fechaE=findViewById(R.id.textView20);
        fechaS=findViewById(R.id.textView21);

        des=findViewById(R.id.textView23);

        Bundle bundle = getIntent().getExtras();
        nombre.setText(nombre.getText()+"  "+bundle.getString("Nombre"));
        String fecha1String=bundle.getString("FechaE");
        String fecha2String=bundle.getString("FechaS");
        try {

            Date fecha1=dt1.parse(fecha1String);
            Date fecha2=dt1.parse(fecha2String);
            fecha1String=dt2.format(fecha1);
            fecha2String=dt2.format(fecha2);
        } catch (ParseException e) {
            fecha1String="error";
            fecha2String="error";
        }
        fechaE.setText(fechaE.getText()+"  "+fecha1String);
        fechaS.setText(fechaS.getText()+"  "+fecha2String);




        des.setText(bundle.getString("Descripcion"));


    }
}
