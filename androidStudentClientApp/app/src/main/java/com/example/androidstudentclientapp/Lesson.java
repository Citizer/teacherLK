package com.example.androidstudentclientapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lesson {
    //Экземпляр класса занятие
    @SerializedName("theme")
    @Expose
    private String theme;

    @SerializedName("subject")
    @Expose
    private String subject;

    @SerializedName("teacher")
    @Expose
    private String teacher;

    @SerializedName("platoon")
    @Expose
    private String platoon;

    @SerializedName("classroom")
    @Expose
    private String classroom;

    @SerializedName("timeStart")
    @Expose
    private String timeStart;

    @SerializedName("timeEnd")
    @Expose
    private String timeEnd;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("teacherId")
    @Expose
    private Long teacherid;

    public Lesson() {}

    public String getClassroom() {
        return classroom;
    }

    public String getPlatoon() {
        return platoon;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTheme() {
        return theme;
    }

    public void setPlatoon(String platoon) {
        this.platoon = platoon;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }
    /* public void setFlagTeacher(Boolean flagTeacher) {
        this.flagTeacher = flagTeacher;
    }


    public Boolean getFlagTeacher() {
        return this.flagTeacher;
    }*/


}