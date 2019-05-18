package net.ehvi.android.update;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_TOPIC_APPLICATION_UPDATE_ANDROID = "TopicAppUpdateAndroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        setUpApp();
    }

    private void setUpApp() {
        if (SettingsManager.isFirstTimeLaunch(this)) {
            subscribeToFirebaseNotificationTopic();
            SettingsManager.setVersionCode(BuildConfig.VERSION_CODE, this);
            SettingsManager.setFirstTimeLaunch(false, this);
        } else if (SettingsManager.getVersionCode(this) != BuildConfig.VERSION_CODE) {
            SettingsManager.setVersionCode(BuildConfig.VERSION_CODE, this);
        }
    }

    private void subscribeToFirebaseNotificationTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_TOPIC_APPLICATION_UPDATE_ANDROID);
    }
}
