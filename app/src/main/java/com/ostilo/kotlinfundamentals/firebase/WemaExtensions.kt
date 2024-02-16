package com.ostilo.kotlinfundamentals.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

object WemaExtensions {

    public fun createPendingIntentGetActivity(
        context: Context?,
        id: Int,
        intent: Intent?,
        flag: Int,
    ): PendingIntent? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_IMMUTABLE or flag)
        } else {
            PendingIntent.getActivity(context, id, intent, flag)
        }
    }

    fun cancelNotification(context: Context) {
        val nMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = (System.currentTimeMillis() and 0xfffffffL).toInt()
        nMgr.cancel(notificationId)
    }
}