package com.muneiah.example.notificationwithworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
NotificationCompat.Builder builder;
NotificationManager nm;
public static final int NOTIFICATION_ID=12123123;
public static final String NOTIFICATION_CHNNEL_ID="MYCHANNEL";
PendingIntent pi;
Intent i;
OneTimeWorkRequest onetime;
PeriodicWorkRequest secondtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onetime=new OneTimeWorkRequest.Builder(FirstWork.class).build();
        secondtime=new PeriodicWorkRequest.Builder(SecondWork.class,2, TimeUnit.MINUTES).build();

        /*i=new Intent(this,MainActivity.class);
        pi=PendingIntent.getActivity(this,33,i,PendingIntent.FLAG_UPDATE_CURRENT);
        nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createChannel();*/
    }

   /* private void createChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel nc=new NotificationChannel(NOTIFICATION_CHNNEL_ID,"apsddc",NotificationManager.IMPORTANCE_HIGH);
            nc.enableVibration(true);
            nc.enableLights(true);
            nc.setLightColor(Color.BLUE);
            nm.createNotificationChannel(nc);
        }
    }*/

    public void sendNotification(View view) {
        WorkManager.getInstance(this).enqueue(onetime);
        WorkManager.getInstance(this).enqueue(secondtime);
        /*builder=new NotificationCompat.Builder(this,NOTIFICATION_CHNNEL_ID);
        builder.setContentTitle("My Notification");
        builder.setContentText("Don't Sleep in the class room ");
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentIntent(pi);
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.addAction(R.drawable.ic_baseline_reply_24,"Reply",pi);
        nm.notify(NOTIFICATION_ID,builder.build());
*/
    }
    /*for notification  design 3 components
    *   1.Icon
    * 2.Text
    * 3.Message *
    For Notification creation Classes  reqvured ?
    * 3Classes
    * NoticationCompact
    * NotficationManger
    * NotificationChannel */
}