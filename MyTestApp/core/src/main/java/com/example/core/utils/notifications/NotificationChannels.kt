package com.example.core.utils.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

object NotificationChannels {

    val IMPORTANT_MESSAGE_CHANNEL_ID = "messages"

    fun create(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelWithImportantMessages(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannelWithImportantMessages(context: Context) {
        val name = "Messages"
        val channelDescription = "Urgent messages"
        val priority = NotificationManager.IMPORTANCE_HIGH
        val channel =NotificationChannel(IMPORTANT_MESSAGE_CHANNEL_ID,name,priority).apply {
            description = channelDescription
            enableVibration(true)
            vibrationPattern = longArrayOf(100,200,500,100)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),null)
        }
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }

}