package com.depromeet.finddepro.data;

public class User {
    //todo (hee: 임시 user)
    private String id;
    private String name;
    private String profileUrl;
    private String email;

    public User(String id, String name, String email, String profileUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getEmail() {
        return email;
    }
}
