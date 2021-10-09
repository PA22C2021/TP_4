package com.example.tp_4.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.tp_4.domain.Categoria;
import com.example.tp_4.domain.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import gui.fragment.ListadoFragment;

public class DataMainActivity extends AsyncTask<String, Void, String> {

    private ListView lvClientes;
    private Context context;
    private static String result2;
    private static ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();

    public DataMainActivity(ListView lv, Context ct){
        lvClientes = lv;
        context = ct;
    }


    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Categorias");
            result2 = " ";

            response = "Conexion exitosa";
        }
        catch (Exception e){
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        ClienteAdapter adapter = new ClienteAdapter(context, listaClientes);
        lvClientes.setAdapter(adapter);
    }
}