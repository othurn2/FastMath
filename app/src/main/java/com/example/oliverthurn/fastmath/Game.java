package com.example.oliverthurn.fastmath;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.HardwarePropertiesManager;
import android.renderscript.Sampler;
import android.text.Layout;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import org.w3c.dom.Text;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by oliverthurn on 10/4/16.
 */
public class Game extends Activity implements View.OnClickListener  {



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
    protected int sinceWinClickCounter;
    protected String clickText;

    protected GridLayout numDisplayGrid;
    protected GridLayout buttonGrid;
    protected TextView additionTextView;
    protected TextView levelTextView;
    protected TextView clickCounterView;
    protected TextView scorePlus;
    protected ImageView goldMedal;
    protected ImageView silverMedal;
    protected ImageView bronzeMedal;


    // booleans for determining if how many buttons have to be pressed to add to the number
    protected static boolean twoMatch = false;
    protected static boolean threeMatch = false;
    protected static boolean fourMatch = false;

    private final int[] blockImages = {R.drawable.blueblock75, R.drawable.greenblock75, R.drawable.redblock75, R.drawable.yellowblock75};

    private final int[] blockImages150 = {R.drawable.blueblock150, R.drawable.greenblock150, R.drawable.redblock150, R.drawable.yellowblock150};


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
        scorePlus = (TextView)findViewById(R.id.scorePlusView);

        silverMedal = (ImageView)findViewById(R.id.silverMedalView); // Sets the medals on the screen but invisible at the creation time
        silverMedal.setAlpha(0);
        bronzeMedal = (ImageView)findViewById(R.id.bronzeMedalView);
        bronzeMedal.setAlpha(0);
        goldMedal = (ImageView)findViewById(R.id.goldMedalView);
        goldMedal.setAlpha(0);

        highestNum = 99;
        level = 0;
        clickCounter = 0;


        generateRandom(highestNum);
        createScorePlusView();
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


    public void createScorePlusView(){

        scorePlus.setText("");
        scorePlus.setTextColor(textColor);
        scorePlus.setTextSize(smallerTextSize);

    }

    public void createNumDisplayGrid(int high){
        int digits = -1;
        Random randDisplay = new Random();
        displayNum = randDisplay.nextInt(high) + 10;
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
        int numToGet = random.nextInt(blockImages150.length);


        String holder = Integer.toString(displayNum);
        Log.i("Info Holder", holder);

        TextView textView = new TextView(this);
        textView.setBackgroundResource(blockImages150[numToGet]);

        // Setting text in image
        textView.setTextSize(textSize);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(textColor);

        // Setting that char into text
        textView.setText(holder);

        numDisplayGrid.addView(textView);
    }


    public void generateRandom(int high){
            Random randDisplay = new Random();
            highestNum = randDisplay.nextInt(high) + 1;
        }

    public void createClickCounterDisplay(){
        clickCounterView.setTextColor(textColor);
        clickCounterView.setTextSize(smallerTextSize);
        clickCounterView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        clickText = "" + clickCounter;
        clickCounterView.setText(clickText);
    }

    public void createAdditionDisplay(){

        //additionTextView.setBottom((int)additionDisplayY);
        additionTextView.setBackgroundResource(R.drawable.goldblock75);
        additionTextView.setTextColor(textColor);
        additionTextView.setTextSize(textSize);
        additionTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        additionTextView.setText(Integer.toString(score));
    }

    public void createLevelDisplay(){
        levelTextView.setTextSize(smallerTextSize);
        levelTextView.setTextColor(textColor);
        levelTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        levelTextView.setText(levelText + level);

    }

    /**
     * Bubble sort method to be used inside of the createButtonGrid
     * */

