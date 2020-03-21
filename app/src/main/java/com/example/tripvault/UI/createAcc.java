package com.example.tripvault.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripvault.R;
import com.example.tripvault.data.Contact;
import com.example.tripvault.data.DatabaseHandler;

public class createAcc extends AppCompatActivity implements View.OnClickListener {
    Button signUpCancel;
    EditText signUsername;
	EditText signUpPasswd;
	EditText signUpEmail;
	EditText signUpAddress;
	EditText signupPhoneNumber;
	Button signUpButton;

	String username;
	String password;
	String email;
	String address;
	String phone_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        signUpCancel = findViewById(R.id.signUpCancel);


        signUsername = findViewById(R.id.signUpUserName);
		signUpPasswd = findViewById(R.id.signUpPasswd);
		signUpEmail = findViewById(R.id.signUpserEmail);
		signupPhoneNumber = findViewById(R.id.signUpUserPhoneNum);
		signUpAddress = findViewById(R.id.signUpUserAdd);
		signUpButton = findViewById(R.id.signUpButton);





		signUsername.setOnClickListener(this);
		signUpPasswd.setOnClickListener(this);
		signUpEmail.setOnClickListener(this);
		signUpAddress.setOnClickListener(this);
		signupPhoneNumber.setOnClickListener(this);

        signUpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        signUpButton.setOnClickListener(this);

    }

	@Override
	public void onClick(View v) {
    	switch (v.getId()){
			case R.id.signUpButton:

				username = signUsername.getText().toString();
				password = signUpPasswd.getText().toString();
				email = signUpEmail.getText().toString();
				address = signUpAddress.getText().toString();
				phone_number= signupPhoneNumber.getText().toString();

				Contact contact = new Contact();
//				contact.setUserId(1);
				contact.setUserName(username);
				contact.setPassword(password);
				contact.setEmailAddress(email);
				contact.setCity(address);
				contact.setPhone_num(phone_number);

				Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();

				DatabaseHandler databaseHandler = new DatabaseHandler(createAcc.this);
				databaseHandler.addContact(contact);

				Log.i("ALL","onClick: "+databaseHandler.getAllContact());
				break;



		}

	}
}
