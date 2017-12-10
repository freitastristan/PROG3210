package com.example.tristan.prog3210a01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity  extends Activity
implements OnEditorActionListener, OnClickListener {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        login    = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnRegisterAccount);
    }

    @Override
    public void onClick(View v) {
        //login.ck
        switch(v.getId())
        {
            case R.id.btnLogin:
                loginAuthenticator(username.getText().toString(), password.getText().toString());
                break;
            case R.id.btnRegisterAccount:
                Intent registerIntent = new Intent(this, RegisterActivity.class);
                startActivity(registerIntent);
                break;
            default:
                throw new RuntimeException("Button click error");
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    public void loginAuthenticator(String username, String password) {
        String reqUsername = "tfreitas";
        String reqPassword = "password";

        if (username.equals(reqUsername) && password.equals(reqPassword))
        {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Login Failed. Please try again.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.d(TAG, "incorrect");
        }

    }
}
