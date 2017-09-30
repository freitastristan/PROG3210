package com.example.tristan.prog3210a01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity
        implements OnEditorActionListener, OnClickListener {

    private EditText username;
    private EditText firstName;
    private EditText lastName;
    private CheckBox isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Context context = getApplicationContext();
        CharSequence text = "Login Successful";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        username = (EditText) findViewById(R.id.txtProfileUsername);
        firstName = (EditText) findViewById(R.id.txtProfileFirstName);
        lastName = (EditText) findViewById(R.id.txtProfileLastName);
        isVisible = (CheckBox) findViewById(R.id.chkProfileVisible);
        username.setText("tFreitas");
        firstName.setText("Tristan");
        lastName.setText("Freitas");
        isVisible.setChecked(true);

    }

    @Override
    public void onClick(View v) {
        //login.ck
        switch(v.getId())
        {
            case R.id.btnProfileMaps:
                Intent intent = new Intent(this, TempMapActivity.class);
                startActivity(intent);
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}
