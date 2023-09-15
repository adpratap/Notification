package com.noreplypratap.notification

import android.app.Application
import com.noreplypratap.notification.utils.UtilFunctions

class NotificationApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        UtilFunctions.logMessage(message = "NotificationApplication ... onCreate")
    }
}