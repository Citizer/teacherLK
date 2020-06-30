package com.example.androidstudentclientapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class teacher {
    //Экземпляр класса хранит информацию об 1 преподавателе
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("fio")
    @Expose
    private String fio;

    @SerializedName("personnelNumber")
    @Expose
    private int personnelNumber;

    @SerializedName("user")
    @Expose
    private int user;

    public teacher() {

    }
    public void setName(String fio) {
        this.fio = fio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        //функция получения инициалов из ФИО
        String[] substr;
        String delimeter = " ";
        substr = fio.split(delimeter);
        char im = substr[1].charAt(0);
        char ot = substr[2].charAt(0);
        return substr[0] + " " + im + ". " + ot + ".";
    }
}
