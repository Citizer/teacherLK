package com.example.androidstudentclientapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class notification {

    //Экземпляр класса хранит информации об одном уведомлении
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("platoonId")
    @Expose
    private Long platoonId;

    @SerializedName("platoonName")
    @Expose
    private String platoonName;
    public notification() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPlatoonId() {
        return platoonId;
    }

    public void setPlatoonId(Long platoonId) {
        this.platoonId = platoonId;
    }

    public String getPlatoonName() {
        return platoonName;
    }

    public void setPlatoonName(String platoonName) {
        this.platoonName = platoonName;
    }
}
