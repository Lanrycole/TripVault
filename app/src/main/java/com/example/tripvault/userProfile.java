package com.example.tripvault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class userProfile extends AppCompatActivity {

    private TextView welcomeinfo;
    Button goToPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        welcomeinfo = findViewById(R.id.welcomeInfos);
        goToPage= (Button) findViewById(R.id.go_to_page);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        welcomeinfo.setText(userName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        goToPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferToPage();
            }
        });

    }

    private void transferToPage(){
        Intent intent = new Intent(this, activityList.class);
        startActivity(intent);

    }
}
