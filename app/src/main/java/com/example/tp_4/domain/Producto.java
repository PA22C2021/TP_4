package com.example.tp_4.domain;

public class Producto {

    public Producto() {
    }

    public Producto(int id, String name, int stock, int idCategoria)
    {
        this.Id = id;
        this.Name = name;
        this.Stock = stock;
        this.IdCategoria = idCategoria;
    }

    private int Id;
    private String Name;
    private int Stock;
    private int IdCategoria;
    Categoria Categoria;

    @Override
    public String toString() {
        return Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public com.example.tp_4.domain.Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(com.example.tp_4.domain.Categoria categoria) {
        Categoria = categoria;
    }
}
