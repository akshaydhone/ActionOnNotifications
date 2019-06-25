package com.example.webcubic.androidnoti;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    private final String CHANNEL_ID="Personal notifications";
    private final int NOTIFICATION_ID=001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
                //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addNotification() {

        createNotificationChannel();

        Intent landingintent=new Intent(this,LandingActivity.class);
        landingintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent landingPendingIntent=PendingIntent.getActivity(this,0,landingintent,PendingIntent.FLAG_ONE_SHOT);

        Intent yesintent=new Intent(this,YesActivity.class);
        yesintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent YesPendingIntent=PendingIntent.getActivity(this,0,yesintent,PendingIntent.FLAG_ONE_SHOT);



        Intent nointent=new Intent(this,NoActivity.class);
        nointent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent NoPendingIntent=PendingIntent.getActivity(this,0,nointent,PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
                        builder.setSmallIcon(R.drawable.sent);
                builder.setContentTitle("Notifications Example");
                builder.setContentText("This is a test notification");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setAutoCancel(true);
                builder.setContentIntent(landingPendingIntent);
                builder.addAction(R.drawable.ic_action_name,"Accept",YesPendingIntent);
        builder.addAction(R.drawable.ic_action_name,"Decline",NoPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O);
        CharSequence names="Personal Notifications";
        String description="include all the personal notification";
        int importance=NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,names,importance);
notificationChannel.setDescription(description);
NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
notificationManager.createNotificationChannel(notificationChannel);

    }
}