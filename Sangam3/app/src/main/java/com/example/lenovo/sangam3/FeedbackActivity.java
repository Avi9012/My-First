package com.example.lenovo.sangam3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FeedbackActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle("Feedback");
        setSupportActionBar(tb);

        NavigationView navigationview = (NavigationView)findViewById(R.id.nav_view);
        if(MainActivity.loggedIn == 1) {
            Menu menu = navigationview.getMenu();
            MenuItem menuItem = menu.findItem(R.id.log_in);
            menuItem.setTitle("Log Out");
        }
        navigationview.setNavigationItemSelectedListener(this);


        dl = (DrawerLayout) findViewById(R.id.dl);
        ActionBarDrawerToggle ABT;
        ABT = new ActionBarDrawerToggle(this, dl, tb, R.string.open, R.string.close);

        dl.addDrawerListener(ABT);
        ABT.syncState();
    }

    public void send_mail(View v)
    {
        TextInputLayout name = (TextInputLayout) findViewById(R.id.name);
        TextInputLayout email = (TextInputLayout) findViewById(R.id.email);
        TextInputLayout subject = (TextInputLayout) findViewById(R.id.subject);
        TextInputLayout message = (TextInputLayout) findViewById(R.id.message);

        if(name.getEditText().getText().toString().equals(""))
            name.setError("required field");

        else if(email.getEditText().getText().toString().equals(""))
            email.setError("required field");

        else if(subject.getEditText().getText().toString().equals(""))
            subject.setError("required field");

        else if(message.getEditText().getText().toString().equals(""))
            message.setError("required field");

        else
        {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto: "));
            i.putExtra(Intent.EXTRA_EMAIL, new String[] {"Avigangwar9012@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, subject.getEditText().getText().toString());
            i.putExtra(Intent.EXTRA_TEXT, "dear Abhishek gangwar \n" + message.getEditText().getText().toString() + "\n regards, " + email.getEditText().getText().toString());

            try {
                startActivity(Intent.createChooser(i, "send mail"));
            } catch (android.content.ActivityNotFoundException ex)
            {
                Toast.makeText(this, "no mail found", Toast.LENGTH_SHORT).show();
            }
            catch (Exception ex)
            {
                Toast.makeText(this, "unexpected error occured" + ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId()) {
            case R.id.emergency:
                Intent intent4 = new Intent(this, Emergency_Activity.class);
                startActivity(intent4);
                break;
            case R.id.hotels:
                Intent intent1 = new Intent(this, HotelsActivity.class);
                startActivity(intent1);
                break;
            case R.id.res:
                Intent intent3 = new Intent(this, RestaurantActivity.class);
                startActivity(intent3);
                break;
            case R.id.tour:
                Intent intent2 = new Intent(this, TourActivity.class);
                startActivity(intent2);
                break;
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                Intent intent5 = new Intent(this, FeedbackActivity.class);
                startActivity(intent5);
                break;
            case R.id.log_in:
                if(MainActivity.loggedIn == 1)
                {
                    MainActivity.loggedIn = 0;
                    NavigationView navigationview = (NavigationView)findViewById(R.id.nav_view);
                    Menu menu = navigationview.getMenu();
                    MenuItem menuItem = menu.findItem(R.id.log_in);
                    menuItem.setTitle("Log In");
                }
                Intent intent6 = new Intent(this, AboutActivity.class);
                startActivity(intent6);
                finish();
                break;
        }
        dl.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
