package com.vapeur.beans;

import java.io.Serializable;

public class GamePlatform implements Serializable {
    private static final long serialVersionUID = 1L;

    private int gameId;
    private int platformId;

    // Constructeurs
    public GamePlatform() {
    }

    public GamePlatform(int gameId, int platformId) {
        setGameId(gameId);
        setPlatformId(platformId);
    }

    // Getters et setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "GamePlatform{" +
                "gameId=" + gameId +
                ", platformId=" + platformId +
                '}';
    }
}
