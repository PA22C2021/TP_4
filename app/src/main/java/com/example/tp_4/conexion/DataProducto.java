package com.example.tp_4.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tp_4.domain.Categoria;
import com.example.tp_4.domain.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataProducto extends AsyncTask<String, Void, String> {

    ListView lvProductos;
    private Context context;
    private static String result2;
    private static ArrayList<Producto> listaProducto;

    public DataProducto(Context ct, ListView lv){
        context = ct;
        lvProductos = lv;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT a.*, c.descripcion as categoria FROM `articulo` a LEFT JOIN `categoria` c ON a.idCategoria = c.id");
            result2 = " ";

            listaProducto = new ArrayList<Producto>();
            while(rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setName(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria(new Categoria(rs.getInt("idCategoria"), rs.getString("categoria")));
                listaProducto.add(producto);
            }

            con.close();

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
        ArrayAdapter<Producto> arrayAdapter = new ArrayAdapter<Producto>(context, android.R.layout.simple_list_item_1, listaProducto);
        lvProductos.setAdapter(arrayAdapter);
    }
}