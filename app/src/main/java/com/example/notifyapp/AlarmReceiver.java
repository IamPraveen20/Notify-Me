package com.example.notifyapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(5000);


        int notification = intent.getIntExtra("notificationID",0);
        String message = intent.getStringExtra("todo");

      Intent intent1 = new Intent(context,MainActivity.class);
        PendingIntent contentintent =  PendingIntent.getActivity(context,0,intent1,0);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("It's Time")
        .setContentText(message)
        .setWhen(System.currentTimeMillis())
         .setAutoCancel(true)
         .setContentIntent(contentintent)
          .setPriority(Notification.PRIORITY_MAX)
            .setDefaults(Notification.DEFAULT_ALL);

        Uri ring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        Ringtone r = RingtoneManager.getRingtone(context,ring);
        r.play();


        manager.notify(notification,builder.build());
    }
}
