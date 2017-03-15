package joseeduardo.com.projectmemorygame.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import joseeduardo.com.projectmemorygame.BuildConfig;
import joseeduardo.com.projectmemorygame.R;


public class MainScreen extends Activity {

    @BindView(R.id.versionTextView) TextView versionText;
    @BindView(R.id.startGame) ImageView startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ButterKnife.bind(this);

        //Version
        String version = BuildConfig.VERSION_NAME;
        versionText.setText(version);

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
