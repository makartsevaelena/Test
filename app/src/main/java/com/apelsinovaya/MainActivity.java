package com.apelsinovaya;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    TextView textView_text;
    float x;
    float y;
    String lang;
    TranslateAnimation falling_down_anim;
    TranslateAnimation reverse_anim;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout) findViewById(R.id.const_layout);
        textView_text = (TextView) findViewById(R.id.textview_text);
        textView_text.setText(R.string.text);
        lang = Locale.getDefault().getLanguage();

        falling_down_anim = (TranslateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.falling_down);
        reverse_anim = (TranslateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.reverse);

        textView_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_text.getAnimation().cancel();
                textView_text.clearAnimation();
                falling_down_anim.setAnimationListener(null);

            }
        });

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x = motionEvent.getX();
                y = motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    textView_text.setX(x);
                    textView_text.setY(y);
                    if (lang.equals("ru")) {
                        textView_text.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));

                    } else if (lang.equals("en")) {
                        textView_text.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    } else {
                        textView_text.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                    startFallingDownAnim();
                }

                return true;
            }
        });
    }

    private void startFallingDownAnim() {
        textView_text.startAnimation(falling_down_anim);
        falling_down_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == falling_down_anim) {
                    startReverseAnim();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startReverseAnim() {
        textView_text.startAnimation(reverse_anim);

    }

}