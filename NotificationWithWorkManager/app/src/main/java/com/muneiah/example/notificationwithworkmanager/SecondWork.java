package com.muneiah.example.notificationwithworkmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SecondWork extends Worker {
    NotificationManager nm;
    NotificationCompat.Builder builder;
    PendingIntent pi;
    Intent i;
    public SecondWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        i=new Intent(getApplicationContext(),MainActivity.class);
        pi=PendingIntent.getActivity(getApplicationContext(),33,i,PendingIntent.FLAG_UPDATE_CURRENT);
        nm= (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel();
        showNotification();
        return Result.success();
    }

    private void showNotification() {
        builder=new NotificationCompat.Builder(getApplicationContext(),MainActivity.NOTIFICATION_CHNNEL_ID);
        builder.setContentTitle("My Notification");
        builder.setContentText("Don't Sleep in the class room ");
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentIntent(pi);
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.addAction(R.drawable.ic_baseline_reply_24,"Reply",pi);
        nm.notify(MainActivity.NOTIFICATION_ID,builder.build());

    }

    private void createChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel nc=new NotificationChannel(MainActivity.NOTIFICATION_CHNNEL_ID,"apsddc",NotificationManager.IMPORTANCE_HIGH);
            nc.enableVibration(true);
            nc.enableLights(true);
            nc.setLightColor(Color.BLUE);
            nm.createNotificationChannel(nc);
        }

    }
}
