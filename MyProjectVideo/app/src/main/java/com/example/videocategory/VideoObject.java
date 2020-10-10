package com.example.videocategory;

public class VideoObject {
    int id;
    String title;
    String avatar;
    String date_created;
    String date_modified;
    String thumb;
    String url;

    public VideoObject() {
    }

    public VideoObject(int id, String title, String avatar, String date_created, String date_modified, String thumb, String url) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
        this.date_created = date_created;
        this.date_modified = date_modified;
        this.thumb = thumb;
        this.url = url;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
