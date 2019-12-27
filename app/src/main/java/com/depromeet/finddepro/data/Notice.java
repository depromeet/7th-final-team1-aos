package com.depromeet.finddepro.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Notice {
    private int id;
    private String title;
    private String content;
    private long createAt;

    public Notice(int id, String title, String content, long createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
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

    public String getCreateAtStr() {
        Date date = new Date(createAt);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        return format.format(date);
    }
}
