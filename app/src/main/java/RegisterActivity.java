package com.example.esha.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.esha.login.utils.PasswordValidate;
import com.example.esha.login.utils.ShowToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Incoorporates the layout containing the registeration form. All the field values
 * are accessed and validated. Regular expression is used for password validation.
 * On cancel, the form field is reset and on register, email and password is passed to the main activity
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout textFirstName;
    private TextInputLayout textLastName;
    private TextInputLayout textUsername;
    private TextInputLayout textPassword;
    private TextInputLayout textPhone;
    private TextInputLayout textLocation;
    private TextInputLayout textUrl;
    private TextInputLayout textEmail;
    private Button btnRegister;
    private Button btnCancel;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phone;
    private String location;
    private String url;
    private String email;
    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        createView();
    }

    public void createView(){
        textFirstName=findViewById(R.id.text_first_name);
        textLastName=findViewById(R.id.text_last_name);
        textUsername=findViewById(R.id.text_username);
        textPassword=findViewById(R.id.text_password);
        btnRegister=findViewById(R.id.btn_register);
        btnCancel=findViewById(R.id.btn_cancel_reg);
        textPhone=findViewById(R.id.text_phone);
        textLocation=findViewById(R.id.text_location);
        textUrl=findViewById(R.id.text_url);
        textEmail=findViewById(R.id.text_email);
        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void checkCriteria(){
        firstName=textFirstName.getEditText().getText().toString().trim();
        lastName=textLastName.getEditText().getText().toString().trim();
        username=textUsername.getEditText().getText().toString().trim();
        password=textPassword.getEditText().getText().toString().trim();
        phone=textPhone.getEditText().getText().toString().trim();
        location=textLocation.getEditText().getText().toString().trim();
        url=textUrl.getEditText().getText().toString().trim();
        email=textEmail.getEditText().getText().toString().trim();
        if(TextUtils.isEmpty(firstName)||TextUtils.isEmpty(lastName)||TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(location)||TextUtils.isEmpty(url)||TextUtils.isEmpty(email))
            ShowToast.showToast(this, "Field cannot be empty", false);
        else if(username.length()>15) {
            ShowToast.showToast(this, "Username must be less than or equal to 15", false);
        }
        else{
            if(PasswordValidate.isValidPassword(password, PASSWORD_PATTERN)){
                Intent registerIntent=new Intent(RegisterActivity.this, MainActivity.class);
                registerIntent.putExtra("username",username);
                registerIntent.putExtra("password",password);
                registerIntent.putExtra("phone",phone);
                registerIntent.putExtra("location",location);
                registerIntent.putExtra("url",url);
                registerIntent.putExtra("email",email);
                startActivity(registerIntent);
                finish();
            }
            else{
                ShowToast.showToast(this, "Password must contain capital letter, $,small letter and a number", false);
            }
        }
    }



    public void emptyField(){
        textFirstName.getEditText().setText("");
        textLastName.getEditText().setText("");
        textLocation.getEditText().setText("");
        textPhone.getEditText().setText("");
        textEmail.getEditText().setText("");
        textUsername.getEditText().setText("");
        textPassword.getEditText().setText("");
        textUsername.getEditText().setText("");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_register:
                checkCriteria();
                break;
            case R.id.btn_cancel_reg:
                emptyField();
                break;
        }
    }
}
