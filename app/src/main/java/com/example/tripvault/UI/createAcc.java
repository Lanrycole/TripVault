package com.example.tripvault.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripvault.R;
import com.example.tripvault.Util.EncryptPasswd;
import com.example.tripvault.data.Contact;
import com.example.tripvault.data.DatabaseHandler;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class createAcc extends AppCompatActivity implements View.OnClickListener {
    Button signUpCancel;
    EditText signUsername;
	EditText signUpPasswd;
	EditText confirmPasswd;

	EditText signUpEmail;
	EditText signUpAddress;
	EditText signupPhoneNumber;
	Button signUpButton;

	String username;
	String password;
	String email;
	String address;
	String phone_number;
	String confirmPassword;


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
		confirmPasswd = findViewById(R.id.confirmsignUpPasswd);

        signUpCancel.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

	@Override
	public void onClick(View v) {
    	switch (v.getId()){
			case R.id.signUpButton:

				try {
					processRegistration();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case R.id.signUpCancel:
				finish();
				break;
		}

	}

	public void processRegistration() throws Exception {

		username = signUsername.getText().toString();
		email = signUpEmail.getText().toString();
		password = signUpPasswd.getText().toString();
		address = signUpAddress.getText().toString();
		phone_number = signupPhoneNumber.getText().toString();
		confirmPassword= confirmPasswd.getText().toString();


		if (username.isEmpty() || email.isEmpty() || address.isEmpty()|| phone_number.isEmpty()||password.isEmpty() || confirmPassword.isEmpty()) {
			Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
		}
		else if(!password.equals(confirmPassword)){
			Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();

		}else {

			String finalPassword  = encryptPassword(password);

			Contact contact = new Contact();
			contact.setUserName(username);
			contact.setPassword(finalPassword);
			contact.setEmailAddress(email);
			contact.setCity(address);
			contact.setPhone_num(phone_number);


			DatabaseHandler databaseHandler = new DatabaseHandler(createAcc.this);
					databaseHandler.addContact(contact);
			databaseHandler.deleteAll();
			Log.i("ALL","onClick: " +contact.getPassword());
		}
	}

public String encryptPassword( String finalpass)  {
    	String md5string;
	finalpass=password;
	byte[]encryptPass= new byte[0];
	try {
		encryptPass = finalpass.getBytes("UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	BigInteger md5encrypt=null;

	try {
		md5encrypt=new BigInteger(1,EncryptPasswd.encryptMD5(encryptPass));
	} catch (Exception e) {
		e.printStackTrace();
	}
	md5string=md5encrypt.toString();
		return md5string;
}

}
