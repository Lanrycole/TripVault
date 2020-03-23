package com.example.tripvault.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripvault.R;
import com.example.tripvault.data.Contact;
import com.example.tripvault.data.DatabaseHandler;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
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
	String finalPassword = "";


	private final byte[] ENCRYPTIONKEY = {9,115,51,86,105, 4, -31, -23, -68, 88, 17, 20, 3, -105, -119, -53};
	private Cipher cipher, decipher;
	private SecretKey secretKey;
	private final String AES= "AES";

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

		try {
			cipher = Cipher.getInstance(AES);
			decipher = Cipher.getInstance(AES);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}

		secretKey = new SecretKeySpec(ENCRYPTIONKEY,"AES" );

		signUpButton.setOnClickListener(this);
		signUpCancel.setOnClickListener(this);

    }


	@RequiresApi(api = Build.VERSION_CODES.O)
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
				DatabaseHandler databaseHandler = new DatabaseHandler(createAcc.this);
				Log.i("ALL","onClick: " +databaseHandler.getAllContact());
				break;
			}

		}

	@RequiresApi(api = Build.VERSION_CODES.O)
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
			String finalPassword  = encrypt(password);
			Contact contact = new Contact();
			contact.setUserName(username);
			contact.setPassword(finalPassword);
			contact.setEmailAddress(email);
			contact.setCity(address);
			contact.setPhone_num(phone_number);
			DatabaseHandler databaseHandler = new DatabaseHandler(createAcc.this);
//			databaseHandler.addContact(contact);
			Log.i("ALL","onClick: " +databaseHandler.getAllContact());
		}
	}
	public String encrypt(String password) throws  Exception{
		byte[] stringByte = password.getBytes();
		byte[] encryptedByte = new byte[stringByte.length];
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encryptedByte = cipher.doFinal(stringByte);
		return new String(encryptedByte,StandardCharsets.ISO_8859_1);
	}
	public String decrypt(String password) throws Exception{
		byte [] encrypted = password.getBytes(StandardCharsets.ISO_8859_1);
		String decryptedString = password;
		byte[] decryption;
		decipher.init(Cipher.DECRYPT_MODE, secretKey);
		decryption = decipher.doFinal(encrypted);
		decryptedString = new String(decryption);
		return decryptedString;
	}

}
