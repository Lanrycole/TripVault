package com.example.tripvault.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripvault.MainActivity;
import com.example.tripvault.R;
import com.example.tripvault.data.User;
import com.example.tripvault.data.DatabaseHandler;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class createAcc extends AppCompatActivity implements View.OnClickListener {
    private static Cipher decipher;
    private static Key secretKey;
    private static Cipher cipher;
    EditText signUsername;
    EditText signUpPasswd;
    EditText confirmPasswd;
    CheckBox checkBox;
    TextInputLayout userNameError;
    TextInputLayout passwordErroe;
    TextInputLayout confirmPasswordError;
    TextInputLayout emailError;
    TextInputLayout cityError;
    TextInputLayout phoneNumError;
    EditText signUpEmail;
    EditText signUpAddress;
    EditText signupPhoneNumber;
    Button signUpButton;
    Button signInFromCreate;

    String username;
    String password;
    String email;
    String address;
    String phone_number;
    String confirmPassword;
    MaterialToolbar toolbar;
    ArrayList<User> addContacts = new ArrayList<>();


    private final byte[] ENCRYPTIONKEY = {9, 115, 51, 86, 105, 4, -31, -23, -68, 88, 17, 20, 3, -105, -119, -53};

    private final String AES = "AES";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        signUsername = findViewById(R.id.signUpUserName);
        signUpPasswd = findViewById(R.id.signUpPasswd);
        signUpEmail = findViewById(R.id.signUpserEmail);
        signupPhoneNumber = findViewById(R.id.signUpUserPhoneNum);
        signUpAddress = findViewById(R.id.signUpUserAdd);
        signUpButton = findViewById(R.id.signUpButton);
        confirmPasswd = findViewById(R.id.confirmsignUpPasswd);
        signInFromCreate = findViewById(R.id.SignInlogin);


        ;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        try {
            cipher = Cipher.getInstance(AES);
            decipher = Cipher.getInstance(AES);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        secretKey = new SecretKeySpec(ENCRYPTIONKEY, "AES");
        signUpButton.setOnClickListener(this);
        signInFromCreate.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpButton:
                try {
                    processRegistration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.SignInlogin:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

        }
    }


    public void processRegistration() throws Exception {
        checkBox = findViewById(R.id.checkbox);

        userNameError = findViewById(R.id.usernameError);
        passwordErroe = findViewById(R.id.passwordError);
        cityError = findViewById(R.id.cityError);
        confirmPasswordError = findViewById(R.id.confirmpasswordError);
        phoneNumError = findViewById(R.id.phoneError);
        emailError = findViewById(R.id.emailError);

        username = signUsername.getText().toString();
        email = signUpEmail.getText().toString();
        password = signUpPasswd.getText().toString();
        address = signUpAddress.getText().toString();
        phone_number = signupPhoneNumber.getText().toString();
        confirmPassword = confirmPasswd.getText().toString();

        if (username.isEmpty()) {
            userNameError.setError("Enter a username");
        } else {
            userNameError.setError(" ");
        }
        if (password.isEmpty()) {
            passwordErroe.setError("Enter password");
        } else {
            passwordErroe.setError(" ");
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordError.setError("Password does not match");
            passwordErroe.setError("Password does not match");
        } else {
            confirmPasswordError.setError(" ");
            passwordErroe.setError(" ");
        }
        if (address.isEmpty()) {
            cityError.setError("Enter a city");
        } else {
            cityError.setError(" ");
        }
        if (phone_number.isEmpty()) {
            phoneNumError.setError("Enter phone Number");
        } else {
            phoneNumError.setError(" ");
        }
        if (email.isEmpty()) {
            emailError.setError("Enter an email");
        } else {
            emailError.setError(" ");
        }

        if (!email.isEmpty() && !address.isEmpty() && !phone_number.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && password.equals(confirmPassword)) {

            String finalPassword = encrypt(password);
            Log.d("ENCRYPT", "Encrypted Password: " + finalPassword);
            User user = new User();
            user.setUserName(username.toLowerCase());
            user.setPassword(finalPassword);
            user.setEmailAddress(email);
            user.setCity(address);
            user.setPhone_num(phone_number);
            DatabaseHandler databaseHandler = new DatabaseHandler(createAcc.this);
            databaseHandler.addContact(user);
            addContacts.add(user);
            Toast.makeText(getApplicationContext(), "Sign up Successful", Toast.LENGTH_SHORT).show();
            Log.i("ALL", "onClick: " + databaseHandler.getAllContact());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


    }




    public static String encrypt(String password) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        byte[] stringByte = password.getBytes();
        byte[] encryptedByte = new byte[stringByte.length];
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        encryptedByte = cipher.doFinal(stringByte);
        String pass = new String(encryptedByte, StandardCharsets.ISO_8859_1);
        return pass;
    }

    public static String decrypt(String password) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] encrypted = password.getBytes(StandardCharsets.ISO_8859_1);
        String decryptedString = password;
        byte[] decryption;
        decipher.init(Cipher.DECRYPT_MODE, secretKey);
        decryption = decipher.doFinal(encrypted);
        decryptedString = new String(decryption);
        return decryptedString;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
