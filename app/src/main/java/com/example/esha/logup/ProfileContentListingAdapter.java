package com.example.esha.logup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileContentListingAdapter extends RecyclerView.Adapter<ProfileContentListingAdapter.ProfileViewHolder>{
    private ArrayList<ProfileContentList> profileList;

    public ProfileContentListingAdapter(ArrayList<ProfileContentList> profileList) {
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profile,parent,false);
        ProfileViewHolder pvh=new ProfileViewHolder(layoutView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, int i) {
        profileViewHolder.tvName.setText(profileList.get(i).getUsername());
        profileViewHolder.tvContent.setText(profileList.get(i).getContent());
        profileViewHolder.ivDp.setImageResource(profileList.get(i).getDpId());
        profileViewHolder.tvDate.setText(profileList.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivDp;
        private TextView tvName;
        private TextView tvContent;
        private TextView tvDate;

        public ProfileViewHolder(View itemView){
            super(itemView);
            ivDp=itemView.findViewById(R.id.iv_dp);
            tvName=itemView.findViewById(R.id.tv_username);
            tvContent=itemView.findViewById(R.id.tv_content);
            tvDate=itemView.findViewById(R.id.tv_date);
        }
    }
}
