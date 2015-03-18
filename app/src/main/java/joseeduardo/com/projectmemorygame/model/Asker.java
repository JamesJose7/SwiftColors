package joseeduardo.com.projectmemorygame.model;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Jose on 18/01/2015.
 */
public class Asker {
    private String[] mColorText = {
            "RED",
            "YELLOW",
            "BLUE",
            "GREEN",
            "ORANGE",
            "PURPLE",
            "SKYBLUE",
            "LIMEGREEN"
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

    public int getColor() {

        String color = "";

        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColor.length);

        color = mColor[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

    public String getColorText() {

        String color = "";

        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColorText.length);

        color = mColorText[randomNumber];

        return color;
    }
}
