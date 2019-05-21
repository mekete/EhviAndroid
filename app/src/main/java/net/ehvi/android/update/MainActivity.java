package net.ehvi.android.update;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import net.ehvi.android.update.service.EhviMessagingService;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_TOPIC_APPLICATION_UPDATE_ANDROID = "TopicAppUpdateAndroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        openGooglePlayIfNeeded();
        authenticateAnonymously();
        setUpApp();
    }

    private void openGooglePlayIfNeeded() {
        Log.e(TAG, "111--- openGooglePlayIfNeeded: "+"" );
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID));
        boolean openGooglePlayApp = getIntent().getBooleanExtra(EhviMessagingService.ACTION_OPEN_PLAY_STORE,false);
        if (openGooglePlayApp) {
            Log.e(TAG, "2222--- openGooglePlayIfNeeded: "+"" );

            startActivity(intent);
        }else{
            Log.e(TAG, "3333--- openGooglePlayIfNeeded: "+"" );

        }
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
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "msg_subscribed", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "OOOOps ..msg_subscribed", Toast.LENGTH_SHORT).show();

                    }


                });
    }

    private static final String TAG = "MainActivity";

    private void authenticateAnonymously() {
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser == null) {
//            mAuth.signInAnonymously().addOnCompleteListener(this, task -> {
//                if (task.isSuccessful()) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.e(TAG, "signInAnonymously:success");
//                    FirebaseUser user = mAuth.getCurrentUser();
//
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.e(TAG, "signInAnonymously:failure", task.getException());
//                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        }
    }
}
