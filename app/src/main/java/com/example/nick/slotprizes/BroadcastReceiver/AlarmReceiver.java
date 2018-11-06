package com.example.nick.slotprizes.BroadcastReceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.nick.slotprizes.MainActivity;
import com.example.nick.slotprizes.R;

/**
 * Created by nick on 12/30/17.
 */

public class AlarmReceiver extends BroadcastReceiver{

    public void onReceive(Context context, Intent intent){

        long when = System.currentTimeMillis();

        NotificationManager nt = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent ni = new Intent(context, MainActivity.class);

        ni.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pi = PendingIntent.getActivity(context,0,ni,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Slot Prizes")
                .setContentText("Hey, are you feeling lucky today? Come play!")
                .setSound(alarm)
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pi)
                .setVibrate(new long[]{1000,1000,1000,1000,1000});

        nt.notify(0,builder.build());




























    }




}
