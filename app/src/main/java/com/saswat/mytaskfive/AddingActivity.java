package com.saswat.mytaskfive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddingActivity extends AppCompatActivity {
    private EditText et_user_name , et_user_email , et_user_phone;
    private Button add_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
         et_user_name= findViewById(R.id.et_user_name_login);
        et_user_email = findViewById(R.id.et_user_email);
        et_user_phone = findViewById(R.id.et_user_phone);
        add_btn = findViewById(R.id.button_add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(AddingActivity.this);
                dbHelper.addDataToDatabase(et_user_name.getText().toString().trim(),
                        et_user_email.getText().toString().trim(),
                        Long.parseLong(et_user_phone.getText().toString().trim()));
            }
        });
    }
}