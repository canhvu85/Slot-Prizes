package com.example.nick.slotprizes;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nick on 12/30/17.
 */

public class SlotPrizes extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }




}
