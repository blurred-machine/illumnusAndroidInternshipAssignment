package com.example.paras.illumnusandroidinternshipassignment.EntryPage;

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
import com.example.paras.illumnusandroidinternshipassignment.DetailsPage.DetailsPageActivity;
import com.example.paras.illumnusandroidinternshipassignment.R;

public class EntryPageViewAdapter extends RecyclerView.Adapter<EntryPageViewAdapter.MyListViewHolder>{

    public EntryPageUserDataModel[] data;
    public Context context;

    public EntryPageViewAdapter(Context context, EntryPageUserDataModel[] data){
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.entry_page_view_layout, viewGroup, false);

        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyListViewHolder myListViewHolder, int i) {
        final EntryPageUserDataModel user = data[i];
        myListViewHolder.userNameTv.setText(user.getUserName());
        Glide.with(myListViewHolder.userImg.getContext()).load(user.getUserImageUrl()).into(myListViewHolder.userImg);

        myListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsPageActivity.class);
                intent.putExtra("USER_LOGIN_ID", user.getUserName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder{

        ImageView userImg;
        TextView userNameTv;
        TextView moreInfoTv;

        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);

            userImg = (ImageView)itemView.findViewById(R.id.userImage);
            userNameTv = (TextView)itemView.findViewById(R.id.userName);
            moreInfoTv = (TextView) itemView.findViewById(R.id.moreInfoTv);

        }
    }

}
