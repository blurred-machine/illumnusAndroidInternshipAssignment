package com.example.paras.illumnusandroidinternshipassignment.FollowersPage;

import com.google.gson.annotations.SerializedName;

public class FollowersPageDataModel {

    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String userImageUrl;


    public FollowersPageDataModel(String userName, String userImageUrl) {
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
