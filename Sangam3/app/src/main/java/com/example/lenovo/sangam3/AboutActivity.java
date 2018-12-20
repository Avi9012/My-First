package com.example.lenovo.sangam3;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int REQUEST_CALL = 1;
    private DrawerLayout dl;
    // button b is for SOS button
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {                                                     //on create method creates activity
        super.onCreate(savedInstanceState);                                                                  // savedInstanceState holds the state of activity
        setContentView(R.layout.activity_about);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle("Home");
        setSupportActionBar(tb);                                                                            //setsupportactionBar method sets toolbar as actionbar

        NavigationView navigationview = (NavigationView)findViewById(R.id.nav_view);
        Menu menu = navigationview.getMenu();
        MenuItem menuItem = menu.findItem(R.id.log_in);
        View headerView1 = navigationview.getHeaderView(0);
        View headerView2 = navigationview.getHeaderView(1);
        if(MainActivity.loggedIn == 1)
        {
            menuItem.setTitle("Log Out");
        }
        navigationview.setNavigationItemSelectedListener(this);
        b = (Button) findViewById(R.id.button);

        dl = (DrawerLayout) findViewById(R.id.dl);
        ActionBarDrawerToggle ABT;
        ABT = new ActionBarDrawerToggle(this, dl, tb, R.string.open, R.string.close);

        dl.addDrawerListener(ABT);
        ABT.syncState();
        //method for click event
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(AboutActivity.this,  Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AboutActivity.this, new String [] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel: 9936707796"));
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId()) {
            case R.id.hotels:
                if(MainActivity.loggedIn == 1) {
                    Intent intent = new Intent(this, HotelsActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), (String)("Please Log In First.."), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.res:
                if(MainActivity.loggedIn == 1) {
                    Intent intent1 = new Intent(this, RestaurantActivity.class);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), (String)("Please Log In First.."), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tour:
                if(MainActivity.loggedIn == 1) {
                    Intent intent2 = new Intent(this, TourActivity.class);
                    startActivity(intent2);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), (String)("Please Log In First.."), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.about:
                if(MainActivity.loggedIn == 1) {
                    Intent intent3 = new Intent(this, AboutActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent3);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), (String)("Please Log In First.."), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.emergency:
                if(MainActivity.loggedIn == 1) {
                    Intent intent4 = new Intent(this, Emergency_Activity.class);
                    startActivity(intent4);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), (String)("Please Log In First.."), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.feedback:
                if(MainActivity.loggedIn == 1) {
                    Intent intent5 = new Intent(this, FeedbackActivity.class);
                    startActivity(intent5);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), (String)("Please Log In First.."), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.log_in:
                if(MainActivity.loggedIn == 0) {
                    Intent intent6 = new Intent(this, LogInActivity.class);
                    startActivity(intent6);
                }
                else
                {
                    MainActivity.loggedIn = 0;
                    NavigationView navigationview = (NavigationView)findViewById(R.id.nav_view);
                    Menu menu = navigationview.getMenu();
                    MenuItem menuItem = menu.findItem(R.id.log_in);
                    menuItem.setTitle("Log In");
                    Intent intent6 = new Intent(this, AboutActivity.class);
                    startActivity(intent6);
                }
                finish();
                break;
        }
        dl.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
        /*
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        */
    }
}
