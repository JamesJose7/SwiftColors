package joseeduardo.com.projectmemorygame.ui;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import joseeduardo.com.projectmemorygame.R;

public class HowToPlay extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.close_tutorial)
    public void closeTutorial() {
        finish();
    }

}
