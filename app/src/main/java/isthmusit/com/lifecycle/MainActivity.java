package isthmusit.com.lifecycle;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private NotificationCompat.Builder notification_builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotification("onCreate");
    }

    @Override protected void onPause() {
        sendNotification("onPause");
        super.onPause();
    }

    @Override protected void onResume() {
        super.onResume();
        sendNotification("onResume");
    }

    @Override protected void onStop() {
        sendNotification("onStop");
        super.onStop();
    }

    @Override protected void onRestart() {
        super.onRestart();
        sendNotification("onRestart");
    }

    @Override protected void onDestroy() {
        sendNotification("onDestroy");
        super.onDestroy();
    }

    private void sendNotification(String title){
        NotificationManager notification_manager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String chanel_id = "3000";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(chanel_id, title, importance);
            mChannel.setDescription("");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notification_manager.createNotificationChannel(mChannel);
            notification_builder = new NotificationCompat.Builder(this, chanel_id);
        } else {
            notification_builder = new NotificationCompat.Builder(this);
        }
        notification_builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText("")
                .setAutoCancel(true);

        Notification notification = notification_builder.build();
        notification_manager.notify(1, notification);
    }
}
