package com.example.loginlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignInLibraryPage extends AppCompatActivity {

    Button signin;
    EditText username,password;
    String user,pass,hashOfPass,hashFromDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_library_page);

        signin = (Button)findViewById(R.id.libSignInButton);

        username = (EditText)findViewById(R.id.libUserNameEditText);
        password = (EditText)findViewById(R.id.libPasswordEditText);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString().trim();
                APICalls apiCalls = new APICalls();
                hashFromDataBase = apiCalls.getData(user);
                pass = password.getText().toString().trim();
                hashOfPass = md5(pass);

                if(hashOfPass.equals(hashFromDataBase)){
                    Intent intent = new Intent();
                    intent.putExtra("Result","Correct");
                    setResult(100,intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Un-successful attempt",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
