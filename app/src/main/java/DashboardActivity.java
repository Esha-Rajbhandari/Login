package com.example.esha.login;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.esha.login.utils.ShowToast;

/**
 * Only initiates if the email and password is correct.
 * Based on the number of buttons available, performs different actions.
 * The user can place the call, open websites, open google map, send the email via approriate media
 * and view the profile set using recycle view
 */

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
    static final int LOCATION=1;
    static final int CALL=2;
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
            HelperAcitvity.createDialog(DashboardActivity.this,"Install","Do you want to install chrome in your device?","Yes","No");
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
        } catch (ActivityNotFoundException ex) {
            ShowToast.showToast(DashboardActivity.this, "There are no email clients installed.", false);
        }
    }

    public void openProfile(){
        Intent profileIntent=new Intent(DashboardActivity.this, MainProfileActivity.class);
        startActivity(profileIntent);
    }

    public void getPermission(String permission, Integer requestCode){
        if (ContextCompat.checkSelfPermission(DashboardActivity.this,permission)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(DashboardActivity.this,permission)){
                ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{permission}, requestCode);
            }
            else{
                ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{permission}, requestCode);
            }
        }
        else{
            ShowToast.showToast(this, "" + permission + " is already granted.", false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0])== PackageManager.PERMISSION_GRANTED){
            switch(requestCode){
                case 1:
                    openMap();
                    break;
                case 2:
                    placeCall();
                    break;
            }
        }
        else{
            ShowToast.showToast(this, "Permission denied", false);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_phone:
                getPermission(Manifest.permission.CALL_PHONE,CALL);
                //placeCall();
                break;
            case R.id.btn_url:
                openUrl();
                break;
            case R.id.btn_location:
                getPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);
                //openMap();
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
        ShowToast.showToast(this, "Please click BACK again to exit", true);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
