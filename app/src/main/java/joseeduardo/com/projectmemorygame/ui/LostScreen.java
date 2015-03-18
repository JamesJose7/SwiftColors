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

import joseeduardo.com.projectmemorygame.BuildConfig;
import joseeduardo.com.projectmemorygame.R;
import joseeduardo.com.projectmemorygame.model.ScoreBoard;

public class LostScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_screen);

        //Version
        String version = BuildConfig.VERSION_NAME;
        TextView version1 = (TextView) findViewById(R.id.versionTextView);
        version1.setText(version);

        TextView lastScoreTextView = (TextView) findViewById(R.id.lastScoreTextView);
        TextView highScoreTextView = (TextView) findViewById(R.id.highScoreTextView);
        ImageView retryButton = (ImageView) findViewById(R.id.retryButton);

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
