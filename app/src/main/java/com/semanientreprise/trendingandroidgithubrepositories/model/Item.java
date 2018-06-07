package com.semanientreprise.trendingandroidgithubrepositories.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item{
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("created_at")
	@Expose
	private String createdAt;
	@SerializedName("homepage")
	@Expose
	private String homePage;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("license")
    @Expose
    private License license;
}