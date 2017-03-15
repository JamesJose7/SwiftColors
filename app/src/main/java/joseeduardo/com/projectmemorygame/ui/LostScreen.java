package joseeduardo.com.projectmemorygame.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import joseeduardo.com.projectmemorygame.BuildConfig;
import joseeduardo.com.projectmemorygame.R;
import joseeduardo.com.projectmemorygame.model.ScoreBoard;

public class LostScreen extends Activity {

    @BindView(R.id.versionTextLost) TextView versionTextView;
    @BindView(R.id.lastScoreTextView) TextView lastScoreTextView;
    @BindView(R.id.highScoreTextView) TextView highScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_screen);
        ButterKnife.bind(this);

        //Version
        String version = BuildConfig.VERSION_NAME;
        versionTextView.setText(version);

        // Get score from previous activity
        Intent intent = getIntent();
        String score = intent.getStringExtra(Game.SCORE);
        lastScoreTextView.setText(score);

        SharedPreferences prefs = this.getSharedPreferences(Game.PREFS_KEY, Context.MODE_PRIVATE);
        int highScore = prefs.getInt(Game.HIGH_SCORE, 0);

        highScoreTextView.setText(highScore + "");

    }

    @OnClick(R.id.retryButton)
    public void retryGame() {
        startGameActivity();
    }

    public void startGameActivity() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

}
