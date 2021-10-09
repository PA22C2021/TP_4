package com.example.tp_4.domain;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tp_4.conexion.Accion;
import com.example.tp_4.conexion.DataMainActivity;
import com.example.tp_4.conexion.DataProducto;
import com.example.tp_4.conexion.ProductoABM;

import java.util.ArrayList;

public class BaseDatos
{
    public BaseDatos()
    {

    }

    public void SetCategoriasSpinner(View view, Spinner sp)
    {
        DataMainActivity task = new DataMainActivity(view.getContext(), sp);
        task.execute();
    }

    public void SetProductos(View view, ListView lvProductos)
    {
        DataProducto task = new DataProducto(view.getContext(), lvProductos);
        task.execute();
    }

    public void InsertProductoId(View view, EditText etId, EditText etNom, EditText etSto, Spinner spCat)
    {
        ProductoABM task = new ProductoABM(Accion.Alta, view.getContext(), etId, etNom, etSto, spCat);
        task.execute();
    }

    public void ModifyProductoId(View view, EditText etId, EditText etNom, EditText etSto, Spinner spCat)
    {
        ProductoABM task = new ProductoABM(Accion.Edicion, view.getContext(), etId, etNom, etSto, spCat);
        task.execute();
    }

    public void SetProductoId(View view, EditText etId, EditText etNom, EditText etSto, Spinner spCat)
    {
        ProductoABM task = new ProductoABM(Accion.GetId, view.getContext(), etId, etNom, etSto, spCat);
        task.execute();
    }

    public boolean ValidateExistID(String toString) {
        return false;
    }

}
