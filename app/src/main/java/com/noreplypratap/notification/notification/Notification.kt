package com.noreplypratap.notification.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.noreplypratap.notification.ui.MainActivity
import com.noreplypratap.notification.R
import com.noreplypratap.notification.utils.Constants.CHANNEL_ID
import com.noreplypratap.notification.utils.Constants.CHANNEL_NAME
import com.noreplypratap.notification.utils.Constants.MARK_AS_READ_ACTION

object Notification {

    fun createNotification(
        notificationId: Int,
        context: Context,
        title: String,
        message: String,
        imageUrl: String? = null,
        channelID: String = CHANNEL_ID,
        channelName: String = CHANNEL_NAME
    ) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a Notification Channel (required for Android Oreo and higher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Create an intent for the notification click
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Create an intent for the "Mark as Read" action
        val markAsReadIntent = Intent(context, BroadcastReceiver::class.java)
        markAsReadIntent.action = MARK_AS_READ_ACTION
        val markAsReadPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            markAsReadIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Create a large bitmap from the image URL (if provided)
        val largeBitmap: Bitmap? = imageUrl?.let {
            try {
                val inputStream = java.net.URL(it).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                null
            }
        }

        // Build the notification
        val builder = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(largeBitmap)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(largeBitmap)
                    .bigLargeIcon(largeBitmap)
            )
            .addAction(
                R.drawable.ic_launcher_foreground,
                "Mark as Read",
                markAsReadPendingIntent
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Show the notification
        notificationManager.notify(notificationId, builder.build())
    }
}


