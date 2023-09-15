package com.noreplypratap.notification.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.noreplypratap.notification.utils.Constants.MARK_AS_READ_ACTION
import com.noreplypratap.notification.utils.UtilFunctions

class ActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == MARK_AS_READ_ACTION) {
            UtilFunctions.logMessage(message = "BroadcastReceiver ... onReceive")
            // Handle the "Mark as Read" action here
            // You can perform any desired action when the button is clicked
        }
    }
}