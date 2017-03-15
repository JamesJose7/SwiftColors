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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import joseeduardo.com.projectmemorygame.BuildConfig;
import joseeduardo.com.projectmemorygame.R;
import joseeduardo.com.projectmemorygame.model.Asker;
import joseeduardo.com.projectmemorygame.model.ScoreBoard;

public class Game extends Activity {

    public static final String PREFS_KEY = "prefsKey";
    public static final String HIGH_SCORE = "highScore";

    private Asker mAsker = new Asker();
    private ScoreBoard mScoreBoard = new ScoreBoard();
    final Timer mTimer = new Timer(3000, 1000);

    @BindView(R.id.askerTextView) TextView mAskerTextView;
    @BindView(R.id.scoreTextView) TextView mScoreTextView;
    @BindView(R.id.timeTextView) TextView mTimeTextView;

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

    @BindView(R.id.redButton) Button redButton;
    @BindView(R.id.yellowButton) Button yellowButton;
    @BindView(R.id.greenButton) Button greenButton;
    @BindView(R.id.blueButton) Button blueButton;
    @BindView(R.id.orangeButton) Button orangeButton;
    @BindView(R.id.purpleButton) Button purpleButton;
    @BindView(R.id.skyblueButton) Button skyblueButton;
    @BindView(R.id.limegreenButton) Button limeGreenButton;
    @BindView(R.id.versionTextGame) TextView versionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

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
        versionText.setText(version);

        setAsker();
        mTimer.start();
        shuffleTilesAndSetColor();

        yellowButton.getDrawableState();

        // Get the previous highscore
        SharedPreferences prefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        mSavedScore = prefs.getInt(HIGH_SCORE, 0);
    }

    @OnClick(R.id.redButton)
    public void redButton(View view) {
        //String answer = "RED";
        checkButtonsAnswer(view.getBackground());
    }

    @OnClick(R.id.yellowButton)
    public void yellowButton(View view) {
        //String answer = "YELLOW";
        checkButtonsAnswer(view.getBackground());
    }

    @OnClick(R.id.greenButton)
    public void greenButton(View view) {
        //String answer = "GREEN";
        checkButtonsAnswer(view.getBackground());
    }

    @OnClick(R.id.blueButton)
    public void blueButton(View view) {
        //String answer = "BLUE";
        checkButtonsAnswer(view.getBackground());
    }

    @OnClick(R.id.orangeButton)
    public void orangeButton(View view) {
        //String answer = "ORANGE";
        checkButtonsAnswer(view.getBackground());
    }

    @OnClick(R.id.purpleButton)
    public void purpleButton(View view) {
        //String answer = "PURPLE";
        checkButtonsAnswer(view.getBackground());
    }

    @OnClick(R.id.skyblueButton)
    public void skyBlueButton(View view) {
        //String answer = "SKYBLUE";
        checkButtonsAnswer(view.getBackground());
    }

    @OnClick(R.id.limegreenButton)
    public void limeGreenButton(View view) {
        //String answer = "LIMEGREEN";
        checkButtonsAnswer(view.getBackground());
    }

    private void checkButtonsAnswer(Drawable background) {
        String askerDisplay = mAskerTextView.getText().toString();
        String answer = getDrawableStringName(background);

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

