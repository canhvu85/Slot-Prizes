package com.example.nick.slotprizes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nick on 1/24/18.
 */

public class MainAdapter extends android.support.v7.widget.RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<String> myData;

    public MainAdapter(ArrayList<String> myData) {
        this.myData = myData;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;


    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

        holder.myTitle.setText(myData.get(position));
        //holder.myImg.setBackgroundResource(myData.get(position));

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView myTitle;
        public ImageView myImg;



        public ViewHolder(View itemView) {
            super(itemView);
            myTitle = itemView.findViewById(R.id.title);
            myImg = itemView.findViewById(R.id.imageView11);

        }
    }
}
