package beans;

import java.io.Serializable;

public class GameMode implements Serializable {
    private static final long serialVersionUID = 1L;

    private int gameId;
    private int modeId;

    // Constructeurs
    public GameMode() {
    }

    public GameMode(int gameId, int modeId) {
        setGameId(gameId);
        setModeId(modeId);
    }

    // Getters et setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "GameMode{" +
                "gameId=" + gameId +
                ", modeId=" + modeId +
                '}';
    }
}
