package com.example.nick.slotprizes;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class Silverone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silverone);





        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.anim444444);
        lottieAnimationView.setImageAssetsFolder("images/");

        //lottieAnimationView.setVisibility(View.VISIBLE);

        lottieAnimationView.setAnimation("silveranim.json");

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

                        Intent n = new Intent(Silverone.this, MainActivity.class);
                        startActivity(n);
                        finish();




                    }
                },5500);


            }
        },1000);




    }

    @Override
    public void onBackPressed() {

        int a;

        Random r = new Random();
        a = r.nextInt(100);

        if(a < 50 ){

            Toast.makeText(this, "Pressing back button!", Toast.LENGTH_SHORT).show();

        }


    }
}
