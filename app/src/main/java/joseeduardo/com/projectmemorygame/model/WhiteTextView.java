package joseeduardo.com.projectmemorygame.model;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jeeps on 3/15/2017.
 */

public class WhiteTextView extends android.support.v7.widget.AppCompatTextView {
    public WhiteTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public WhiteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WhiteTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTextColor(Color.parseColor("#ffffff"));
            setAlpha(0.9F);
        }
    }


}
