package com.example.reservas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {
    String usuario;
    EditText usu;
    EditText contr;
    ArrayList<String> datos= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usu=findViewById(R.id.editText3);
        contr=findViewById(R.id.editText4);
    }



    public void log(View v){

      new  ConexionUsuarios().execute(usu.getText().toString(), contr.getText().toString());




    }






    public class ConexionUsuarios  extends AsyncTask<String, Void, ArrayList<String>> {

        private static final String url = "jdbc:mysql://192.168.101.35:3306/alojamientos?serverTimezone=UTC";
        private static final String user = "lajs";
        private static final String password = "lajs";

        String res = "";
        Integer i = 0;
        Boolean salir=false;


        @Override
        protected ArrayList<String> doInBackground(String... stirn) {


            try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";

                Statement st = con.createStatement();
        /*Se ejecutará la consulta que devolverá todas las filas almacenadas,
        a excepción del campo id.*/
                ResultSet rs = st.executeQuery("Select * from usuario ");
                if(stirn[0].toString().equals("")||stirn[1].toString().equals("")){
                   /* datos.add("");
                    datos.add("");*/

                }else {
                    String resultado = "";
                    while (rs.next() && salir == false) {

                        if (!rs.getString("username").equals(stirn[0])) {
                           /* datos.add("");
                            datos.add("");*/


                        } else {
                            String contrasena =encriptar(stirn[1]);
                            if (contrasena.equalsIgnoreCase(rs.getString("password"))) {
                                datos.add(stirn[0]);
                                datos.add(contrasena);



                                 salir = true;
                            }else{
                                /*datos.add("");
                                datos.add("");*/

                              System.out.print("NO COINCIDE");
                            }


                        }

                    }


                    //System.out.print(resultado);
                }

            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }

            return datos;

        }

        @Override
        protected void onPostExecute(ArrayList<String> datos) {

            if(datos.size()==0 ){
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("ERROR")
                        .setContentText("Datos incorrectos")
                        .show();
            }else{
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }




        }
    }

    public static String encriptar(String s) throws Exception {
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
        public void registro(View v){

            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
            finish();

        }
}
