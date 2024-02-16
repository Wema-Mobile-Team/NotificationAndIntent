package com.ostilo.kotlinfundamentals.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.ostilo.kotlinfundamentals.MainActivity
import com.ostilo.kotlinfundamentals.PersonModel
import com.ostilo.kotlinfundamentals.R
import kotlinx.coroutines.tasks.await
import java.util.Random

class AlatFirebaseMessagingService : FirebaseMessagingService() {
//d-mf5cL4Tf2LfHTRgaOufp:APA91bFyqg-rbO5QXGXmBB-wyk6uKPpDSANZPe2tG_r66YVGhV9JYq-una2MbfsS7wy0MNJZbbmeJ22rbnQkGWPT25kZ6pEUmrqXnMi_-GcF3m3XoZcMiuyNAeWie3PFJ6eqLiTgu1YU
    //d-mf5cL4Tf2LfHTRgaOufp:APA91bFyqg-rbO5QXGXmBB-wyk6uKPpDSANZPe2tG_r66YVGhV9JYq-una2MbfsS7wy0MNJZbbmeJ22rbnQkGWPT25kZ6pEUmrqXnMi_-GcF3m3XoZcMiuyNAeWie3PFJ6eqLiTgu1YU
    private var notificationUtils: NotificationUtils? = null

    override fun onNewToken(token: String) {
        super.onNewToken(token)
       // val token = FirebaseMessaging.getInstance().token.await()

        FirebaseMessaging.getInstance().token.addOnCompleteListener { item ->
            if(!item.isSuccessful){
                Log.e("addOnCompleteListener","FirebaseMessaging onNewToken")
            }

            var finalToken = item.result

            var catchToken = finalToken
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showMyNotification(message)
    }

    private fun showMyNotification(message: RemoteMessage) {
        try {
            var personModel : PersonModel? = null
            // message.notification = Notification()
            if(message.notification != null){
                val notificationBody = message.notification?.body
                if(notificationBody != null){
                    personModel = PersonModel(notificationBody,1)
                }
            }

            val bundle = Bundle()
            bundle.putString("personModel",Gson().toJson(personModel))

            val resultIntent = Intent(applicationContext, MainActivity::class.java)
            resultIntent.action = Intent.ACTION_VIEW
            resultIntent.putExtra("action","gotoProductPage")
            resultIntent.putExtra("type","payment")
            resultIntent.putExtra("name", bundle)
            showNotificationMessage(applicationContext, "Welcome", "Yoo, Mo and Micheal", "", resultIntent)

        }catch (e:Exception){
            Log.e("showMyNotification", e.localizedMessage?.toString().toString())
        }
    }

    private fun showNotificationMessage(
        context: Context,
        title: String,
        message: String,
        timeStamp: String,
        intent: Intent
    ) {
        notificationUtils = NotificationUtils(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        notificationUtils?.showNotificationMessage(title, message, timeStamp, intent)
    }

}