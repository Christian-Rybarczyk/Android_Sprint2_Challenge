package com.lambdaschool.sprint2_challenge.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.lambdaschool.sprint2_challenge.R

object Notification {
    fun createNotification(context: Context) {
        val channelId = "${context.packageName}.simplechannel"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Shopping list notification channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "Channel to send your shopping list"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ice_cream_cone)
                .setContentTitle("Confirmation")
                .setContentText("Your order has been placed")
                .setAutoCancel(true)
        notificationManager.notify(42, builder.build())
    }
}