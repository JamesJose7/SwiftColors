package joseeduardo.com.projectmemorygame.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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
import joseeduardo.com.projectmemorygame.model.Timer;
import joseeduardo.com.projectmemorygame.model.RoundDrawable;
import joseeduardo.com.projectmemorygame.model.ScoreBoard;

public class Game extends Activity implements Timer.TimerListener {

    public static final String PREFS_KEY = "prefsKey";
    public static final String HIGH_SCORE = "highScore";
    public static final String SCORE = "score";

    private int mCurrentDuration;

    private Asker mAsker;
    private ScoreBoard mScoreBoard;
    protected Timer mTimer;

    @BindView(R.id.askerTextView) TextView mAskerTextView;
    @BindView(R.id.scoreTextView) TextView mScoreTextView;
    @BindView(R.id.timeTextView) TextView mTimeTextView;

    private int mNewScore;
    private int mSavedScore;
    private boolean mBlackMode = false;

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
        mCurrentDuration = 6;
        mTimer  = new Timer(mCurrentDuration, this);

        //Version
        String version = BuildConfig.VERSION_NAME;
        versionText.setText(version);

        setAsker();
        mTimer.start();
        shuffleTilesAndSetColor();

        // Get the previous highscore
        SharedPreferences prefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
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

            changeDifficulty();

            mTimer.cancelTimer();
            mTimer = new Timer(mCurrentDuration, this);
            mTimer.start();
            shuffleTilesAndSetColor();
        } else {
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

        return "";
    }

    private void shuffleTilesAndSetColor() {
        RoundDrawable[] tilesArray;
        if (!mBlackMode)
            tilesArray = RoundDrawable.getAllExceptBlack();
        else
            tilesArray = RoundDrawable.values();

        Button[] buttons = {blueButton, greenButton, limeGreenButton, orangeButton, purpleButton,
            redButton, skyblueButton, yellowButton};

        List<RoundDrawable> tilesList =  Arrays.asList(tilesArray);
        Collections.shuffle(tilesList);

        for (int i = 0; i < buttons.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                buttons[i].setBackground(getDrawable(tilesList.get(i).getId()));
            } else {
                buttons[i].setBackground(getResources().getDrawable(tilesList.get(i).getId()));
            }
        }

    }

    public void  setAsker() {
        mAskerTextView.setText(mAsker.getColorText());
        mAskerTextView.setBackgroundColor(mAsker.getColor());
    }

    public boolean checkAnswers(String answer, String askerDisplay) {
        if (answer.equals(askerDisplay) || answer.equals(RoundDrawable.BLACK.getColorName()))
            return true;
        return false;
    }

    public void startLostScreenActivity(int score) {
        mTimer.cancelTimer();
        Intent intent = new Intent(this, LostScreen.class);
        intent.putExtra(SCORE, score);
        startActivity(intent);
        finish();
    }

    public void updateScore() {
        mNewScore = mScoreBoard.increaseScore();
        mScoreTextView.setText(mNewScore + "");
    }

    public void getHighScore() {
        SharedPreferences prefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (mNewScore > mSavedScore) {
            int highScore = mNewScore;
            editor.putInt(HIGH_SCORE, highScore);
            editor.apply();
        }
    }

    private void changeDifficulty() {
        switch (mNewScore) {
            case 8:
                //medium
                mCurrentDuration = 4;
                break;
            case 16:
                //hard
                mCurrentDuration = 2;
                break;
            case 24:
                //try this
                mBlackMode = true;
                break;
            default:
        }
    }

    @Override
    public void onFinish() {
        getHighScore();
        startLostScreenActivity(mNewScore);
    }

    @Override
    public void onTick() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTimeTextView.setText(mTimer.getSeconds() + "");
            }
        });
    }
}

