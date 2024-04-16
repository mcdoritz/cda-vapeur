package com.vapeur.beans;

import java.io.Serializable;

public class Language implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String localeCode;
    private String language;

    // Constructeurs
    public Language() {
    }

    

    public Language(int id, String localeCode, String language) {
		super();
		setId(id);
		setLocaleCode(localeCode);
		setLanguage(language);
	}

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", localeCode=" + localeCode + ", language=" + language + "]";
	}
    
    
}