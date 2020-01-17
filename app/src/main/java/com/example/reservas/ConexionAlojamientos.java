package com.example.reservas;

import android.os.AsyncTask;

import androidx.viewpager.widget.PagerAdapter;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ConexionAlojamientos  extends AsyncTask {

    String res = "";

    private static final String url = "jdbc:mysql://192.168.101.35:3306/alojamientos?serverTimezone=UTC";
    private static final String user = "lajs";
    private static final String password = "lajs";
    ArrayList<String> nombres;

    @Override
    protected ArrayList<String> doInBackground(Object... stirn) {



        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Databaseection success");

            String result = "Database Connection Successful\n";

          Statement st = con.createStatement();
        /*Se ejecutará la consulta que devolverá todas las filas almacenadas,
        a excepción del campo id.*/
            ResultSet rs = st.executeQuery("Select * from alojamiento ");

            String resultado="";
            while(rs.next())
            {
                resultado += rs.getString("nombre");
                resultado += rs.getString("telefono") +"\n";
                nombres.add(rs.getString("nombre"));

            }



        System.out.print(resultado);


        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }
        return nombres;
    }
}
