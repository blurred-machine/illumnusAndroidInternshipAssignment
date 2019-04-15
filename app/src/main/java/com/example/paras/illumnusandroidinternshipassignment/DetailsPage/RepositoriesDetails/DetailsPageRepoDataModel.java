package com.example.paras.illumnusandroidinternshipassignment.DetailsPage.RepositoriesDetails;

import com.google.gson.annotations.SerializedName;

public class DetailsPageRepoDataModel {

    @SerializedName("name")
    String userRepoName;

    @SerializedName("description")
    String userRepoDescription;

    public DetailsPageRepoDataModel(String userRepoName, String userRepoDescription) {
        this.userRepoName = userRepoName;
        this.userRepoDescription = userRepoDescription;
    }

    public String getUserRepoName() {
        return userRepoName;
    }

    public String getUserRepoDescription() {
        return userRepoDescription;
    }
}
