package com.example.esha.logup;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
    private String username;
    private String email;
    private String phone;
    private String url;
    private String location;
    private TextView tvUsername;
    private Button btnEmail;
    private Button btnPhone;
    private Button btnLocation;
    private Button btnUrl;
    private Button btnProfile;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        createView();
        getIntentValue();


    }

    public void createView(){
        tvUsername=findViewById(R.id.tv_username);
        btnEmail =findViewById(R.id.btn_email);
        btnPhone =findViewById(R.id.btn_phone);
        btnLocation =findViewById(R.id.btn_location);
        btnUrl =findViewById(R.id.btn_url);
        btnProfile=findViewById(R.id.btn_profile);

        btnEmail.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnUrl.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

    }

    public void getIntentValue(){
        Intent intent=getIntent();
        if(intent==null)
            return;
        username=intent.getStringExtra("username");
        email=intent.getStringExtra("email");
        phone=intent.getStringExtra("phone");
        location=intent.getStringExtra("location");
        url = intent.getStringExtra("url");

        tvUsername.setText(username);

    }

    public void placeCall(){
        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+phone));
        startActivity(callIntent);
    }

    public void openUrl(){
        try {
            Uri uri = Uri.parse("googlechrome://navigate?url=" + url);
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            // Chrome is probably not installed
            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(DashboardActivity.this);
            alertDialogBuilder.setTitle("Install");
            alertDialogBuilder.setMessage("Do you want to install chrome in your device?");
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final String appPackageName = BuildConfig.APPLICATION_ID; // package name of the app
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            });
           alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   Toast.makeText(DashboardActivity.this, "Our app runs better on Google Chrome", Toast.LENGTH_SHORT).show();
               }
           });
           alertDialogBuilder.show();
        }
    }

    public void openMap(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        }, 1000);
    }

    public void sendEmail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DashboardActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void openProfile(){
        Intent profileIntent=new Intent(DashboardActivity.this, MainProfileActivity.class);
        startActivity(profileIntent);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_phone:
                placeCall();
                break;
            case R.id.btn_url:
                openUrl();
                break;
            case R.id.btn_location:
                openMap();
                break;
            case R.id.btn_email:
                sendEmail();
                break;
            case R.id.btn_profile:
                openProfile();
                break;
        }
    }

    @Override
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
