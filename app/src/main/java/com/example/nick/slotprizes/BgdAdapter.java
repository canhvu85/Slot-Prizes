package com.example.nick.slotprizes;

import android.content.Context;

import android.content.Intent;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by nick on 1/25/18.
 */

public class BgdAdapter extends PagerAdapter {

    List<Integer> imgss;
    Context context;
    LayoutInflater layoutInflater;
    String aaa;

    public BgdAdapter(List<Integer> img, Context context){
        this.imgss = img;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }



    @Override
    public int getCount() {
        return imgss.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = layoutInflater.inflate(R.layout.card_item,container,false);
        final ImageView imgv = (ImageView)view.findViewById(R.id.imgRoll);
        imgv.setImageResource(imgss.get(position));

        //getCurrentList(imgss.get(position));







        Log.v(aaa,"position" + position);


        container.addView(view);
        return view;
    }




}
