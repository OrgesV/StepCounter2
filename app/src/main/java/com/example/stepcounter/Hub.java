package com.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hub extends AppCompatActivity implements View.OnClickListener {
    // Buttons!
    private Button registerButton; // Button used to get to the registration page.
    private Button musicButton;  // Button used to get to the music page.
    private Button loginButton; // Button that takes you to the login page.
    private Button personalButton; // Button that takes you to the personal page.
    private Button supportButton; // Button that takes you to the support page.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        registerButton = findViewById(R.id.reg_button);
        musicButton = findViewById(R.id.music_button);
        loginButton = findViewById(R.id.login_button);
        personalButton = findViewById(R.id.personal_button);
        supportButton = findViewById(R.id.support_button);

        registerButton.setOnClickListener(this);
        musicButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        personalButton.setOnClickListener(this);
        supportButton.setOnClickListener(this);

    };

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.reg_button:
                startActivity(new Intent(Hub.this, register.class));
                break;
            case R.id.music_button:
                startActivity(new Intent(Hub.this, music.class));
                break;
            case R.id.login_button:
                startActivity(new Intent(Hub.this, login.class));
                break;
            case R.id.personal_button:
                startActivity(new Intent(Hub.this, personal.class));
                break;
            case R.id.support_button:
                startActivity(new Intent(Hub.this, support.class));
                break;
        }

    }
}
