package com.example.lenovo.sangam3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MIN_LENGTH = 6;
    private final AppCompatActivity activity = RegisterActivity.this;

        private NestedScrollView nsv ;

        private TextInputLayout name, email, password, confirm_pass;

        private TextInputEditText Name, Email, Password, Confirm_Pass;

        private AppCompatButton appCompatButtonregister;

        private AppCompatTextView appCompatTextLogin;

        private Validation valid;

        private dataBaseHelper dbh;

        private User user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            //Toolbar tb = (Toolbar)findViewById(R.id.toolbar);
            //setSupportActionBar(tb);

            initViews();
            initListners();
            initObjects();
        }

    private void initViews() {
        nsv = (NestedScrollView)findViewById(R.id.NSV);

        Name = (TextInputEditText)findViewById(R.id.Text_Name);
        Email = (TextInputEditText)findViewById(R.id.Text_Email);
        Password = (TextInputEditText)findViewById(R.id.Text_Pass);
        Confirm_Pass = (TextInputEditText)findViewById(R.id.Text_Confirm_Pass);

        name = (TextInputLayout)findViewById(R.id.textName);
        email = (TextInputLayout)findViewById(R.id.textEmail);
        password = (TextInputLayout)findViewById(R.id.textPass);
        confirm_pass = (TextInputLayout)findViewById(R.id.textConfirmPass);

        appCompatButtonregister = (AppCompatButton)findViewById(R.id.register);
        appCompatTextLogin = (AppCompatTextView)findViewById(R.id.LoginLink);
    }

    private void initListners() {
        appCompatButtonregister.setOnClickListener(this);
        appCompatTextLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.register:
                String pass = Password.getText().toString().trim();
                if(pass.length() > MIN_LENGTH) {
                    postDataToSQLite();
                }
                else
                {
                    Snackbar.make(nsv, getString(R.string.short_pass), Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.LoginLink:
                finish();
                break;
        }

    }

    private void postDataToSQLite() {
        if(!valid.isInputEditTextFilled(Name, name, getString(R.string.error_message_name)))
        {
            return;
        }
        if(!valid.isInputEditTextFilled(Email, email, getString(R.string.error_message_email)))
        {
            return;
        }
        if(!valid.isInputEditTextEmail(Email, email, getString(R.string.error_message_email)))
        {
            return;
        }
        if(!valid.isInputEditTextFilled(Password, password, getString(R.string.error_message_password)))
        {
            return;
        }
        if(!valid.isInputEditTextMatches(Password, Confirm_Pass, confirm_pass, getString(R.string.error_pass_match)))
        {
            return;
        }
        if(!dbh.checkUser(Email.getText().toString().trim()))
        {
            user.setName(Name.getText().toString().trim());
            user.setEmail(Email.getText().toString().trim());
            user.setPassword(Password.getText().toString().trim());
            dbh.addUser(user);

            Snackbar.make(nsv, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();

        }
        else
        {
            Snackbar.make(nsv, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
            Name.setText(null);
            Email.setText(null);
            Password.setText(null);
            Confirm_Pass.setText(null);
    }

    private void initObjects() {
        dbh = new dataBaseHelper(activity);
        valid = new Validation(activity);
        user = new User();
    }

}
