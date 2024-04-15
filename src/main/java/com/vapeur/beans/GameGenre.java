package com.vapeur.beans;

import java.io.Serializable;

public class GameGenre implements Serializable {
    private static final long serialVersionUID = 1L;

    private int gameId;
    private int genreId;

    // Constructeurs
    public GameGenre() {
    }

    public GameGenre(int gameId, int genreId) {
        setGameId(gameId);
        setGenreId(genreId);
    }

    // Getters et setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "GameGenre{" +
                "gameId=" + gameId +
                ", genreId=" + genreId +
                '}';
    }
}
