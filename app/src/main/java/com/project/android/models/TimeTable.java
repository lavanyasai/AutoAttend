package com.project.android.models;

public class TimeTable {

    String time;
    String subject;

    public TimeTable(String time, String subject) {
        this.time = time;
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
