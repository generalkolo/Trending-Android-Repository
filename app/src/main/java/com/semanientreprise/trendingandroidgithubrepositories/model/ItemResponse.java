package com.semanientreprise.trendingandroidgithubrepositories.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponse {
    @SerializedName("items")
    @Expose
    private List<Item> items;
}
