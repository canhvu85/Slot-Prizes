package com.example.nick.slotprizes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class GoldBoxSecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_box_second);
        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim444);
        lottieAnimationView.setImageAssetsFolder("images/");

        //lottieAnimationView.setVisibility(View.VISIBLE);

        lottieAnimationView.setAnimation("epicfirst.json");

        lottieAnimationView.loop(false);


        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                lottieAnimationView.playAnimation();
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent n = new Intent(GoldBoxSecond.this, MainActivity.class);
                        startActivity(n);
                        finish();





                    }
                },5500);


            }
        },1000);




    }

    @Override
    public void onBackPressed() {

    }
}
