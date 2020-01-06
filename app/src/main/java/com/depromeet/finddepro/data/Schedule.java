package com.depromeet.finddepro.data;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Schedule {
    private int id;
    // private transient int weeks;  추후에 필요할 때 사용
    private long date;
    private String content;


    public Schedule(int id, long date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getWeeksStr() {
        //몇 월의 몇 번째 주인지 string으로 반환 -> 추후에 형식에 맞게 바꾸기
        String dateStr = getDateStr();
        String dates[] = dateStr.split("/");

        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        String strWeeks = String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH));
        String result = dates[1] + "월 " + strWeeks + "번째 주";
        return result;

    }

    public String getDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String strDate = format.format(new Date(date));
        return strDate;
    }

    public String getContent() {
        return content;
    }
}
