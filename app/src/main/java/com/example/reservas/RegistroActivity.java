package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistroActivity extends AppCompatActivity {
    EditText nacimiento;
    Calendar calendario = Calendar.getInstance();
    EditText nombre;
    EditText apellido;
    EditText usuario;
    EditText contraseña;
    EditText dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nacimiento=findViewById(R.id.date);
        nombre=findViewById(R.id.editText);
        apellido=findViewById(R.id.editText2);
        usuario=findViewById(R.id.editText7);
        contraseña=findViewById(R.id.editText6);
        dni = findViewById(R.id.editText5);

        nacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistroActivity.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };

    private void actualizarInput() {
        String formatoDeFecha = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        nacimiento.setText(sdf.format(calendario.getTime()));


    }
    public void registro(View v){
        if(nombre.getText().toString().equals("")){
            Toast.makeText(this," Rellene nombre", Toast.LENGTH_LONG).show();

        }else if(apellido.getText().toString().equals("")) {
            Toast.makeText(this, " Rellene apellido", Toast.LENGTH_LONG).show();


        }
        else if(dni.getText().toString().equals("")) {
            Toast.makeText(this, " Rellene dni", Toast.LENGTH_LONG).show();

        }
        else if(usuario.getText().toString().equals("")) {
            Toast.makeText(this, " Rellene usuario", Toast.LENGTH_LONG).show();

        }
       else  if(contraseña.getText().toString().equals("")) {
            Toast.makeText(this, " Rellene contraseña", Toast.LENGTH_LONG).show();

        }
       else if(nacimiento.getText().toString().equals("")){
            Toast.makeText(this," Rellene fecha", Toast.LENGTH_LONG).show();

        }



        else{
            Toast.makeText(this,"Fecha  "+calendario.getTime(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }


    }









}
