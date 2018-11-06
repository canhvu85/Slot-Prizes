package com.example.nick.slotprizes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BackgroundShop extends AppCompatActivity {


    private Button btn;
    private MainActivity mn;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_shop);


        btn = findViewById(R.id.change);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstraintLayout ct = findViewById(R.id.activity_main);




                SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                int bg = sharedPref.getInt("acitivity_main", R.drawable.bglegendary); // the second parameter will be fallback if the preference is not found
                ct.setBackgroundResource(bg);
                btn.setText("Success");


                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent n = new Intent(BackgroundShop.this, MainActivity.class);
                        startActivity(n);
                    }
                },500);

            }
        });


    }
}
