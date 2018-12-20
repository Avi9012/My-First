package com.example.lenovo.sangam3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;


public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LogInActivity.this;

    private NestedScrollView nsv ;

    private TextInputLayout email, password;

    private TextInputEditText Email, Password;

    private AppCompatButton appCompatButtonlogin;

    private AppCompatTextView appTextViewLinkregister;

    private Validation valid;

    private dataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aactivity_login);
        //Toolbar tb = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(tb);

        initViews();
        initListners();
        initObjects();
    }

    private void initViews() {
        nsv = (NestedScrollView)findViewById(R.id.nestedscrollview);
        Email = (TextInputEditText)findViewById(R.id.edit_email);
        Password = (TextInputEditText)findViewById(R.id.edit_password);
        email = (TextInputLayout)findViewById(R.id.email);
        password = (TextInputLayout)findViewById(R.id.password);

        appCompatButtonlogin = (AppCompatButton)findViewById(R.id.login_button);
        appTextViewLinkregister = (AppCompatTextView)findViewById(R.id.textViewLinkRegister);
    }


    private void initListners() {
        appCompatButtonlogin.setOnClickListener(this);
        appTextViewLinkregister.setOnClickListener(this);
    }

    private void initObjects() {
        dbh = new dataBaseHelper(activity);
        valid = new Validation(activity);
    }




    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.login_button:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void verifyFromSQLite() {
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
        if(dbh.checkUser(Email.getText().toString().trim(), Password.getText().toString().trim()))
        {
            MainActivity.loggedIn = 1;
            /*
            NavigationView navigationview = (NavigationView)findViewById(R.id.nav_view);
            View headerView1 = navigationview.getHeaderView(0);
            View headerView2 = navigationview.getHeaderView(1);
            SQLiteDatabase db = this.getReadableDatabase();
            */
            Intent intent = new Intent(activity, AboutActivity.class);
            //intent.putExtra("EMAIL", Email.getText().toString().trim());
            emptyInputEditText();
            startActivity(intent);
            finish();
        }
        else
        {
            Snackbar.make(nsv, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        Email.setText(null);
        Password.setText(null);
    }
}
