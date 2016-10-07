package com.example.oliverthurn.fastmath;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.HardwarePropertiesManager;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOError;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

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

        highestNum = 99;
        level = 0;
        clickCounter = 0;
        boolean play = true;

        //playGame(play);

        generateRandom(highestNum);
        createNumDisplayGrid(highestNum);
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

    public void createNumDisplayGrid(int high){
        int digits = -1;
        Random randDisplay = new Random();
        displayNum = randDisplay.nextInt(high) + 1;
        finalDisplayNumber = displayNum;
        String highString = Integer.toString(high);

        Log.i("Info: stringLength", "" + highString.length());

        if (highString.length() == 1 ){
            digits = 1;
        } else if ( highString.length() == 2){
            digits = 2;
        } else if( highString.length() == 3) {
            digits = 3;
        }

        Log.i("Info: randDisplayNum", "" + displayNum);
        displayText = Integer.toString(displayNum);
        Log.i("Info: displayText", displayText);
        Log.i("displaytextlength", "" + displayText.length());



        numDisplayGrid.removeAllViews();
        numDisplayGrid.setRowCount(1);
        numDisplayGrid.setColumnCount(digits);

        Log.i("Info: randDisplayNum", "" + displayNum);
        displayText = Integer.toString(displayNum);
        Log.i("Info: displayText", displayText);
        Log.i("displaytextlength", "" + displayText.length());

        Random random = new Random();
        int numToGet = random.nextInt(blockImages.length);

        for (int i = 0; i < digits; i++){

            String holder = "";
            // Setting up blocks for grid

            if(displayNum < 10) {
                holder = Integer.toString(displayNum);
            } else if (displayNum >= 10){
                    if( i == 0){
                        holder = displayText.substring(i, i+1);
                        } else if (i == 1){
                        holder = displayText.substring(i, i + 1);
                            } else if ( i == 2){
                            holder = displayText.substring(i);
                            }
            }

            Log.i("Info Holder", holder);

            TextView textView = new TextView(this);
            textView.setBackgroundResource(blockImages[numToGet]);

            // Setting text in image
            textView.setTextSize(textSize);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setTextColor(textColor);

            // Setting that char into text
            textView.setText(holder);

            numDisplayGrid.addView(textView,i);
        }
    }


    public void generateRandom(int high){
            Random randDisplay = new Random();
            highestNum = randDisplay.nextInt(high) + 1;
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
            displayNum = 0;
            createNumDisplayGrid((highestNum * ((level + 1 )/ 2)));
            createButtonGrid(highestNum * ((level + 1) / 2));
            createAdditionDisplay();
        } else if (score > displayNum){

            //additionTextView.animate().x(screenX / 2).y((screenY/ 2)).setDuration(1000).start();
            createAnimationBlockMove(additionTextView);
            //Animation moveAnswer = AnimationUtils.loadAnimation(this, R.anim.additionbuttonanimation);
            // additionTextView.startAnimation(moveAnswer);

            //createAnimation(additionTextView);
            level = 0;
            //levelText = levelText + level;
            levelTextView.setText(levelText + level);
            score = 0;
            displayNum = 0;
            clickCounter = 0;
            clickText = Integer.toString(clickCounter);
            clickCounterView.setText(clickText);
            createNumDisplayGrid(highestNum);
            createButtonGrid(highestNum);
            createAdditionDisplay();

        }
    }

    public void createAnimationBlockMove(View view) {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        float originalX = (float) view.getX();
        float originalY = (float) view.getY();

        final float screenX = (float) dm.widthPixels;
        final float screenY = (float) dm.heightPixels;

       final ViewPropertyAnimator b = view.animate();
        Runnable endAction = new Runnable() {

            public void run() {
                //b.animate().x(screenX).y(screenY).setDuration(1800);
            }
        };

        final ViewPropertyAnimator a = view.animate().x(originalX).y(screenY / 2).setDuration(2000).rotation(360).setDuration(1800).withEndAction(endAction);

        //ViewPropertyAnimator b = view.animate().rotation(360);
        //ViewPropertyAnimator c = view.animate().x(view.getX() + originalX).y(screenY / 2).setStartDelay(4000);
        //ViewPropertyAnimator d = view.animate().translationX(originalX).translationY(originalY);


    }

    public void createAnimation(View view){

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        float originalX = view.getX();
        float originalY = view.getY();

        float screenX = (float) dm.widthPixels;
        float screenY = (float) dm.heightPixels;

        AnimationSet animationSet = new AnimationSet(false);
        Animation animations = new TranslateAnimation(originalX, screenX / 2, originalY, screenY / 2);
        animations.setDuration(4000);
        animations.setFillAfter(false);
        //view.setAnimation(animations);

        TranslateAnimation trans = new TranslateAnimation(Animation.ABSOLUTE, ((screenX / 2) + (view.getWidth() / 2)),Animation.ABSOLUTE,  originalX, Animation.ABSOLUTE, ((screenY / 2) + view.getHeight()/ 2), Animation.ABSOLUTE, originalY);
        trans.setDuration(1800);

        RotateAnimation rotation = new RotateAnimation(0, 360);
        rotation.setDuration(2000);

        TranslateAnimation transTwo = new TranslateAnimation(view.getX(), view.getY(), view.getY(), originalY);
        transTwo.setDuration(1800);
       // transTwo.setStartOffset(4000);

        animationSet.addAnimation(animations);
       // animationSet.addAnimation(rotation);

        view.setAnimation(animationSet);


        //rotation.start();



    }



}
