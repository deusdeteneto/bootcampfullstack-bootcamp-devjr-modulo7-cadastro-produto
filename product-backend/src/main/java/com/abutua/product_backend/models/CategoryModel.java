package com.abutua.product_backend.models;

public class CategoryModel {
 
    // Atributos
    private int id;
    private String name;

    // Construtor
    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

        public CategoryModel() {
    }

    // Métodos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
