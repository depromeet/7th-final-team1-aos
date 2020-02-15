package com.depromeet.finddepro.data;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Schedule {
    private int id;
    private long date;
    private String content;
    private transient int weeks;

    public Schedule(int id, int weeks, long date, String content) {
        this.id = id;
        this.weeks = weeks;
        this.date = date;
        this.content = content;
    }

    public String getWeeksStr() {

        return weeks + "주차";
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    //오늘이 년 중 몇 번째 주
    public int getTodayWeekOfYear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        return getWeekOfYear(format.format(new Date()));
    }

    //아이템(스케줄)이 년 중 몇 번째 주
    public int getItemWeekOfYear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        return getWeekOfYear(format.format(new Date(date)));
    }

    public String getDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd", Locale.getDefault());
        return format.format(new Date(date));
    }


    //년의  몇 번째 주 계산
    private int getWeekOfYear(String dateStr) {
        String[] dates = dateStr.split("/");

        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);


        return calendar.get(Calendar.WEEK_OF_YEAR);

    }


    /* 현재 사용 안 함
    private String getWeeksStr() {
        //몇 월의 몇 번째 주인지 string으로 반환 -> 추후에 형식에 맞게 바꾸기
        String dateStr = getDateStr();
        String[] dates = dateStr.split("/");

        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(month - 1, day);

        String strWeeks = String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)) + "주";
        return strWeeks;

    }*/
}
