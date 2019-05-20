package net.ehvi.android.update;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import net.ehvi.android.update.utility.DateTimeUtil;

import java.util.Map;

public class EhviMessagingService extends FirebaseMessagingService {
    private static final String TAG = "EhviMessagingService";

    private final static String UPDATE_NOTIFICATION_CHANNEL_ID = "app_update";
    private final static String UPDATE_NOTIFICATION_CHANNEL_NAME = "App Update";
    public static final int NOTIFICATION_ID = 21;//can be some unique value
    public static final int REQUEST_CODE_SENDER = 0;//can be some unique value

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        createUpdateNotificationChannel();
        Map<String, String> data = remoteMessage.getData();

        RemoteMessage.Notification notification = remoteMessage.getNotification();

        if (notification != null) {
            String title = notification.getTitle();
            String body = notification.getBody();

            Log.e(TAG, "\n\nonMessageReceived:" +
                    "\ntitle:  "+ title+
                    "\nbody :  "+ body);

            if (data.size() > 0) {//message contains a data payload.
                //for short time
                showUpdateAvailabilityNotification(title, body, DateTimeUtil.currentDateTime(), data);
            }
        }
    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of  the previous token had been compromised.
     * Note that this is called when the InstanceID token is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.e(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or manage this apps subscriptions on the server side,
        // send the Instance ID token to your app server.
        //sendRegistrationToServer(token);


    }


    private void createUpdateNotificationChannel() {
        //NotificationChannel class is new in Oreo; its not yet in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String description = "Channel for app update notification";
            NotificationChannel channel = new NotificationChannel(UPDATE_NOTIFICATION_CHANNEL_ID, UPDATE_NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     */
    public void showUpdateAvailabilityNotification(String contentTitle, String contentText, long acceptedTimestamp, Map<String, String> data) {

        //Intent openActivityIntent = createGooglePlayUpdateIntent(acceptedTimestamp, data);
        Intent openActivityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( "market://details?id=" +  BuildConfig.APPLICATION_ID));
        openActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, REQUEST_CODE_SENDER, openActivityIntent, 0);
        //
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addNextIntentWithParentStack(openActivityIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(REQUEST_CODE_SENDER, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, UPDATE_NOTIFICATION_CHANNEL_ID)//
                .setSmallIcon(R.mipmap.ic_launcher)//
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contentText))//
                .setContentTitle(contentTitle)//
                .setContentText(contentText)
                .setLights(ContextCompat.getColor(this, R.color.colorPrimary), 2_000, 5_000)//
                .setTicker(contentTitle)//
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent);


        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    private Intent createGooglePlayUpdateIntent(long acceptedTimestamp, Map<String, String> data) {
        String PLAY_URL_APP = "market://details?id=" +  BuildConfig.APPLICATION_ID;
        Intent openActivityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( "market://details?id=" +  BuildConfig.APPLICATION_ID));
        return openActivityIntent;
    }

}