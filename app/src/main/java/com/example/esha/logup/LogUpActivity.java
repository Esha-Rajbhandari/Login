package com.example.esha.logup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LogUpActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgLogo;
    private EditText edtEmail;
    private EditText edtpassword;
    private Button btnSignIn;
    private Button btnRegister;

    private String username;
    private String password;
    private String location;
    private String url;
    private String phone;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);
        imgLogo=findViewById(R.id.img_logo);
        imgLogo.setImageResource(R.drawable.header);

        createView();
        getValue();
    }

    public void createView(){
        edtEmail=findViewById(R.id.edt_email);
        edtpassword=findViewById(R.id.edt_password);

        btnSignIn=findViewById(R.id.btn_login);
        btnSignIn.setOnClickListener(this);

        btnRegister=findViewById(R.id.btn_logup);
        btnRegister.setOnClickListener(this);
    }

    public void getValue(){
        Intent intent=getIntent();
        if(intent==null)
            return;
        username=intent.getStringExtra("username");
        password=intent.getStringExtra("password");
        email=intent.getStringExtra("email");
        url=intent.getStringExtra("url");
        phone=intent.getStringExtra("phone");
        location=intent.getStringExtra("location");

        edtEmail.setText(email);
        edtpassword.setText(password);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_logup:
                Intent intent=new Intent(LogUpActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                if(edtEmail.getText().toString().trim().equals(email)&&edtpassword.getText().toString().trim().equals(password))
                {
                    Intent loginIntent=new Intent(LogUpActivity.this, DashboardActivity.class);
                    loginIntent.putExtra("username",username);
                    loginIntent.putExtra("email",email);
                    loginIntent.putExtra("location",location);
                    loginIntent.putExtra("phone",phone);
                    loginIntent.putExtra("url",url);
                    startActivity(loginIntent);
                }
                break;
        }
    }
}
