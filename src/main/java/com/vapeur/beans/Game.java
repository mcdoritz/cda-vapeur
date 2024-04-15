package com.vapeur.beans;

import java.io.Serializable;
import java.sql.Date;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String title;
    private String description;
    private int classification;
    private float price;
    private Date releaseDate;
    private float usersAvgScore;
    private boolean controllerSupport;
    private boolean requires3rdPartyAccount;
    private int stock;
    private String tags;
    private int developerId;

    // Constructeurs
    public Game() {
    }

    public Game(int id, String title, String description, int classification, float price, Date releaseDate, float usersAvgScore, boolean controllerSupport, boolean requires3rdPartyAccount, int stock, String tags, int developerId) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setClassification(classification);
        setPrice(price);
        setReleaseDate(releaseDate);
        setUsersAvgScore(usersAvgScore);
        setControllerSupport(controllerSupport);
        setRequires3rdPartyAccount(requires3rdPartyAccount);
        setStock(stock);
        setTags(tags);
        setDeveloperId(developerId);
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getUsersAvgScore() {
        return usersAvgScore;
    }

    public void setUsersAvgScore(float usersAvgScore) {
        this.usersAvgScore = usersAvgScore;
    }

    public boolean isControllerSupport() {
        return controllerSupport;
    }

    public void setControllerSupport(boolean controllerSupport) {
        this.controllerSupport = controllerSupport;
    }

    public boolean isRequires3rdPartyAccount() {
        return requires3rdPartyAccount;
    }

    public void setRequires3rdPartyAccount(boolean requires3rdPartyAccount) {
        this.requires3rdPartyAccount = requires3rdPartyAccount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", classification=" + classification +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", usersAvgScore=" + usersAvgScore +
                ", controllerSupport=" + controllerSupport +
                ", requires3rdPartyAccount=" + requires3rdPartyAccount +
                ", stock=" + stock +
                ", tags='" + tags + '\'' +
                ", developerId=" + developerId +
                '}';
    }
}