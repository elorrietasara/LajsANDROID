package com.example.reservas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MisReservasActivity extends AppCompatActivity {
    List<Reserva> mDataset=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reservas);

        recyclerView=findViewById(R.id.res);
        new ConexionReservas().execute();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

    }



    public class ConexionReservas extends AsyncTask<Void, Void, List<Reserva>> {

        private static final String url = "jdbc:mysql://192.168.101.35:3306/alojamientos?serverTimezone=UTC";
        private static final String user = "lajs";
        private static final String password = "lajs";
        List<Reserva> nombres = new ArrayList<Reserva>();
        String res = "";
        Integer i = 0;


        @Override
        protected List<Reserva> doInBackground(Void... stirn) {


            try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";

                Statement st = con.createStatement();
                Statement st2 = con.createStatement();
        /*Se ejecutará la consulta que devolverá todas las filas almacenadas,
        a excepción del campo id.*/


                SharedPreferences sh = getSharedPreferences("datosUsu", Context.MODE_PRIVATE);


                Integer id = sh.getInt("idUsu", 0);

                ResultSet rs = st.executeQuery("Select * from reserva where idUsr="+id+";");
                ResultSet rs2=null;


                while (rs.next()) {


                          rs2 = st2.executeQuery("Select * from alojamiento where idAloj="+rs.getString("idAloj")+";");
                         while (rs2.next()) {
                             Reserva reserva= new Reserva(rs2.getString("nombre"),rs.getString("fechaEntrada"),rs.getString("fechaSalida"));


                            mDataset.add(reserva);

                    }




                }
                rs2.close();
                rs.close();



            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }

            return mDataset;
        }

        @Override
        protected void onPostExecute(List<Reserva> lista) {

            super.onPostExecute(lista);
            // Limpiar elementos antiguos


            // Añadir elementos nuevos

            recyclerView.setHasFixedSize(true);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(MisReservasActivity.this);
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)
            mAdapter = new MyAdapterReservas(mDataset);
            recyclerView.setAdapter(mAdapter);

            // Parar la animación del indicador

            // List<String> aux=(List<String>)obj;
            // System.out.print("Hola  " + aux.size() );


        }
    }
}
