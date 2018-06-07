package com.semanientreprise.trendingandroidgithubrepositories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.semanientreprise.trendingandroidgithubrepositories.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GeneralKolo on 2018/06/07.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<Item> repos;
    private Context context;
    customItemClickListener listener;

    public RepositoryAdapter(List<Item> repos, Context context, customItemClickListener listener) {
        this.repos = repos;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.android_repo_single_view, null);
        final RepositoryViewHolder RepositoryViewHolder = new RepositoryViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, RepositoryViewHolder.getAdapterPosition());
            }
        });

        return RepositoryViewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.repoName.setText(repos.get(position).getName());
        holder.repoDescription.setText(repos.get(position).getDescription());
        String url = repos.get(position).getOwner().getAvatarUrl();
        if(!url.isEmpty()){
            Picasso.with(context).load(url)
                    .resize(200,200)
                    .into(holder.repoImage);
        }
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repo_image) ImageView repoImage;
        @BindView(R.id.repo_name) TextView repoName;
        @BindView(R.id.repo_description) TextView repoDescription;

        private RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
