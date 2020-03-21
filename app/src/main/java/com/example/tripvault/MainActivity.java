package com.example.tripvault;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripvault.UI.createAcc;
import com.example.tripvault.UI.userProfile;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button logIn = findViewById(R.id.login);
		Button createAcc = findViewById(R.id.createAccount);

		createAcc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				goToacc();
			}
		});
		logIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goToUserPage();
			}
		});
	}

	public void goToacc(){
		Intent intent = new Intent(this, createAcc.class);
		startActivity(intent);
	}
	public void goToUserPage(){

		EditText username = findViewById(R.id.username);
		Intent intent = new Intent(this, userProfile.class);
		String typedName = username.getText().toString();

		if(typedName.isEmpty() || typedName.length()<4 || typedName.contains(" ")){
			Toast toast=Toast.makeText(getApplicationContext(),"Username cannot be empty",Toast.LENGTH_SHORT);
			toast.show();
		}else{
			intent.putExtra("name", typedName);
			startActivity(intent);
		}
	}

}