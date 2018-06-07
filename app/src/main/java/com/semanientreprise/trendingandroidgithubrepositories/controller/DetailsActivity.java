package com.semanientreprise.trendingandroidgithubrepositories.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.semanientreprise.trendingandroidgithubrepositories.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_image)
    ImageView detailsImage;
    @BindView(R.id.details_name)
    TextView detailsName;
    @BindView(R.id.details_description)
    TextView detailsDescription;
    @BindView(R.id.details_createdAt)
    TextView detailsCreatedAt;
    @BindView(R.id.details_homePage)
    TextView detailsHomePage;
    @BindView(R.id.details_language)
    TextView detailsLanguage;
    @BindView(R.id.details_license_name)
    TextView detailsLicenseName;

    private String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        getSupportActionBar().setTitle(displayName);
    }

    private void initViews() {
        Intent intent = getIntent();
        Picasso.with(this).load(intent.getStringExtra("RepoImageUrl")).into(detailsImage);
        displayName = intent.getStringExtra("RepoName");
        detailsName.setText(displayName);
        detailsCreatedAt.setText(intent.getStringExtra("RepoCreatedAt"));
        detailsDescription.setText(intent.getStringExtra("RepoDescription"));
        detailsHomePage.setText(intent.getStringExtra("RepoHomePage"));
        detailsLanguage.setText(intent.getStringExtra("RepoLanguage"));
        detailsLicenseName.setText(intent.getStringExtra("RepoLicense"));

    }
}
