package com.depromeet.finddepro.data;

public class User {
    //todo (hee: 임시 user)
    private String id;
    private String name;
    private String profilePath;
    private String email;
    private boolean pushAlarm;

    public User(String id, String name, String email, String profilePath, boolean pushAlarm) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePath = profilePath;
        this.pushAlarm = pushAlarm;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getPushAlarm() {
        return pushAlarm;
    }

    public String getProfileUrl() {
        return profilePath;
    }

    public String getEmail() {
        return email;
    }
}
