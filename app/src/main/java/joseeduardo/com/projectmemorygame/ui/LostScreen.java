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
import joseeduardo.com.projectmemorygame.BuildConfig;
import joseeduardo.com.projectmemorygame.R;
import joseeduardo.com.projectmemorygame.model.ScoreBoard;

public class LostScreen extends Activity {

    @BindView(R.id.versionTextLost) TextView versionTextView;
    @BindView(R.id.lastScoreTextView) TextView lastScoreTextView;
    @BindView(R.id.highScoreTextView) TextView highScoreTextView;
    @BindView(R.id.retryButton) ImageView retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_screen);
        ButterKnife.bind(this);

        //Version
        String version = BuildConfig.VERSION_NAME;
        versionTextView.setText(version);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity();
            }
        });

        // Get score from previous activity
        Intent intent = getIntent();
        String score = intent.getStringExtra("score");
        lastScoreTextView.setText(score);


        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        int highScore = prefs.getInt("highScore", 0);


        highScoreTextView.setText(highScore + "");

    }

    public void startGameActivity() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

}
