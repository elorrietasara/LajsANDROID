package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            Toast.makeText(this,"Introduzca nombre", Toast.LENGTH_LONG).show();

        }else if(apellido.getText().toString().equals("")) {
            Toast.makeText(this, " Introduzca apellido", Toast.LENGTH_LONG).show();


        }
        else if(dni.getText().toString().equals("") || !validarNIF(dni.getText().toString())) {
            Toast.makeText(this, "Introduzca dni valido ", Toast.LENGTH_LONG).show();

        }
        else if(usuario.getText().toString().equals("")) {
            Toast.makeText(this, " Introduzca usuario", Toast.LENGTH_LONG).show();

        }
       else  if(contraseña.getText().toString().equals("")) {
            Toast.makeText(this, "Introduzca contraseña", Toast.LENGTH_LONG).show();

        }
       else if(nacimiento.getText().toString().equals("")){
            Toast.makeText(this,"Introduzca fecha", Toast.LENGTH_LONG).show();

        }



        else{

            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
            new registroUsuarioBBDD().execute(nombre.getText().toString(),apellido.getText().toString(),dni.getText().toString(),usuario.getText().toString(),nacimiento.getText().toString(),contraseña.getText().toString());
            startActivity(intent);
            finish();

        }


    }

    public class registroUsuarioBBDD   extends AsyncTask<Object, Void, String> {

        String res = "";

        private static final String url = "jdbc:mysql://192.168.101.35:3306/alojamientos?serverTimezone=UTC";
        private static final String user = "lajs";
        private static final String password = "lajs";

        @Override
        protected String doInBackground(Object... stirn) {

            System.out.print("DATOS:"+stirn[0]+"  "+stirn[1]+"  "+stirn[2]+"  "+stirn[3]);

            try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";





                java.sql.Date SQLdate1 = new java.sql.Date(((java.util.Date)calendario.getTime()).getTime());


                PreparedStatement ps= (PreparedStatement) con.prepareStatement("insert into usuario (apellidos,dni,fechaNac,nombre,password,username,admin) values(?,?,?,?,?,?,?)");

                ps.setString(1,((String)stirn[1]));
                ps.setString(2,(String)stirn[2]);
                ps.setDate(3,SQLdate1);
                ps.setString(4,(String)stirn[0]);
                ps.setString(5,encriptar((String)stirn[5]));
                ps.setString(6,(String)stirn[3]);
                ps.setInt(7,0);
                ps.executeUpdate();

                ps.close();


                res = result;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String lista) {






        }

        public  String encriptar(String s) throws Exception {
            try{
                MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                digest.update(s.getBytes());
                byte messageDigest[] = digest.digest();
                StringBuilder hexString = new StringBuilder();
                for(int i = 0; i < messageDigest.length; i++){
                    String h = Integer.toHexString(0xFF & messageDigest[i]);
                    while(h.length() < 2){
                        h = "0" + h;
                    }
                    hexString.append(h);
                }
                return hexString.toString();
            }catch (NoSuchAlgorithmException e){
                Log.e("MD5", "md5() NoSuchAlgorithmException: " + e.getMessage());
            }
            return "";

        }




    }
    public  boolean validarNIF(String nif) {

        boolean correcto = false;

        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

        Matcher matcher = pattern.matcher(nif);

        if (matcher.matches()) {

            String letra = matcher.group(2);

            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

            int index = Integer.parseInt(matcher.group(1));

            index = index % 23;

            String reference = letras.substring(index, index + 1);



            if (reference.equalsIgnoreCase(letra)) {

                correcto = true;

            } else {

                correcto = false;

            }

        } else {

            correcto = false;

        }

        return correcto;

    }










}
