package joseeduardo.com.projectmemorygame.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import joseeduardo.com.projectmemorygame.BuildConfig;
import joseeduardo.com.projectmemorygame.R;
import joseeduardo.com.projectmemorygame.model.Asker;
import joseeduardo.com.projectmemorygame.model.ScoreBoard;

public class Game extends Activity {

    private Asker mAsker = new Asker();
    private ScoreBoard mScoreBoard = new ScoreBoard();
    final Timer mTimer = new Timer(3000, 1000);

    private TextView mAskerTextView;
    private TextView mScoreTextView;
    private TextView mTimeTextView;

    private String mNewScore;
    private int mSavedScore;

    private Drawable blueTile;
    private Drawable greenTile;
    private Drawable limeTile;
    private Drawable orangeTile;
    private Drawable purpleTile;
    private Drawable redTile;
    private Drawable skyBlueTile;
    private Drawable yellowTile;

    private Button redButton;
    private Button yellowButton;
    private Button greenButton;
    private Button blueButton;
    private Button orangeButton;
    private Button purpleButton;
    private Button skyblueButton;
    private Button limeGreenButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //All them drawables
        blueTile = getResources().getDrawable(R.drawable.blueroundedbutton);
        greenTile = getResources().getDrawable(R.drawable.greenroundedbutton);
        limeTile = getResources().getDrawable(R.drawable.limegreenroundedbutton);
        orangeTile = getResources().getDrawable(R.drawable.orangeroundedbutton);
        purpleTile = getResources().getDrawable(R.drawable.purpleroundedbutton);
        redTile = getResources().getDrawable(R.drawable.redroundedbutton);
        skyBlueTile = getResources().getDrawable(R.drawable.skyblueroundedbutton);
        yellowTile = getResources().getDrawable(R.drawable.yellowroundedbutton);


        //Version
        String version = BuildConfig.VERSION_NAME;
        TextView version1 = (TextView) findViewById(R.id.versionTextView);
        version1.setText(version);

        mAskerTextView = (TextView) findViewById(R.id.askerTextView);
        mScoreTextView = (TextView) findViewById(R.id.scoreTextView);
        mTimeTextView = (TextView) findViewById(R.id.timeTextView);

        redButton = (Button) findViewById(R.id.redButton);
        yellowButton = (Button) findViewById(R.id.yellowButton);
        greenButton = (Button) findViewById(R.id.greenButton);
        blueButton = (Button) findViewById(R.id.blueButton);
        orangeButton = (Button) findViewById(R.id.orangeButton);
        purpleButton = (Button) findViewById(R.id.purpleButton);
        skyblueButton = (Button) findViewById(R.id.skyblueButton);
        limeGreenButton = (Button) findViewById(R.id.limegreenButton);

        setAsker();
        mTimer.start();
        shuffleTilesAndSetColor();

        yellowButton.getDrawableState();

        // Get the previous highscore
        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        mSavedScore = prefs.getInt("highScore", 0);

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "RED";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(redButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    //Increase score
                    updateScore();

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "YELLOW";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(yellowButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    // Increase score
                    updateScore();

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "GREEN";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(greenButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    // Increase Score
                    updateScore();

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "BLUE";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(blueButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    // Increase Score
                    updateScore();

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "ORANGE";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(orangeButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    // Increase Score
                    updateScore();

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

        purpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "PURPLE";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(purpleButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    // Increase Score
                    updateScore();

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

        skyblueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "SKYBLUE";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(skyblueButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    // Increase Score
                    updateScore();

                    skyblueButton.setBackgroundColor(Color.parseColor("#ffffff"));

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

        limeGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "LIMEGREEN";
                String askerDisplay = mAskerTextView.getText().toString();
                String answer = getDrawableStringName(limeGreenButton.getBackground());

                if (checkAnswers(answer, askerDisplay)) {
                    setAsker();
                    // Increase Score
                    updateScore();

                    mTimer.cancel();
                    mTimer.start();
                    shuffleTilesAndSetColor();
                } else {
                    checkEmptyScore();
                    getHighScore();
                    startLostScreenActivity(mNewScore);
                }
            }
        });

    }

    private String getDrawableStringName(Drawable drawable) {

        String color = "";

        if(drawable == blueTile) {
            color = "Blue";
        }
        else if (drawable == greenTile) {
            color = "Green";
        }
        else if (drawable == limeTile) {
            color = "Lime";
        }
        else if (drawable == orangeTile) {
            color = "Orange";
        }
        else if (drawable == purpleTile) {
            color = "Purple";
        }
        else if (drawable == redTile) {
            color = "Red";
        }
        else if (drawable == skyBlueTile) {
            color = "Sky Blue";
        }
        else if (drawable == yellowTile) {
            color = "Yellow";
        }


        return color;
    }

    private void shuffleTilesAndSetColor() {

        Drawable[] tilesArray = {blueTile, greenTile, limeTile, orangeTile, purpleTile, redTile, skyBlueTile, yellowTile};

        List<Drawable> tilesList =  Arrays.asList(tilesArray);
        Collections.shuffle(tilesList);

        blueButton.setBackground(tilesArray[0]);
        greenButton.setBackground(tilesArray[1]);
        limeGreenButton.setBackground(tilesArray[2]);
        orangeButton.setBackground(tilesArray[3]);
        purpleButton.setBackground(tilesArray[4]);
        redButton.setBackground(tilesArray[5]);
        skyblueButton.setBackground(tilesArray[6]);
        yellowButton.setBackground(tilesArray[7]);
    }

    public void setAsker() {

        mAskerTextView.setText(mAsker.getColorText());
        mAskerTextView.setBackgroundColor(mAsker.getColor());
    }

    public boolean checkAnswers(String answer, String askerDisplay) {
        boolean result = false;
        if (answer == askerDisplay) {
            result = true;
        }

        return result;
    }

    public void checkEmptyScore() {
        if (mNewScore == null) {
            mNewScore = "0";
            mTimer.cancel();
        }
    }

    public void startLostScreenActivity(String score) {
        mTimer.cancel();
        Intent intent = new Intent(this, LostScreen.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    public void updateScore() {
        mNewScore = (mScoreBoard.increaseScore()) + "";
        mScoreTextView.setText(mNewScore);
    }

    public void getHighScore() {

        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (Integer.parseInt(mNewScore) > mSavedScore) {
            int highScore = Integer.parseInt(mNewScore);
            editor.putInt("highScore", highScore);
            editor.commit();
        }
    }


    public class Timer extends CountDownTimer {

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTimeTextView.setText(millisUntilFinished / 1000 + "");
        }

        @Override
        public void onFinish() {
            startLostScreenActivity(mNewScore);
            checkEmptyScore();
            getHighScore();
        }


    }





}

