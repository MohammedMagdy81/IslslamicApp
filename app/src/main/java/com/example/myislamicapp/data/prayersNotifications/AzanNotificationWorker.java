package com.example.myislamicapp.data.prayersNotifications;

import static android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION;

import static com.example.myislamicapp.data.prayersNotifications.AzanUtils.AZAN_CHANNEL_ID;
import static com.example.myislamicapp.data.prayersNotifications.AzanUtils.AZAN_CHANNEL_NAME;
import static com.example.myislamicapp.data.utils.Constant.AZAN_CONTENT_KEY;
import static com.example.myislamicapp.data.utils.Constant.AZAN_TITLE_KEY;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myislamicapp.R;

public class AzanNotificationWorker extends Worker {


    public AzanNotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public void sendNotification(String title, String body, Uri sound) {
        NotificationManager manager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationBuilder(title, body, sound);
        createNotificationChannel(manager, sound);
        manager.notify(2, createNotificationBuilder(title, body, sound).build());
    }

    private NotificationCompat.Builder createNotificationBuilder(String title, String body, Uri sound) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), AZAN_CHANNEL_ID);
        builder
                .setContentTitle(title)
                .setContentText(body)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.azan)
                .setSound(sound);
        return builder;
    }

    private void createNotificationChannel(NotificationManager manager, Uri sound) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(AZAN_CHANNEL_ID, AZAN_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(CONTENT_TYPE_SONIFICATION)
                    .build();
            channel.setSound(sound, audioAttributes);
            manager.createNotificationChannel(channel);
        }
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        String title = data.getString(AZAN_TITLE_KEY);
        String content = data.getString(AZAN_CONTENT_KEY);
        Uri azanSound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.azan);
        sendNotification(title, content, azanSound);
        return Result.success();
    }
}









