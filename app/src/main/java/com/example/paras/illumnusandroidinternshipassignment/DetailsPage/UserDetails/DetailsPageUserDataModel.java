package com.example.paras.illumnusandroidinternshipassignment.DetailsPage.UserDetails;

import com.google.gson.annotations.SerializedName;

public class DetailsPageUserDataModel {

    @SerializedName("login")
    String userName;

    @SerializedName("avatar_url")
    String userImgUrl;

    @SerializedName("company")
    String userCompany;

    @SerializedName("email")
    String userEmail;

    @SerializedName("public_repos")
    String userRepositories;

    @SerializedName("followers")
    String userFollowers;

    @SerializedName("following")
    String userFollowing;

    @SerializedName("location")
    String userLocation;

    public DetailsPageUserDataModel(String userName, String userImgUrl, String userCompany, String userEmail, String userRepositories, String userFollowers, String userFollowing, String userLocation) {
        this.userName = userName;
        this.userImgUrl = userImgUrl;
        this.userCompany = userCompany;
        this.userEmail = userEmail;
        this.userRepositories = userRepositories;
        this.userFollowers = userFollowers;
        this.userFollowing = userFollowing;
        this.userLocation = userLocation;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserRepositories() {
        return userRepositories;
    }

    public String getUserFollowers() {
        return userFollowers;
    }

    public String getUserFollowing() {
        return userFollowing;
    }

    public String getUserLocation() {
        return userLocation;
    }
}
