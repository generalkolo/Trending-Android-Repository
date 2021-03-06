package com.semanientreprise.trendingandroidgithubrepositories.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Owner{
    @SerializedName("avatar_url")
    @Expose
	private String avatarUrl;
}