package com.saswat.mytaskfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText user_name , user_password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name = findViewById(R.id.et_user_name_login);
        user_password = findViewById(R.id.et_user_password_login);

        preferences = getApplicationContext().getSharedPreferences("INFO_APP", MODE_PRIVATE);
        editor = preferences.edit();

        boolean isLoggedin = preferences.getBoolean("ISLOGGEDIN", false);

        if (isLoggedin) {
            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }

    public void doLogInOperation(View view) {
        String email = user_name.getText().toString();
        String pasword = user_password.getText().toString();


        editor.putString("EMAIL", email);
        editor.putString("PASSWORD", pasword);
        editor.putBoolean("ISLOGGEDIN", true);
        editor.apply();
        Intent loginIntent = new Intent(LoginActivity.this,
                MainActivity.class);
        startActivity(loginIntent);
        finish();
    }
}