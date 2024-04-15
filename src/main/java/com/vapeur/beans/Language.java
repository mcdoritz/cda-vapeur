package com.vapeur.beans;

import java.io.Serializable;

public class Language implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String localeCode;

    // Constructeurs
    public Language() {
    }

    public Language(int id, String localeCode) {
        setId(id);
        setLocaleCode(localeCode);
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", localeCode='" + localeCode + '\'' +
                '}';
    }
}