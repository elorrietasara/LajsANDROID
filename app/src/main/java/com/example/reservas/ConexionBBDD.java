package com.example.reservas;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.SearchManager.QUERY;

public class ConexionBBDD   extends AsyncTask<String, Void, String> {

    String res = "";

    private static final String url = "jdbc:mysql://192.168.101.35:3306/alojamientos?serverTimezone=UTC";
    private static final String user = "lajs";
    private static final String password = "lajs";

    @Override
    protected String doInBackground(String... stirn) {

        System.out.print("DATOS:"+stirn[0]+"  "+stirn[1]+"  "+stirn[2]+"  "+stirn[3]);

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Databaseection success");

            String result = "Database Connection Successful\n";


            SimpleDateFormat sdf=new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy");
            SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");

            Date date1= sdf.parse(stirn[0]);
            Date date2= sdf.parse(stirn[1]);


            java.sql.Timestamp SQLdate1 = new java.sql.Timestamp(date1.getTime());
            java.sql.Timestamp SQLdate2 = new java.sql.Timestamp(date2.getTime());


            java.util.Date today = new java.util.Date();

            PreparedStatement ps= (PreparedStatement) con.prepareStatement("insert into reserva (fechaEntrada,fechaSalida,idAloj,idUsr) values(?,?,?,?)");

            ps.setTimestamp(1,SQLdate1);
            ps.setTimestamp(2,SQLdate2);
            ps.setInt(3,Integer.parseInt(stirn[2]));
            ps.setInt(4,Integer.parseInt(stirn[3]));
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

   /* public void insertar(Date e, Date s, Integer idAloj, Integer idUsu) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Databaseection success");

            String result = "Database Connection Successful\n";
            Statement st = con.createStatement();

            String peticion ="INSERT INTO `reserva`( `fechaEntrada`, `fechaSalida`, `idAloj`, `idUsr`) VALUES ("+e+","+s+","+idAloj+","+idUsu+")";
            st.executeUpdate(peticion);


           /* while (rs.next()) {
                result += rs.getString(1).toString() + "\n";
                Log.d(result.toString(),"HOLA");
            }
            res = result;
            con.close();
        } catch (Exception ee) {
            ee.printStackTrace();

        }

    }



  /* public void conectar() {
       Connection conn=null;
       try {

           Class.forName("com.mysql.jdbc.Driver").newInstance();
           conn = DriverManager.getConnection(url, user, password);
           Log.d("HOLA1","HOLA");
           PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM reserva");
           ResultSet rs = stmt.executeQuery();

           while (rs.next())
               System.out.println(rs.getString("idRes"));
           Log.d("HOLA","HOLA");

           conn.close();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InstantiationException eee) {
           eee.printStackTrace();
       } catch (SQLException ee) {
           ee.printStackTrace();
       } catch (ClassNotFoundException eeee) {
           eeee.printStackTrace();
       }


   }


     /*   public void insertarReserva(Date e, Date s, Integer idAloj, Integer idUsu){


        Statement estado = null;
        try {
            Statement st = conn.createStatement();

            String peticion ="INSERT INTO `reserva`(`idRes`, `fechaEntrada`, `fechaSalida`, `idAloj`, `idUsr`) VALUES ('',"+e+","+s+","+idAloj+","+idUsu+")";

            ResultSet rs = st.executeQuery(peticion);
            conn.close();

        } catch (SQLException r) {
            r.printStackTrace();
        }




    }*/

}
