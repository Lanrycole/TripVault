package com.example.tripvault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class userProfile extends AppCompatActivity {

    private TextView welcomeinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        welcomeinfo = findViewById(R.id.welcomeInfos);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        welcomeinfo.setText(userName);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
