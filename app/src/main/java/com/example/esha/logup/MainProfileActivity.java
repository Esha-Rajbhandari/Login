package com.example.esha.logup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainProfileActivity extends AppCompatActivity {
    private RecyclerView rvProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvProfile=findViewById(R.id.rv_profile);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainProfileActivity.this);
        rvProfile.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvProfile.setLayoutManager(linearLayoutManager);

        rvProfile.setAdapter(new ProfileContentListingAdapter(getProfileContent()));
    }

    public ArrayList<ProfileContentList> getProfileContent(){
        ArrayList<ProfileContentList> profileList=new ArrayList<>();
        profileList.add(new ProfileContentList("Wasps","Content goes here",R.drawable.dp,"Posted On: 7/18/2018"));
        profileList.add(new ProfileContentList("Wasps","Content1 goes here",R.drawable.dp,"Posted On: 7/20/2017"));
        profileList.add(new ProfileContentList("Wasps","Content2 goes here",R.drawable.dp,"Posted On: 5/1/2017"));
        profileList.add(new ProfileContentList("Wasps","Content3 goes here",R.drawable.dp,"Posted On: 1/18/2018"));
        profileList.add(new ProfileContentList("Wasps","Content4 goes here",R.drawable.dp,"Posted On: 7/18/2015"));
        profileList.add(new ProfileContentList("Wasps","Content5 goes here",R.drawable.dp,"Posted On: 12/12/2012"));
        return profileList;
    }
}

