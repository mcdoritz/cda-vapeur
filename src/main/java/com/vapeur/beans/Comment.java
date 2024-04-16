package com.vapeur.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String content;
    private Timestamp uploaded;
    private float score;
    private int userId;

    public Comment() {
    }

    public Comment(int id, String content, Timestamp uploaded, float score, int userId) {
        setId(id);
        setContent(content);
        setUploaded(uploaded);
        setScore(score);
        setUserId(userId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getUploaded() {
        return uploaded;
    }

    public void setUploaded(Timestamp uploaded) {
        this.uploaded = uploaded;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", uploaded=" + uploaded +
                ", score=" + score +
                ", userId=" + userId +
                '}';
    }
}