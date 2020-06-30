package com.example.androidstudentclientapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("tokenType")
    @Expose
    private String tokenType;
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("accessToken")
    @Expose
    private String acessToken;

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("fio")
    @Expose
    private String fio;

    @SerializedName("personnelNumber")
    @Expose
    private Integer personnelNumber;

    @SerializedName("message")
    @Expose
    private String message;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setAcessToken(String acessToken) {
        this.acessToken = acessToken;
    }

    public String getAcessToken() {
        return acessToken;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPersonnelNumber(Integer personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public Integer getPersonnelNumber() {
        return personnelNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}