    public static void bubble(int[] array){

        for(int count = 0; count < array.length - 1; count++){
            boolean swapped = false;

            for(int i = 1; i < array.length; i++){
                int a = array[i-1];
                int b = array[i];
                if (a < b ){
                    int c = a;
                    array[i-1] = b;
                    array[i] = c;
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
    }

    /**
     * method to find a match in the array used to sort the grid numbers
     * */

    public static void findAdditionMatch(int[] array, int match){

        for(int i = 0; i < array.length - 3; i++){
            int a = array[i];
            if(twoMatch && threeMatch){
                break;
            }
            for(int j = 1; j < array.length - 2; j++){
                int b = array[j];
                if (a + b == match){
                    //Log.i("MyInfo"," There is a match in a + b");
                    twoMatch = true;
                }
                if(twoMatch && threeMatch && fourMatch){
                    break;
                }
                    for (int k = 2; k < array.length - 1; k++){
                        int c = array[k];
                        if (a + b + c == match){
                            //Log.i("MyInfo"," There is a match in a + b + c");
                            threeMatch = true;
                        }
                        if(twoMatch && threeMatch && fourMatch){
                            break;
                        }

                        for (int l = 3; l < array.length; l++){
                            int d = array[l];
                                if (a + b + c + d == match){
                                    fourMatch = true;
                                }
                                    if (twoMatch && threeMatch && fourMatch){
                                        break;
                                    }
                        }

                    }
                }
            }
        Log.i("MyInfo","twoMatch" + twoMatch);
        Log.i("MyInfo","threeMatch" + threeMatch);
        Log.i("MyInfo","fourMatch" + fourMatch);



    }

    /* Method to create the button grid
     * */

    public void createButtonGrid(int high){

        twoMatch = false;
        threeMatch = false;
        fourMatch = false;

        buttonGrid.removeAllViews();
        buttonGrid.setRowCount(buttonGridColumnRowCount);
        buttonGrid.setColumnCount(buttonGridColumnRowCount);

        int[] sortableArray = new int[(buttonGridColumnRowCount * buttonGridColumnRowCount)];


            for (int i = 0; i < (buttonGrid.getRowCount() * buttonGrid.getColumnCount()); i++){

                final Button button = new Button(this);
                button.setOnClickListener(this);
                button.setId(i);

                Random rand = new Random();
                int a = rand.nextInt(blockImages.length);
                int buttonNum = (finalDisplayNumber) % (rand.nextInt(finalDisplayNumber) + 1) + 1;

                sortableArray[i] = buttonNum;

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
                        sinceWinClickCounter++;
                        clickText = Integer.toString(clickCounter);
                        clickCounterView.setText(clickText);
                        button.setClickable(false);
                        button.animate().alpha(0);
                        checkForMatch();

                    }

                });
                buttonGrid.addView(button);
            }

        bubble(sortableArray);
        findAdditionMatch(sortableArray, finalDisplayNumber);


    }

    public void checkForMatch() {

        if (score == displayNum){
            level++;
            //levelText = levelText + level;
            levelTextView.setText(levelText + level);
            score = 0;
            displayNum = 0;
            answerViewAnimation(numDisplayGrid, highestNum * ((level + 1 )/ 2));
            createButtonGrid(highestNum * ((level + 1) / 2));
            createAdditionDisplay();
            sinceWinClickCounter = 0;

        } else if (score > displayNum){

            createAdditionViewAnimation(additionTextView);
            level = 0;
            levelTextView.setText(levelText + level);
            score = 0;
            displayNum = 0;
            clickCounter = 0;
            sinceWinClickCounter = 0;
            clickText = Integer.toString(clickCounter);
            clickCounterView.setText(clickText);
            createNumDisplayGrid(highestNum);
            createButtonGrid(highestNum);
            createAdditionDisplay();

        }
    }

    /** public void createAnimationMove(View view)
     *
     * @param view - the view manipulated. Any view that comes from View.
     *
     *  Uses a ViewPropertyAnimator to change the x and y position of the view.
     *  Then rotates 360 degrees
     *  Attempts to put object back in its original spot
     *
     * */


    public void createAdditionViewAnimation(View view){


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        final float originalX = view.getX();
        final float originalY = view.getY();

        final float screenX = (float) dm.widthPixels;
        final float screenY = (float) dm.heightPixels;

        float moveToX = ((screenX / 2) - (view.getWidth() / 2));
        float moveToY = ((screenY / 2) - (view.getHeight() / 2));

        AnimatorSet translateAnimatorSet = new AnimatorSet();

        ValueAnimator translateToX = ObjectAnimator.ofFloat(view, "X", moveToX);
        translateToX.setDuration(2000);

        ValueAnimator translateToY = ObjectAnimator.ofFloat(view, "Y", moveToY);
        translateToY.setDuration(2000);

        ValueAnimator rotateView = ObjectAnimator.ofFloat(view, "Rotation", 360);
        rotateView.setDuration(2000);

        ValueAnimator rotateBackView = ObjectAnimator.ofFloat(view, "Rotation", 0);
        rotateBackView.setDuration(1000);

        ValueAnimator backToX = ObjectAnimator.ofFloat(view, "X", originalX);
        backToX.setDuration(1000);

        ValueAnimator backToY = ObjectAnimator.ofFloat(view, "Y", originalY);
        backToY.setDuration(1000);

        translateAnimatorSet.play(translateToX).with(translateToY).with(rotateView);
        translateAnimatorSet.play(backToX).with(backToY).with(rotateBackView).after(rotateView);
        translateAnimatorSet.start();
    }


    public void answerViewAnimation(View view, int high){

        float originalX = view.getX();
        float originalY = view.getY();

        Log.i("ScoreViewOGX", "" + originalX);
        Log.i("ScoreViewOGY", "" + originalY);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final float endX = (float) dm.widthPixels;
        final float endY = (float) dm.heightPixels;

        AnimatorSet answerSet = new AnimatorSet();

        ValueAnimator moveXOne =ObjectAnimator.ofFloat(view, "X", (float) (endX + view.getWidth()));
        moveXOne.setDuration(500);

        ValueAnimator moveXTwo = ObjectAnimator.ofFloat(view, "X", (float) 0 - view.getWidth(), originalX);
        moveXTwo.setDuration(500);

        answerSet.play(moveXOne);
        createNumDisplayGrid(high);
        answerSet.play(moveXTwo).after(moveXOne);
        answerSet.start();
    }




}
