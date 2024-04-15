package com.vapeur.beans;

import java.io.Serializable;

public class Platform implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;

    // Constructeurs
    public Platform() {
    }

    public Platform(int id, String name) {
        setId(id);
        setName(name);
    }

    // Getters et setters
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

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}