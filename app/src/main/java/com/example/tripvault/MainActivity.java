package com.example.tripvault;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tripvault.UI.createAcc;
import com.example.tripvault.UI.userProfile;
import com.example.tripvault.data.Contact;
import com.example.tripvault.data.DatabaseHandler;

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
				try {
					goToUserPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void goToacc(){
		Intent intent = new Intent(this, createAcc.class);
		startActivity(intent);
	}
	public void goToUserPage() throws Exception {
		EditText username = findViewById(R.id.username);
		EditText password = findViewById(R.id.Password);

			String typedName = username.getText().toString().toLowerCase();
			String userPass = password.getText().toString();
			DatabaseHandler databaseHandler = new DatabaseHandler(this);
			Contact getcontact;


		  getcontact = databaseHandler.getLogInInfo(typedName);

		createAcc cr = new createAcc();

		String pass = cr.decrypt(getcontact.getPassword());


  		if( getcontact.getUserName()!=null && getcontact.getUserName().equals(typedName) ){
			Intent intent = new Intent(this, userProfile.class);
 				Toast.makeText(getApplicationContext(), "Sign in of "+ typedName + " is Successful", Toast.LENGTH_SHORT).show();

 				intent.putExtra("name", typedName);
 			startActivity(intent);
		}else{
 			Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
		}
		}


	}

