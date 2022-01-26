package com.skillbox.mytestapp.services

import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.core.utils.notifications.NotificationChannels
import com.skillbox.mytestapp.MainActivity
import com.skillbox.mytestapp.R
import timber.log.Timber

class PushNotificartionService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Timber.d("Message recieve ${message.data}")
        val body = message.data["body"]
        Timber.d("Message $body")
        val title = message.data["title"]
        Timber.d("Message $title")
        if (body != null && title != null) {
            showPushNotification(body, title)
        }
    }

    private fun showPushNotification(body: String, title: String) {
        Timber.d("Message HAVE")

        val intent = Intent(this,MainActivity::class.java).apply {
            putExtra("screen","https://mysite.com/cart")
        }
        val pendingIntent = PendingIntent.getActivity(this,123,intent,0)


        val notification =
            NotificationCompat.Builder(
                this,
                NotificationChannels.IMPORTANT_MESSAGE_CHANNEL_ID
            )
                .setContentTitle("new message")
                .setContentText("$body")
                // ДЛЯ АНДРОИД НИЖЕ 8го Необходимо поставить вручную звук
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                // ДЛЯ АНДРОИД НИЖЕ 8го Необходимо поставить вручную Приоритет
                .setSmallIcon(R.drawable.ic_notification_message)
                .setVibrate(longArrayOf(100, 200, 500, 500))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

        NotificationManagerCompat.from(this)
            .notify(MESSAGE_NOTIFICATION_ID, notification)



    }

    companion object {
        const val MESSAGE_NOTIFICATION_ID = 12345
    }

}