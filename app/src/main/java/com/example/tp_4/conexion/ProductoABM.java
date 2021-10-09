package com.example.tp_4.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_4.domain.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class ProductoABM extends AsyncTask<String, Void, String> {

    private static String result2;

    private Producto producto;
    private EditText etId, etNombreProducto, etStock;
    private Spinner spinner;
    private Context context;
    private Accion accion;

    public ProductoABM(Accion accion, Context ct, EditText etId, EditText etNomPro, EditText etSt, Spinner spCat)
    {
        this.context = ct;
        this.etId = etId;
        this.etNombreProducto = etNomPro;
        this.etStock = etSt;
        this.spinner = spCat;
        this.accion = accion;
    }

    public ProductoABM(Accion accion, Context ct, EditText etId)
    {
        this.context = ct;
        this.etId = etId;
        this.accion = accion;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();

            if(accion == Accion.ValidateID){
                ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE id = " + etId.getText());
                result2 = " ";

                while(rs.next()) {
                    producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setName(rs.getString("nombre"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setIdCategoria(rs.getInt("idCategoria"));
                }
            }
            if (accion == Accion.GetId)
            {
                ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE id = " + etId.getText());
                result2 = " ";

                while(rs.next()) {
                    producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setName(rs.getString("nombre"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setIdCategoria(rs.getInt("idCategoria"));
                }
            }
            else if (accion == Accion.Edicion)
            {
                String update = "UPDATE articulo SET" +
                        " nombre = '" + etNombreProducto.getText() +
                        "', stock = " + etStock.getText().toString() +
                        ", idCategoria = " + String.valueOf(spinner.getSelectedItemId() + 1) +
                        " WHERE id = " + etId.getText();

                st.executeUpdate(update);
            }
            else if (accion == Accion.Alta)
            {
                String insert = "INSERT INTO `articulo` (`id`, `nombre`, `stock`, `idCategoria`) " +
                        "VALUES ('" + etId.getText() + "', '" + etNombreProducto.getText() + "', '" + etStock.getText().toString() + "', '" + String.valueOf(spinner.getSelectedItemId() + 1) + "')";

                st.executeUpdate(insert);
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

        if(accion == Accion.ValidateID){
            if (producto != null)
            {
                Toast.makeText(context, "El producto (" + etId.getText() + ") ya se encuentra registrado.", Toast.LENGTH_SHORT).show();
            }
        }
        if (accion == Accion.GetId)
        {
            if (producto == null)
            {
                Toast.makeText(context, "El producto (" + etId.getText() + ") no se encuentra registrado.", Toast.LENGTH_SHORT).show();
                LimpiarCampos();
            }
            else
            {
                etNombreProducto.setText(producto.getName());
                etStock.setText(String.valueOf(producto.getStock()));
                spinner.setSelection(producto.getIdCategoria() - 1);
            }
        }
        else if (accion == Accion.Alta)
        {
            etId.setText("");
            LimpiarCampos();
        }
        else if (accion == Accion.Edicion)
        {
            LimpiarCampos();
        }
    }

    private void LimpiarCampos()
    {
        etNombreProducto.setText("");
        etStock.setText("");
        spinner.setSelection(0);
    }
}
