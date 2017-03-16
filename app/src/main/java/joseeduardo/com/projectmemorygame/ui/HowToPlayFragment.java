package joseeduardo.com.projectmemorygame.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joseeduardo.com.projectmemorygame.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HowToPlayFragment extends Fragment {

    public HowToPlayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_how_to_play, container, false);
    }
}
