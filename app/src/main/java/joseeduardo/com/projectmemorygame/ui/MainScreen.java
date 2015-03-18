package joseeduardo.com.projectmemorygame.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import joseeduardo.com.projectmemorygame.BuildConfig;
import joseeduardo.com.projectmemorygame.R;


public class MainScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Version
        String version = BuildConfig.VERSION_NAME;
        TextView version1 = (TextView) findViewById(R.id.versionTextView);
        version1.setText(version);

        ImageView startGameButton = (ImageView) findViewById(R.id.startGame);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startGameActivity();
            }
        });

    }

    private void startGameActivity() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }



}
