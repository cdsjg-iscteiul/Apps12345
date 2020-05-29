package com.example.apps.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.apps.R;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"test").setSmallIcon(R.drawable.ic_warning)
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText(intent.getStringExtra("description"))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(intent.getIntExtra("key",0), builder.build());
    }
}
