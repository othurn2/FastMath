package com.example.oliverthurn.fastmath;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected ImageButton playButton;
    protected ImageButton quitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (ImageButton)findViewById(R.id.playButton);
        quitButton = (ImageButton)findViewById(R.id.quitButton);
        playButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.playButton:
                startActivity(new Intent(MainActivity.this, Game.class));
                break;
            case R.id.quitButton:
                break;
        }

    }
}
