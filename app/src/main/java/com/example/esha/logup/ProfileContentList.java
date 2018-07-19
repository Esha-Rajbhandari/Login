package com.example.esha.logup;

public class ProfileContentList {
    private String username;
    private String content;
    private int dpId;
    private String date;

    public ProfileContentList(String username, String content, int dpId, String date) {
        this.username = username;
        this.content = content;
        this.dpId = dpId;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDpId() {
        return dpId;
    }

    public void setDpId(int dpId) {
        this.dpId = dpId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
