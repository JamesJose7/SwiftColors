package joseeduardo.com.projectmemorygame.model;

import joseeduardo.com.projectmemorygame.R;

/**
 * Created by jeeps on 3/15/2017.
 */

public enum RoundDrawable {
    BLUE("Blue", R.drawable.blueroundedbutton),
    GREEN("Green", R.drawable.greenroundedbutton),
    LIME("Lime", R.drawable.limegreenroundedbutton),
    ORANGE("Orange", R.drawable.orangeroundedbutton),
    PURPLE("Purple", R.drawable.purpleroundedbutton),
    RED("Red", R.drawable.redroundedbutton),
    SKY_BLUE("Sky Blue", R.drawable.skyblueroundedbutton),
    YELLOW("Yellow", R.drawable.yellowroundedbutton);

    private String colorName;
    private int id;

    RoundDrawable(String colorName, int id) {
        this.colorName = colorName;
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public int getId() {
        return id;
    }
}
