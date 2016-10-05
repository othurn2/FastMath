package com.example.oliverthurn.fastmath;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by oliverthurn on 10/4/16.
 */
public class Game extends Activity implements View.OnClickListener{

    private final float textSize = 50;
    private final float smallerTextSize = 35;
    private final int displayRowCount = 1;
    private final int buttonGridColumnRowCount = 4;
    private final int textColor = Color.WHITE;


    // setting variables global to class
    protected static int finalDisplayNumber;
    protected int highestNum;

    // Level Variables
    protected int level;
    protected String levelText = "Lvl:";

    // Variables for scoring
    protected int score = 0;
    protected int displayNum;
    protected String displayText;

    // Variables for clickCounter
    protected int clickCounter;
    protected String clickText;

    protected GridLayout numDisplayGrid;
    protected GridLayout buttonGrid;
    protected TextView additionTextView;
    protected TextView levelTextView;
    protected TextView clickCounterView;

    private final int[] blockImages = {R.drawable.blueblock75, R.drawable.greenblock75, R.drawable.redblock75, R.drawable.yellowblock75};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        // creating object elements onCreate
        numDisplayGrid = (GridLayout)findViewById(R.id.displayGrid);
        buttonGrid = (GridLayout)findViewById(R.id.buttonGrid);
        additionTextView = (TextView)findViewById(R.id.additonText);
        levelTextView = (TextView)findViewById(R.id.levelTextView);
        clickCounterView = (TextView)findViewById(R.id.clickCounterTextView);

        highestNum = 100;
        level = 0;
        clickCounter = 0;
        boolean play = true;

        //playGame(play);

        createNumDisplayGrid(2, highestNum);
        createButtonGrid(highestNum);
        createAdditionDisplay();
        createLevelDisplay();
        createClickCounterDisplay();


    }

    @Override
    public void onClick(View v) {


    }

    /* Method to create the display for the number to try and add to.
     * int digits - the number of digits in the number, will determine how many display blocks
     * int high - the highest number that can be created to guess
     * First clear the grid set the row count, using constant here because it will always be one
     * Random number created to generate the block color from and array of the block objects
     * for less than the number of digits create that many textViews with backgrounds that are the
     * blocks, then add a number to them.
     */

    public void createNumDisplayGrid(int digits, int high){

        numDisplayGrid.removeAllViews();
        numDisplayGrid.setRowCount(1);
        numDisplayGrid.setColumnCount(digits);

        Random randDisplay = new Random();
        displayNum = randDisplay.nextInt(high) + 1;
        finalDisplayNumber = displayNum;
        Log.i("Info: randDisplayNum", "" + displayNum);
        displayText = Integer.toString(displayNum);
        Log.i("Info: displayText", displayText);
        Log.i("displaytextlength", "" + displayText.length());


        for (int i = 0; i < digits; i++){

            // Setting up blocks for grid
            Random random = new Random();
            int numToGet = random.nextInt(blockImages.length);

            TextView textView = new TextView(this);
            textView.setBackgroundResource(blockImages[numToGet]);

            // Setting text in image
            textView.setTextSize(textSize);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setTextColor(textColor);

            // Setting that char into text
            textView.setText(displayText);

            numDisplayGrid.addView(textView,i);
        }
    }

    public void createClickCounterDisplay(){
        //clickCounterView.setBackgroundResource(R.drawable.whiteandblack10040);
        clickCounterView.setTextColor(textColor);
        clickCounterView.setTextSize(smallerTextSize);
        clickCounterView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        clickText = "" + clickCounter;
        clickCounterView.setText(clickText);
    }

    public void createAdditionDisplay(){
        additionTextView.setBackgroundResource(R.drawable.goldblock75);
        additionTextView.setTextColor(textColor);
        additionTextView.setTextSize(textSize);
        additionTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        additionTextView.setText(Integer.toString(score));

    }

    public void createLevelDisplay(){
        //levelTextView.setBackgroundResource(R.drawable.whiteandblack10040);
        levelTextView.setTextSize(smallerTextSize);
        levelTextView.setTextColor(textColor);
        levelTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        levelTextView.setText(levelText + level);

    }

    /* Method to create the button grid
     * */

    public void createButtonGrid(int high){

        buttonGrid.removeAllViews();
        buttonGrid.setRowCount(buttonGridColumnRowCount);
        buttonGrid.setColumnCount(buttonGridColumnRowCount);


            for (int i = 0; i < (buttonGrid.getRowCount() * buttonGrid.getColumnCount()); i++){

                final Button button = new Button(this);
                button.setOnClickListener(this);
                button.setId(i);

                Random rand = new Random();
                int a = rand.nextInt(blockImages.length);
                int buttonNum = (finalDisplayNumber) % (rand.nextInt(finalDisplayNumber) + 1) + 1;

                button.setBackgroundResource(blockImages[a]);
                button.setTextSize(textSize);
                button.setTextColor(textColor);

                button.setText(Integer.toString(buttonNum));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score += Integer.parseInt(button.getText().toString());
                        additionTextView.setText(Integer.toString(score));
                        Log.i("Info", "was pressed");
                        clickCounter++;
                        clickText = Integer.toString(clickCounter);
                        clickCounterView.setText(clickText);
                        button.setClickable(false);
                        checkForMatch();

                    }

                });
                buttonGrid.addView(button);
            }


    }

    public void checkForMatch() {
        if (score == displayNum){
            level++;
            //levelText = levelText + level;
            levelTextView.setText(levelText + level);
            score = 0;
            createNumDisplayGrid(2, (highestNum * ((level + 1 / 2))));
            createButtonGrid(highestNum * ((level + 1) / 2));
            createAdditionDisplay();
        } else if (score > displayNum){
            level = 0;
            //levelText = levelText + level;
            levelTextView.setText(levelText + level);
            score = 0;
            clickCounter = 0;
            clickText = Integer.toString(clickCounter);
            clickCounterView.setText(clickText);
            createNumDisplayGrid(2, highestNum);
            createButtonGrid(highestNum);
            createAdditionDisplay();

        }
    }
}
