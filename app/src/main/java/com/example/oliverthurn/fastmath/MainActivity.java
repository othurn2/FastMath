package com.example.oliverthurn.fastmath;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button playButton;
    protected Button quitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button)findViewById(R.id.playButton);
        quitButton = (Button)findViewById(R.id.exitButton);
        playButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);
        tst();
    }
    public void tst(){
     boolean blah = true;
        int score = 50;
        int highScore = 124;
        if(score > highScore || blah){
            Log.i("Info", "highScore");

        }else{
            Log.i("Info", "have a nice day");
        }

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.playButton:
                startActivity(new Intent(MainActivity.this, Game.class));
                break;
            case R.id.exitButton:
                System.exit(0);
                break;
        }

    }
}
