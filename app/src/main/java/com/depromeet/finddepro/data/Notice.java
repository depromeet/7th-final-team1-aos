package com.depromeet.finddepro.data;

public class Notice {
    private int id;
    private String title;
    private String content;

    public Notice(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
