package ioc.fernandez.notifyme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
private const val NOTIFICATION_ID = 0

class MainActivity : AppCompatActivity() {

    private lateinit var mNotifyManager: NotificationManager
    private lateinit var notifyButton: Button
    private lateinit var updateButton: Button
    private lateinit var cancelButton: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notifyButton = findViewById(R.id.notify)
        notifyButton.setOnClickListener {
            sendNotification()
        }

        updateButton = findViewById(R.id.update)
        updateButton.isEnabled = false
        updateButton.setOnClickListener {
            updateNotification()
        }

        cancelButton = findViewById(R.id.cancel)
        cancelButton.isEnabled = false
        cancelButton.setOnClickListener {
            cancelNotification()
        }

        createNotificationChannel()
    }

    private fun cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID)
        setNotificationButtonState(
            isNotifyEnabled = true,
            isUpdateEnabled = false,
            isCancelEnabled = false
        )
    }

    private fun updateNotification() {
        val androidImage = BitmapFactory.decodeResource(resources, R.drawable.mascot_1)
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification updated!")
        )
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(
            isNotifyEnabled = false,
            isUpdateEnabled = false,
            isCancelEnabled = true
        )

    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("You've been notified!")
            .setContentText("This is your notification text.")
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(notificationPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification() {
        val notifyBuilder = getNotificationBuilder()
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(
            isNotifyEnabled = false,
            isUpdateEnabled = true,
            isCancelEnabled = true
        )


    }

    private fun createNotificationChannel() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Mascot Notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notification from Mascot"
            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun setNotificationButtonState(
        isNotifyEnabled: Boolean,
        isUpdateEnabled: Boolean,
        isCancelEnabled: Boolean
    ) {
        notifyButton.isEnabled = isNotifyEnabled
        updateButton.isEnabled = isUpdateEnabled
        cancelButton.isEnabled = isCancelEnabled
    }
}