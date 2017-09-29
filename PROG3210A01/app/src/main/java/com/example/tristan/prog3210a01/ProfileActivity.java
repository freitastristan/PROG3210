package com.example.tristan.prog3210a01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class ProfileActivity extends AppCompatActivity
        implements OnEditorActionListener, OnClickListener {

    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = (EditText) findViewById(R.id.txtProfileUsername);
        username.setText("Tristan");



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