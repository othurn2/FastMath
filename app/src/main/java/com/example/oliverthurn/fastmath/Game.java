package com.example.oliverthurn.fastmath;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by oliverthurn on 10/4/16.
 */
public class Game extends Activity implements View.OnClickListener{

    // setting variables global to class
    protected static int score;
    protected int highestNum;
    protected GridLayout numDisplayGrid;
    protected GridLayout buttonGrid;

    private final int[] blockImages = {R.drawable.blueblock75, R.drawable.greenblock75, R.drawable.redblock75, R.drawable.yellowblock75};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.);

        // creating grids onCreate
        numDisplayGrid = (GridLayout)findViewById(R.id.displayGrid);
        buttonGrid = (GridLayout)findViewById(R.id.buttonGrid);

        setNumDisplayGrid(2, 15);
    }

    @Override
    public void onClick(View v) {

    }

    public void setNumDisplayGrid(int digits, int high){

        numDisplayGrid.setRowCount(1);
        numDisplayGrid.setColumnCount(digits);

        Random randDisplay = new Random();
        int displayNum = randDisplay.nextInt(high) + 1;
        String displayString = Integer.toString(displayNum);


        for (int i = 0; i < digits; i++){

            // Setting up blocks for grid
            Random random = new Random();
            int numToGet = random.nextInt(blockImages.length);

            //int imageNum = blockImages[numToGet];

            TextView textView = new TextView(this);
            textView.setBackgroundResource(blockImages[numToGet]);

            //ImageView image = new ImageView(this);
            //image.setImageResource(imageNum);

            // Setting text in image
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            textView.setText(displayString.indexOf(i));

            numDisplayGrid.addView(textView,i);

        }


    }

}
