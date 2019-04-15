package com.example.paras.illumnusandroidinternshipassignment.DetailsPage.RepositoriesDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paras.illumnusandroidinternshipassignment.R;

public class DetailsPageRepoAdapter extends RecyclerView.Adapter<DetailsPageRepoAdapter.MyListViewHolder> {

    public Context context;
    private DetailsPageRepoDataModel[] userRepos;

    public DetailsPageRepoAdapter(Context context, DetailsPageRepoDataModel[] userRepos) {
        this.context = context;
        this.userRepos = userRepos;
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.details_page_repo_layout, viewGroup, false);
        return new DetailsPageRepoAdapter.MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsPageRepoAdapter.MyListViewHolder myListViewHolder, int i) {
        final DetailsPageRepoDataModel oneRepo = userRepos[i];
        myListViewHolder.userRepoName.setText("Repository: " + oneRepo.getUserRepoName());
        myListViewHolder.userRepoDesc.setText("Description: " + oneRepo.getUserRepoDescription());
    }

    @Override
    public int getItemCount() {
        return userRepos.length;
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        TextView userRepoName;
        TextView userRepoDesc;


        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);

            userRepoName = (TextView) itemView.findViewById(R.id.userRepoNameTv);
            userRepoDesc = (TextView) itemView.findViewById(R.id.userRepoDescTv);
        }
    }

}
