package com.semanientreprise.trendingandroidgithubrepositories.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.semanientreprise.trendingandroidgithubrepositories.R;
import com.semanientreprise.trendingandroidgithubrepositories.RepositoryAdapter;
import com.semanientreprise.trendingandroidgithubrepositories.api.apiClient;
import com.semanientreprise.trendingandroidgithubrepositories.api.apiService;
import com.semanientreprise.trendingandroidgithubrepositories.customItemClickListener;
import com.semanientreprise.trendingandroidgithubrepositories.model.Item;
import com.semanientreprise.trendingandroidgithubrepositories.model.ItemResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.android_repo_recView)
    RecyclerView androidRepoRecView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showProgressDialog();
        getRepositories();

        androidRepoRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        androidRepoRecView.setHasFixedSize(true);
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Repositories...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void getRepositories() {
        try{
            apiClient client = new apiClient();
            apiService apiService = client.getClient().create(apiService.class);

            Call<ItemResponse> call = apiService.getRepositories();

            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    if (response.code() == 200 && response.isSuccessful()) {
                        final List<Item> repositories = response.body().getItems();
                        androidRepoRecView.setAdapter(new RepositoryAdapter(repositories, MainActivity.this, new customItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                sendDetailsToNextActivity(repositories,position);
                            }
                        }));
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    showToast("Please ensure you have a working internet connection");
                    progressDialog.dismiss();
                }
            });
        }catch (Exception e){
        }
    }

    private void sendDetailsToNextActivity(List<Item> repositories, int position) {
        Item clickedItem = repositories.get(position);

        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);

        intent.putExtra("RepoImageUrl",clickedItem.getOwner().getAvatarUrl());
        intent.putExtra("RepoName",clickedItem.getName());
        intent.putExtra("RepoDescription",clickedItem.getDescription());
        intent.putExtra("RepoCreatedAt",clickedItem.getCreatedAt());

        if(!(clickedItem.getLanguage() == null))
            intent.putExtra("RepoLanguage",clickedItem.getLanguage());
        else
            intent.putExtra("RepoLanguage","N/A");

        if (clickedItem.getHomePage() == null || clickedItem.getHomePage().isEmpty())
            intent.putExtra("RepoHomePage","N/A");
        else
            intent.putExtra("RepoHomePage",clickedItem.getHomePage());

        if(!(clickedItem.getLicense() == null))
            intent.putExtra("RepoLicense",clickedItem.getLicense().getName());
        else
            intent.putExtra("RepoLicense","N/A");

        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}