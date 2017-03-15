package joseeduardo.com.projectmemorygame.model;

import android.content.res.AssetManager;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by Jose on 18/01/2015.
 */
public class Asker {

    private Random randomGenerator;
    private String[] mColorText = {
            "Red",
            "Yellow",
            "Blue",
            "Green",
            "Orange",
            "Purple",
            "Sky Blue",
            "Lime"
    };
    private String[] mColor = {
            "#ff0000ff",
            "#ff009900",
            "#ffC2FF1F",
            "#ffFFA500",
            "#ff800080",
            "#ffff0000",
            "#ff87CEEB",
            "#ffffdd00"
    };

    public Asker() {
        randomGenerator = new Random();
    }

    public int getColor() {
        String color;

        int randomNumber = randomGenerator.nextInt(mColor.length);

        color = mColor[randomNumber];
        return Color.parseColor(color);
    }

    public String getColorText() {
        String color;

        int randomNumber = randomGenerator.nextInt(mColorText.length);

        color = mColorText[randomNumber];

        return color;
    }
}
