package com.saswat.mytaskfive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    ArrayList<String> user_id;
    ArrayList<String> user_name;
    ArrayList<String> user_email;
    ArrayList<String> user_phone;
    CustomAdaptor customAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tl_home_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.dropdown_menu);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout
                , toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.rc_view_info);
        dbHelper = new DatabaseHelper(MainActivity.this);
        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_email = new ArrayList<>();
        user_phone = new ArrayList<>();

        displayData();

        customAdaptor = new CustomAdaptor(MainActivity.this, this, user_id, user_name, user_email, user_phone);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    public void displayData() {
        Cursor cursor = dbHelper.retriveDataToDatabase();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "ERROR!!!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_email.add(cursor.getString(2));
                user_phone.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.add_person:
                startActivity(new Intent(MainActivity.this, AddingActivity.class));
                recreate();
                break;
            case R.id.sign_out:
                SharedPreferences prefManager = getApplicationContext().getSharedPreferences("INFO_APP", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefManager.edit();


                editor.putBoolean("ISLOGGEDIN", false);
                editor.apply();

                startActivity(new Intent(MainActivity.this,
                        LoginActivity.class));
                finish();
                break;
        }
        return false;

    }


}