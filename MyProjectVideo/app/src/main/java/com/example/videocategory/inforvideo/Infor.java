package com.example.videocategory.inforvideo;

public class Infor {
    private String name;
    private int time;
    private String description;
    private int id_video;

    public Infor() {
    }
    public Infor(String name, int time, String description, int id_video) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.id_video = id_video;
    }

    public int getId_video() {
        return id_video;
    }

    public void setId_video(int id_video) {
        this.id_video = id_video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
