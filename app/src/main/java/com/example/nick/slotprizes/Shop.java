package com.example.nick.slotprizes;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    private TextView text_shop;

    private ImageView btn_bgd;

    private RecyclerView rView;
    private RecyclerView.LayoutManager myLayoutManager;
    private RecyclerView.Adapter myAdapter;

    private ArrayList<String> myData;

    List<Integer> imgs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        btn_bgd = findViewById(R.id.bgd_btn);
        btn_bgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(Shop.this, Bgdbuy.class);
                startActivity(n);
            }
        });












        text_shop = findViewById(R.id.textnormal);

        Typeface square = Typeface.createFromAsset(getAssets(), "fonts/Square.ttf");
        text_shop.setTypeface(square);





    }


}
