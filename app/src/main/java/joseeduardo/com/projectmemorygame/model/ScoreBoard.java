package joseeduardo.com.projectmemorygame.model;

import android.content.SharedPreferences;

/**
 * Created by Jose on 18/01/2015.
 */
public class ScoreBoard {

    private int mCurrentScore = 0;

    public int increaseScore() {
        mCurrentScore++;
        return mCurrentScore;
    }
}
