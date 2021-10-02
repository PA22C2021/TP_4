package com.example.tp_4.domain;

public class Categoria
{
    public Categoria()
    {

    }

    public Categoria(int id, String name)
    {
        this.Id = id;
        this.Name = name;
    }

    private int Id;
    private String Name;

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

    @Override
    public String toString() {
        return this.Name;
    }
}
