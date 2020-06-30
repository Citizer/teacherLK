package com.example.androidstudentclientapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userData {
    //Класс для хранения пользовательских данных
    @SerializedName("username")
    @Expose
    private String username;
     @SerializedName("password")
    @Expose
    private String password;
     @SerializedName("accessToken")
     @Expose
     private String AcessToken;
     @SerializedName("platoonId")
     @Expose
     private Long platoonID;


    public userData(String username, String password, String acessToken) {
        this.username = username;
        this.password = password;
        this.AcessToken = acessToken;
    }

    public String getAcessToken() {
        return AcessToken;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAcessToken(String acessToken) {
        AcessToken = acessToken;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPlatoonID() {
        return platoonID;
    }
}
