package com.example.nick.slotprizes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view2);
        lottieAnimationView.setImageAssetsFolder("images/");

        lottieAnimationView.setAnimation("animslot.json");
        lottieAnimationView.loop(false);
        lottieAnimationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                if(!isConnected(LoadingScreen.this)) buildDialog(LoadingScreen.this).show();
                else {
                    Toast.makeText(LoadingScreen.this,"Welcome", Toast.LENGTH_SHORT).show();
                    Intent n = new Intent(LoadingScreen.this, MainActivity.class);
                    startActivity(n);
                    finish();
                }
                /**
                Intent i = new Intent(LoadingScreen.this, MainActivity.class);
                startActivity(i);
                 */

                // Fecha esta activity
                //finish();
            }
        }, 4500);
    }
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You are not connected to the internet. Connect to continue.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

}
