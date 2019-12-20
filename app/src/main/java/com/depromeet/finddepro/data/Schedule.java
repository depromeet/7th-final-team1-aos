package com.depromeet.finddepro.data;

public class Schedule {
    private int id;
    private int weeks;
    private String date;
    private String content;


    public Schedule(int id, int weeks, String date, String content) {
        this.id = id;
        this.weeks = weeks;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getWeeks() {
        return weeks;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
