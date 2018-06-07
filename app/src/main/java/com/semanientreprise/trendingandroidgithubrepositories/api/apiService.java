package com.semanientreprise.trendingandroidgithubrepositories.api;

import com.semanientreprise.trendingandroidgithubrepositories.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by GeneralKolo on 2018/06/06.
 */

public interface apiService {

    //Retrofit For getting GitHub Items
    @GET("search/repositories?q=android&sort=stars&order=desc")
    Call<ItemResponse> getRepositories();

}