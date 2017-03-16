package joseeduardo.com.projectmemorygame.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import joseeduardo.com.projectmemorygame.R;


public class HowToPlay extends Activity {


    @BindView(R.id.page_bg)
    RelativeLayout pageBg;
    @BindView(R.id.page1)
    RelativeLayout page1;
    @BindView(R.id.page2)
    RelativeLayout page2;
    @BindView(R.id.page3)
    RelativeLayout page3;
    @BindView(R.id.page4)
    RelativeLayout page4;
    @BindView(R.id.page5)
    LinearLayout page5;

    @BindView(R.id.image1) ImageView page1Image;
    @BindView(R.id.image2) ImageView page2Image;
    @BindView(R.id.image3) ImageView page3Image;
    @BindView(R.id.image4) ImageView page4Image;

    @BindView(R.id.close_tutorial)
    ImageView closeTurotial;


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


    @OnClick(R.id.page_bg)
    public void toPage1() {
        page1.setAlpha(0);
        page1.setVisibility(View.VISIBLE);
        Picasso.with(this).load(R.drawable.page1).into(page1Image);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(page1, "alpha", 0, 1);

        AnimatorSet fade = new AnimatorSet();
        fade.playTogether(fadeIn);
        fade.setDuration(400);
        fade.start();
    }

    @OnClick(R.id.page1)
    public void toPage2() {
        page2.setAlpha(0);
        page2.setVisibility(View.VISIBLE);
        Picasso.with(this).load(R.drawable.page2).into(page2Image);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(page1, "alpha", 1, 0);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(page2, "alpha", 0, 1);

        AnimatorSet fade = new AnimatorSet();
        fade.playTogether(fadeOut, fadeIn);
        fade.setDuration(400);
        fade.start();

    }

    @OnClick(R.id.page2)
    public void toPage3() {
        page3.setAlpha(0);
        page3.setVisibility(View.VISIBLE);
        Picasso.with(this).load(R.drawable.page3).into(page3Image);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(page2, "alpha", 1, 0);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(page3, "alpha", 0, 1);

        AnimatorSet fade = new AnimatorSet();
        fade.playTogether(fadeOut, fadeIn);
        fade.setDuration(400);
        fade.start();

        page1.setVisibility(View.GONE);
    }

    @OnClick(R.id.page3)
    public void toPage4() {
        page4.setAlpha(0);
        page4.setVisibility(View.VISIBLE);
        Picasso.with(this).load(R.drawable.page4).into(page4Image);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(page3, "alpha", 1, 0);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(page4, "alpha", 0, 1);

        AnimatorSet fade = new AnimatorSet();
        fade.playTogether(fadeOut, fadeIn);
        fade.setDuration(400);
        fade.start();

        page2.setVisibility(View.GONE);
    }

    @OnClick(R.id.page4)
    public void toPage5() {
        pageBg.setVisibility(View.GONE);
        page5.setAlpha(0);
        page5.setVisibility(View.VISIBLE);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(page4, "alpha", 1, 0);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(page5, "alpha", 0, 1);

        AnimatorSet fade = new AnimatorSet();
        fade.playTogether(fadeOut, fadeIn);
        fade.setDuration(400);
        fade.start();

        page3.setVisibility(View.GONE);
    }

}
