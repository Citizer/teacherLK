package com.example.androidstudentclientapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class student {
    //Экземпляр хранит информацию об оценке 1 студента за 1 занятие
    @SerializedName("studentId")
    @Expose
    private long studentId;

    @SerializedName("mark")
    @Expose
    private int mark;

    @SerializedName("lessonId")
    @Expose
    private long lessonId;

    @SerializedName("studentName")
    @Expose
    private String studentName;

    public student() {

    }

    public int getMark() {
        return mark;
    }

    public long getLessonId() {
        return lessonId;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
