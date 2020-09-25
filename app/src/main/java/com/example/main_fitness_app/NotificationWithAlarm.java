package com.example.main_fitness_app;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationWithAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder bulider= new NotificationCompat.Builder(context,"notifyApp").setSmallIcon(
                R.drawable.ic_notifications_)
                .setContentTitle("Notification")
                .setContentText("This is a Notification For You").setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
        notificationManager.notify(200,bulider.build());
    }

}
