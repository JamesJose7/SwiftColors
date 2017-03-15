package joseeduardo.com.projectmemorygame.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import joseeduardo.com.projectmemorygame.model.RoundDrawable;
import joseeduardo.com.projectmemorygame.model.ScoreBoard;

public class Game extends Activity {

    public static final String PREFS_KEY = "prefsKey";
    public static final String HIGH_SCORE = "highScore";
    public static final String SCORE = "score";

    private Asker mAsker;
    private ScoreBoard mScoreBoard;
    protected Timer mTimer;

    @BindView(R.id.askerTextView) TextView mAskerTextView;
    @BindView(R.id.scoreTextView) TextView mScoreTextView;
    @BindView(R.id.timeTextView) TextView mTimeTextView;

    private String mNewScore;
    private int mSavedScore;

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

        mAsker = new Asker();
        mScoreBoard  = new ScoreBoard();
        mTimer  = new Timer(3000, 1000);

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
        checkButtonsAnswer(view);
    }

    @OnClick(R.id.yellowButton)
    public void yellowButton(View view) {
        //String answer = "YELLOW";
        checkButtonsAnswer(view);
    }

    @OnClick(R.id.greenButton)
    public void greenButton(View view) {
        //String answer = "GREEN";
        checkButtonsAnswer(view);
    }

    @OnClick(R.id.blueButton)
    public void blueButton(View view) {
        //String answer = "BLUE";
        checkButtonsAnswer(view);
    }

    @OnClick(R.id.orangeButton)
    public void orangeButton(View view) {
        //String answer = "ORANGE";
        checkButtonsAnswer(view);
    }

    @OnClick(R.id.purpleButton)
    public void purpleButton(View view) {
        //String answer = "PURPLE";
        checkButtonsAnswer(view);
    }

    @OnClick(R.id.skyblueButton)
    public void skyBlueButton(View view) {
        //String answer = "SKYBLUE";
        checkButtonsAnswer(view);
    }

    @OnClick(R.id.limegreenButton)
    public void limeGreenButton(View view) {
        //String answer = "LIMEGREEN";
        checkButtonsAnswer(view);
    }

    private void checkButtonsAnswer(View view) {
        String askerDisplay = mAskerTextView.getText().toString();
        String answer = getDrawableStringName((Button) view);

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

    private String getDrawableStringName(Button button) {
        for (RoundDrawable roundDrawable : RoundDrawable.values()) {
            if (button.getBackground().getConstantState().equals
                    (getResources().getDrawable(roundDrawable.getId()).getConstantState()))
                return roundDrawable.getColorName();
        }

        return null;
    }

    private void shuffleTilesAndSetColor() {

        RoundDrawable[] tilesArray = RoundDrawable.values();

        List<RoundDrawable> tilesList =  Arrays.asList(tilesArray);
        Collections.shuffle(tilesList);

        blueButton.setBackground(getResources().getDrawable(tilesArray[0].getId()));
        greenButton.setBackground(getResources().getDrawable(tilesArray[1].getId()));
        limeGreenButton.setBackground(getResources().getDrawable(tilesArray[2].getId()));
        orangeButton.setBackground(getResources().getDrawable(tilesArray[3].getId()));
        purpleButton.setBackground(getResources().getDrawable(tilesArray[4].getId()));
        redButton.setBackground(getResources().getDrawable(tilesArray[5].getId()));
        skyblueButton.setBackground(getResources().getDrawable(tilesArray[6].getId()));
        yellowButton.setBackground(getResources().getDrawable(tilesArray[7].getId()));
    }

    public void setAsker() {
        mAskerTextView.setText(mAsker.getColorText());
        mAskerTextView.setBackgroundColor(mAsker.getColor());
    }

    public boolean checkAnswers(String answer, String askerDisplay) {
        if (answer.equals(askerDisplay))
            return true;
        return false;
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
        intent.putExtra(SCORE, score);
        startActivity(intent);
    }

    public void updateScore() {
        mNewScore = (mScoreBoard.increaseScore()) + "";
        mScoreTextView.setText(mNewScore);
    }

    public void getHighScore() {

        SharedPreferences prefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (Integer.parseInt(mNewScore) > mSavedScore) {
            int highScore = Integer.parseInt(mNewScore);
            editor.putInt(HIGH_SCORE, highScore);
            editor.apply();
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

