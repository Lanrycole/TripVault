package com.example.tripvault.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripvault.R;
import com.example.tripvault.activityList;
import com.squareup.picasso.Picasso;

public class userProfile extends AppCompatActivity {

    private TextView welcomeinfo;
    Button goToPage;
	Button deleteBtn;
	ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        welcomeinfo = findViewById(R.id.welcomeInfos);
        goToPage= (Button) findViewById(R.id.go_to_page);
        deleteBtn= findViewById(R.id.delete_Profile);
		deleteBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoDrawer();
			}
		});
		imageView = findViewById(R.id.userProImage);

		Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        welcomeinfo.setText(userName);
        goToPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferToPage();
            }
        });


		Picasso.get().load(R.drawable.camelmin)
				.resize(500, 0)
				.centerInside()
				.error(R.drawable.camelmin)
				.into(imageView);


	}

    private void transferToPage(){
        Intent intent = new Intent(this, activityList.class);
        startActivity(intent);
    }

	public void gotoDrawer(){
		Intent intent = new Intent(this, userDrawer.class);
		startActivity(intent);
	}


}
