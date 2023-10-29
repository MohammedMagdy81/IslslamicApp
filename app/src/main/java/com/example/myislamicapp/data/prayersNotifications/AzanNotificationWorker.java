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
import android.os.Build;

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
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notification = creteNotification(title, body, sound);
        createNotificationChannel(notificationManager, sound);

        notificationManager.notify(0, notification.build());
    }

    private NotificationCompat.Builder creteNotification(String title, String body, Uri sound) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), AZAN_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.azan)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(sound);
        return notification;
    }

    private void createNotificationChannel(NotificationManager notificationManager, Uri sound) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    AZAN_CHANNEL_ID,
                    AZAN_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            notificationChannel.setSound(sound, attributes);
            notificationManager.createNotificationChannel(notificationChannel);
        }


    }

    @NonNull
    @Override
    public Result doWork() {
        Data input = getInputData();

        String title = input.getString(AZAN_TITLE_KEY);
        String body = input.getString(AZAN_CONTENT_KEY);
        Uri sound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.azan);

        sendNotification(title, body, sound);
        return Result.success();
    }

}









