package com.example.paras.illumnusandroidinternshipassignment.EntryPage;

import com.google.gson.annotations.SerializedName;

public class EntryPageUserDataModel {

    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String userImageUrl;


    public EntryPageUserDataModel(String userName, String userImageUrl) {
        this.userName = userName;
        this.userImageUrl = userImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }
}
