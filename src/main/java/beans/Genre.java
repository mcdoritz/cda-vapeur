package beans;

import java.io.Serializable;

public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private int gameId;

    // Constructeurs
    public Genre() {
    }

    public Genre(int id, String name, int gameId) {
        setId(id);
        setName(name);
        setGameId(gameId);
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}