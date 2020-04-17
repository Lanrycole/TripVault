package com.example.tripvault;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.tripvault.UI.createAcc;
import com.example.tripvault.UI.userDrawer;
import com.example.tripvault.data.Contact;
import com.example.tripvault.data.DatabaseHandler;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
	CardView cardView, brandName;
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button logIn = findViewById(R.id.login);
		Button createAcc = findViewById(R.id.createAccount);
		cardView = findViewById(R.id.cardView);
		imageView = findViewById(R.id.homedisplay);

		anim_Slide_Up();
//		anim_Slide_down();
		createAcc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				goToacc();
			}
		});
		logIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					goToUserPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Picasso.get().load(R.drawable.worldtravel)
				.resize(1400, 0)
				.centerCrop()
				.error(R.drawable.camelmin)
				.into(imageView);

	}


	public void goToacc() {
		Intent intent = new Intent(this,createAcc.class);
		startActivity(intent);
	}

	public void goToUserPage() throws Exception {
		EditText username = findViewById(R.id.username);
		EditText password = findViewById(R.id.Password);
		String typedName = username.getText().toString().toLowerCase();
		String userPass = password.getText().toString();
		DatabaseHandler databaseHandler = new DatabaseHandler(this);
		Contact getcontact;

//		  getcontact = databaseHandler.getLogInInfo(typedName);
//		createAcc cr = new createAcc();
//		String pass = cr.decrypt(getcontact.getPassword());

//  		if( getcontact.getUserName()!=null && getcontact.getUserName().equals(typedName) ){
//			Intent intent = new Intent(this, userProfile.class);
// 				Toast.makeText(getApplicationContext(), "Sign in of "+ typedName + " is Successful", Toast.LENGTH_SHORT).show();
//
// 				intent.putExtra("name", typedName);
// 			startActivity(intent);
//		}else{
// 			Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
//		}

		if (!typedName.isEmpty()) {
			Intent intent = new Intent(this,userDrawer.class);
			intent.putExtra("name",typedName);
			startActivity(intent);
		}
		else {
			Toast.makeText(getApplicationContext(),"Sign in unSuccessful",Toast.LENGTH_SHORT).show();

		}
	}
	private void anim_Slide_Up() {
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_slide_up);
		cardView.setAnimation(anim);
		anim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {


			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});


	}
	private void anim_Slide_down() {
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_slide_down);
		imageView.setAnimation(anim);
		anim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {


			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});


	}
}



