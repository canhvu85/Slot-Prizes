package com.example.nick.slotprizes;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class u extends AppCompatActivity {


    Button btn;
    Boolean a = false;
    Random r;
    ImageView img1, img2, img3, img4, img5;

    int img11, img22, img33, img44, img55;

    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        btn = findViewById(R.id.btn2);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img5 = findViewById(R.id.img);
        img4 = findViewById(R.id.img4);

        msg = findViewById(R.id.msg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                img1.setImageResource(R.drawable.anim);
                final AnimationDrawable img1anim = (AnimationDrawable) img1.getDrawable();
                img1anim.start();


                img2.setImageResource(R.drawable.anim);
                final AnimationDrawable img2anim = (AnimationDrawable) img2.getDrawable();
                img2anim.start();

                img3.setImageResource(R.drawable.anim);
                final AnimationDrawable img3anim = (AnimationDrawable) img3.getDrawable();
                img3anim.start();

                img4.setImageResource(R.drawable.anim);
                final AnimationDrawable img4anim = (AnimationDrawable) img4.getDrawable();
                img4anim.start();

                img5.setImageResource(R.drawable.anim);
                final AnimationDrawable img5anim = (AnimationDrawable) img5.getDrawable();
                img5anim.start();

                Handler hh = new Handler();

                hh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        img1anim.stop();
                        img2anim.stop();
                        img3anim.stop();
                        img4anim.stop();
                        img5anim.stop();

                        setImages();

                        getScore();


                    }
                }, 500);



            }
        });


    }
    public void setImages(){

        img11 = r.nextInt(6)+1;
        img22 = r.nextInt(6)+1;
        img33 = r.nextInt(6)+1;
        img44 = r.nextInt(6)+1;
        img55 = r.nextInt(6)+1;

        switch (img11){

            case 1:
                img1.setImageResource(R.drawable.slot1);
                break;
            case 2:
                img1.setImageResource(R.drawable.slot2);
                break;

            case 3:
                img1.setImageResource(R.drawable.slot3);
                break;
            case 4:
                img1.setImageResource(R.drawable.slot4);
                break;
            case 5:
                img1.setImageResource(R.drawable.slot5);
                break;
            case 6:
                img1.setImageResource(R.drawable.slot6);
                break;




        }
        switch (img22){

            case 1:
                img2.setImageResource(R.drawable.slot1);
                break;
            case 2:
                img2.setImageResource(R.drawable.slot2);
                break;

            case 3:
                img2.setImageResource(R.drawable.slot3);
                break;
            case 4:
                img2.setImageResource(R.drawable.slot4);
                break;
            case 5:
                img2.setImageResource(R.drawable.slot5);
                break;
            case 6:
                img2.setImageResource(R.drawable.slot6);
                break;




        }
        switch (img33){

            case 1:
                img3.setImageResource(R.drawable.slot1);
                break;
            case 2:
                img3.setImageResource(R.drawable.slot2);
                break;

            case 3:
                img3.setImageResource(R.drawable.slot3);
                break;
            case 4:
                img3.setImageResource(R.drawable.slot4);
                break;
            case 5:
                img3.setImageResource(R.drawable.slot5);
                break;
            case 6:
                img3.setImageResource(R.drawable.slot6);
                break;




        }
        switch (img44){

            case 1:
                img4.setImageResource(R.drawable.slot1);
                break;
            case 2:
                img4.setImageResource(R.drawable.slot2);
                break;

            case 3:
                img4.setImageResource(R.drawable.slot3);
                break;
            case 4:
                img4.setImageResource(R.drawable.slot4);
                break;
            case 5:
                img4.setImageResource(R.drawable.slot5);
                break;
            case 6:
                img4.setImageResource(R.drawable.slot6);
                break;




        }
        switch (img55){

            case 1:
                img5.setImageResource(R.drawable.slot1);
                break;
            case 2:
                img5.setImageResource(R.drawable.slot2);
                break;

            case 3:
                img5.setImageResource(R.drawable.slot3);
                break;
            case 4:
                img5.setImageResource(R.drawable.slot4);
                break;
            case 5:
                img5.setImageResource(R.drawable.slot5);
                break;
            case 6:
                img5.setImageResource(R.drawable.slot6);
                break;




        }


    }

    public void getScore(){

        if(img11 == img22 && img22 == img33 && img33 == img44 && img44 == img55)
        {
            msg.setText("You Won");

        }
        else
        {


            msg.setText("You lost");
        }





    }
}
























