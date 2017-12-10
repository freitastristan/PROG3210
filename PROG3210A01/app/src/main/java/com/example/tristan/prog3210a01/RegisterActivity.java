package com.example.tristan.prog3210a01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
        implements OnEditorActionListener, OnClickListener  {

    private EditText username;
    private EditText password;
    private Button register;
    private boolean error;

    private AppUsers user;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = AppDatabase.getDatabase(getApplicationContext());

        username = (EditText) findViewById(R.id.txtRegUsername);
        password = (EditText) findViewById(R.id.txtRegisterPassword);
        register = (Button) findViewById(R.id.btnRegisterAccount);

        username.requestFocus();

    }

    @Override
    public void onClick(View v) {
        user = new AppUsers(username.getText().toString(), password.getText().toString());
        try {
            database.userDao().registerUser(user);
        } catch (Exception ex) {
            error = true;
        }
        if (error) {
            Context context = getApplicationContext();
            CharSequence text = "Error: Registering User.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "User was registered. You may login.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent registerIntent = new Intent(this, MainActivity.class);
            startActivity(registerIntent);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}
