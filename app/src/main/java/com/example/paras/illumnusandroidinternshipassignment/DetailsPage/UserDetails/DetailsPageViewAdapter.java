package com.example.paras.illumnusandroidinternshipassignment.DetailsPage.UserDetails;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.paras.illumnusandroidinternshipassignment.FollowersPage.FollowersPageActivity;
import com.example.paras.illumnusandroidinternshipassignment.R;

public class DetailsPageViewAdapter extends RecyclerView.Adapter<DetailsPageViewAdapter.MyListViewHolder> implements View.OnClickListener {


    public Context context;
    private String userName;
    private String userImageUrl;
    private String userCompany;
    private String userEmail;
    private String userRepository;
    private String userFollowers;
    private String userFollowing;
    private String userLocation;


    public DetailsPageViewAdapter(Context context, String userName, String userImageUrl, String userCompany, String userEmail, String userRepository, String userFollowers, String userFollowing, String userLocation) {
        this.context = context;
        this.userName = userName;
        this.userImageUrl = userImageUrl;
        this.userCompany = userCompany;
        this.userEmail = userEmail;
        this.userRepository = userRepository;
        this.userFollowers = userFollowers;
        this.userFollowing = userFollowing;
        this.userLocation = userLocation;
    }



    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.details_page_view_layout, viewGroup, false);
        return new DetailsPageViewAdapter.MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder myListViewHolder, int i) {

        myListViewHolder.userNameTv.setText("Name: " + userName);
        myListViewHolder.userCompanyTv.setText("Company: " + userCompany);
        myListViewHolder.userEmailTv.setText("Email: " + userEmail);
        myListViewHolder.userRepositoriesTv.setText("Repository: " + userRepository);
        myListViewHolder.userFollowingTv.setText("Following: " + userFollowing);
        myListViewHolder.userLocationTv.setText("Location: " + userLocation);
        myListViewHolder.userFollowersBtn.setText("Followers: "+userFollowers+" (see followers)");

        myListViewHolder.userFollowersBtn.setOnClickListener(this);

        Glide.with(myListViewHolder.userImg.getContext()).load(userImageUrl).into(myListViewHolder.userImg);
        }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.userFollowersBtn){
            Intent intent = new Intent(context, FollowersPageActivity.class);
            intent.putExtra("USER_LOGIN_ID2", userName);
            context.startActivity(intent);
        }
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        ImageView userImg;
        TextView userNameTv;
        TextView userCompanyTv;
        TextView userEmailTv;
        TextView userRepositoriesTv;
        TextView userFollowingTv;
        TextView userLocationTv;
        TextView userFollowersBtn;

        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);

            userImg = (ImageView) itemView.findViewById(R.id.userDetailImage);
            userNameTv = (TextView) itemView.findViewById(R.id.userName);
            userCompanyTv = (TextView) itemView.findViewById(R.id.userCompany);
            userEmailTv = (TextView) itemView.findViewById(R.id.userEmail);
            userRepositoriesTv = (TextView) itemView.findViewById(R.id.userRepositories);
            userFollowersBtn = (TextView) itemView.findViewById(R.id.userFollowersBtn);
            userFollowingTv = (TextView) itemView.findViewById(R.id.userFollowing);
            userLocationTv = (TextView) itemView.findViewById(R.id.userLocation);
        }
    }

}
