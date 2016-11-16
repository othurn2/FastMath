package com.example.oliverthurn.fastmath;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity{

    protected ImageView fButton;
    protected ImageView aButtonFast;
    protected ImageView sButton;
    protected ImageView tButtonFast;
    protected ImageView mButton;
    protected ImageView aButtonMath;
    protected ImageView tButtonMath;
    protected ImageView hButton;

    protected ImageView otApps;

    protected float screenWidth;
    protected float screenHeight;
    protected float offScreenRightX;
    protected float offScreenHeight;
    protected float offScreenLeftX;
    protected final float imageWidth = 75;

    protected float firstButtonLocX;
    protected float firstButtonLocY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Creating all the buttons to come into the view
        fButton = (ImageView) findViewById(R.id.fImageView);
        aButtonFast = (ImageView) findViewById(R.id.aFastImageView);
        sButton = (ImageView) findViewById(R.id.sImageView);
        tButtonFast = (ImageView) findViewById(R.id.tFastImageView);
        mButton = (ImageView) findViewById(R.id.mImageView);
        aButtonMath = (ImageView) findViewById(R.id.aMathImageView);
        tButtonMath = (ImageView) findViewById(R.id.tMathImageView);
        hButton = (ImageView) findViewById(R.id.hImageView);

        otApps = (ImageView)findViewById(R.id.otappsView);



        // Starting a DisplayMetrics to use for positioning throughout
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        firstButtonLocX = ((screenWidth / 4) * 2) - (2 * imageWidth);
        firstButtonLocY = ((screenHeight / 4) * 2 - (4 * imageWidth));


        // Setting all buttons properties
        // Setting the image resources for each letter ImageView

        otApps.setImageResource(R.drawable.otapps);
        otApps.setX((screenWidth / 2) - 200);
        otApps.setY(((screenHeight / 3) * 2) - 170);
        otApps.setAlpha(0f);

        fButton.setImageResource(R.drawable.f);
        aButtonFast.setImageResource(R.drawable.a);
        sButton.setImageResource(R.drawable.s);
        tButtonFast.setImageResource(R.drawable.t);

        mButton.setImageResource(R.drawable.redm);
        aButtonMath.setImageResource(R.drawable.reda);
        tButtonMath.setImageResource(R.drawable.redt);
        hButton.setImageResource(R.drawable.redh);



        // Starting all letter ImageViews off the screen
        offScreenRightX = 0- (imageWidth * 2);
        offScreenLeftX = screenWidth + (fButton.getWidth() * 2);
        offScreenHeight = screenHeight / 4;  // Because there are four views on each side off the screen
        Log.i("MyInfo", "imageWidth = " + imageWidth);

        fButton.setX(offScreenRightX - 200);
        fButton.setY(offScreenHeight); // will be at the top left 2x ImageView width

        aButtonFast.setX(offScreenRightX);
        aButtonFast.setY(offScreenHeight * 2); // 2x ImageView width,  1/4 down the height

        sButton.setX(offScreenRightX);
        sButton.setY(offScreenHeight * 3);

        tButtonFast.setX(offScreenRightX);
        tButtonFast.setY(offScreenHeight * 4);

        mButton.setX(offScreenLeftX);
        mButton.setY(offScreenHeight);

        aButtonMath.setX(offScreenLeftX);
        aButtonMath.setY(offScreenHeight * 2);

        tButtonMath.setX(offScreenLeftX);
        tButtonMath.setY(offScreenHeight * 3);

        hButton.setX(offScreenLeftX);
        hButton.setY(offScreenHeight * 4);


        // Creating a animationThread to run the splash screens animation that should happen before the main menu screen is loaded
        // the thread needs to be run on the main UI thread below.
        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                imageAnimation();

            }
        });



        // Creating a thread that will open the games main menu screen after the animations have been completed
        // This thread can be ran on its own. Its only purpose is to delay the opening of the MainActivity intent.
        final Thread openGame = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        runOnUiThread(animationThread); // Running the animation thread on the UI thread.
        openGame.start(); // Running the thread that will open the main menu.
    }



    public void imageAnimation(){

        // Creating an animatorSet to contain all the animations on the splash screen
        AnimatorSet animatorThreadSet = new AnimatorSet();

        /* Creating all the animations for FAST     *       *       *       *     *       *       *     *       *       */
        ObjectAnimator fButtonAnimX = ObjectAnimator.ofFloat(fButton, "X", fButton.getX(), firstButtonLocX);
        fButtonAnimX.setDuration(1800);
        ObjectAnimator fButtonAnimY = ObjectAnimator.ofFloat(fButton, "Y", fButton.getY() , firstButtonLocY);
        fButtonAnimY.setDuration(1800);

        ObjectAnimator aButtonFastAnimX = ObjectAnimator.ofFloat(aButtonFast, "X", aButtonFast.getX(), firstButtonLocX + (imageWidth));
        aButtonFastAnimX.setDuration(1700);
        ObjectAnimator aButtonFastAnimY = ObjectAnimator.ofFloat(aButtonFast, "Y", aButtonFast.getY(), firstButtonLocY );
        aButtonFastAnimY.setDuration(1700);

        ObjectAnimator sButtonAnimX = ObjectAnimator.ofFloat(sButton, "X", sButton.getX(), firstButtonLocX + (imageWidth * 2));
        sButtonAnimX.setDuration(1600);
        ObjectAnimator sButtonAnimY = ObjectAnimator.ofFloat(sButton, "Y", sButton.getY(), firstButtonLocY);
        sButtonAnimY.setDuration(1600);

        ObjectAnimator tFastButtonAnimX = ObjectAnimator.ofFloat(tButtonFast, "X", tButtonFast.getX(), firstButtonLocX + (imageWidth * 3));
        tFastButtonAnimX.setDuration(1500);
        ObjectAnimator tFastButtonAnimY = ObjectAnimator.ofFloat(tButtonFast, "Y", tButtonFast.getY(), firstButtonLocY);
        tFastButtonAnimY.setDuration(1500);

        /*      *       *       *       *       *       *       *       *      *       *       *       *       *       *        */




        /* Creating all the animations for MATH     *       *       *       *     *       *       *     *       *       *       */

        ObjectAnimator mButtonAnimX = ObjectAnimator.ofFloat(mButton, "X", mButton.getX(), firstButtonLocX);
        mButtonAnimX.setDuration(1500);
        ObjectAnimator mButtonAnimY = ObjectAnimator.ofFloat(mButton, "Y", mButton.getY(), firstButtonLocY + (imageWidth));
        mButtonAnimY.setDuration(1500);

        ObjectAnimator aMathButtonAnimX = ObjectAnimator.ofFloat(aButtonMath, "X", aButtonMath.getX(), firstButtonLocX + (imageWidth));
        aMathButtonAnimX.setDuration(1600);
        ObjectAnimator aMathButtonAnimY = ObjectAnimator.ofFloat(aButtonMath, "Y", aButtonMath.getY(), firstButtonLocY + (imageWidth));
        aMathButtonAnimY.setDuration(1600);

        ObjectAnimator tMathButtonAnimX = ObjectAnimator.ofFloat(tButtonMath, "X", tButtonMath.getX(), firstButtonLocX + (imageWidth * 2));
        tMathButtonAnimX.setDuration(1700);
        ObjectAnimator tMathButtonAnimY = ObjectAnimator.ofFloat(tButtonMath, "Y", tButtonMath.getY(), firstButtonLocY + (imageWidth));
        tMathButtonAnimY.setDuration(1700);

        ObjectAnimator hButtonAnimX = ObjectAnimator.ofFloat(hButton, "X", hButton.getX(), firstButtonLocX + (imageWidth * 3));
        hButtonAnimX.setDuration(1800);
        ObjectAnimator hButtonAnimY = ObjectAnimator.ofFloat(hButton, "Y", hButton.getY(), firstButtonLocY + (imageWidth));
        hButtonAnimY.setDuration(1800);

        /*      *       *       *       *       *       *       *       *      *       *       *       *       *       *        */

        ObjectAnimator appsAlpha = ObjectAnimator.ofFloat(otApps, "Alpha", 1f);
        appsAlpha.setDuration(1000);


        animatorThreadSet.play(fButtonAnimX).with(fButtonAnimY);
        animatorThreadSet.play(aButtonFastAnimX).with(aButtonFastAnimY);
        animatorThreadSet.play(sButtonAnimX).with(sButtonAnimY);
        animatorThreadSet.play(tFastButtonAnimX).with(tFastButtonAnimY);
        animatorThreadSet.play(mButtonAnimX).with(mButtonAnimY);
        animatorThreadSet.play(aMathButtonAnimX).with(aMathButtonAnimY);
        animatorThreadSet.play(tMathButtonAnimX).with(tMathButtonAnimY);
        animatorThreadSet.play(hButtonAnimX).with(hButtonAnimY);
        animatorThreadSet.play(appsAlpha).after(1000);


        animatorThreadSet.start();
    }




}