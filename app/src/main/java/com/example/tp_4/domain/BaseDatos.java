package com.example.tp_4.domain;

import java.util.ArrayList;

public class BaseDatos
{
    public BaseDatos()
    {

    }

    public ArrayList<Categoria> GetCategorias()
    {
        ArrayList<Categoria> categoriasList = new ArrayList<Categoria>();
        
        // TODO: Quitar este seteo fijo e ir a buscar a la base los registros!!!!
        categoriasList.add(new Categoria(1, "Fruta"));
        categoriasList.add(new Categoria(2, "Verdura"));
        categoriasList.add(new Categoria(3, "Limpieza"));
        categoriasList.add(new Categoria(4, "Plomeria"));
        categoriasList.add(new Categoria(5, "Carne"));

        return categoriasList;
    }

    public ArrayList<Producto> GetProductos()
    {
        ArrayList<Producto> productosList = new ArrayList<Producto>();

        // TODO: Quitar este seteo fijo e ir a buscar a la base los registros!!!!
        productosList.add(new Producto(1, "Manzana", 5, 1));
        productosList.add(new Producto(2, "Mandarina", 4, 2));
        productosList.add(new Producto(3, "Lechuga", 3, 1));
        productosList.add(new Producto(4, "Vacio", 5, 2));
        productosList.add(new Producto(5, "Tira de asado",  4, 1));

        return productosList;
    }

    public Producto GetProductoId(String id)
    {
        // Si el producto no está en la BD que quede en null.
        Producto producto = null;

        // TODO: Buscar el producto con el ID!!!
        switch (id)
        {
            case "1": producto = new Producto(1, "Manzana", 5, 1); break;
            case "2": producto = new Producto(2, "Mandarina", 4, 2); break;
            case "3": producto = new Producto(3, "Lechuga", 3, 1); break;
            case "4": producto = new Producto(4, "Vacio", 5, 2); break;
            case "5": producto = new Producto(5, "Tira de asado", 4, 1); break;
        }

        return producto;
    }

    public Categoria GetCategoriaId(int id)
    {
        Categoria categoria = null;

        // TODO: Buscar el producto con el ID!!!
        switch (id)
        {
            case 1: categoria = new Categoria(1, "Fruta"); break;
            case 2: categoria = new Categoria(2, "Verdura"); break;
            case 3: categoria = new Categoria(3, "Limpieza"); break;
            case 4: categoria = new Categoria(4, "Plomeria"); break;
            case 5: categoria = new Categoria(5, "Carne"); break;
        }

        return categoria;
    }

    public boolean ValidateExistID(String toString) {
        return false;
    }
}
