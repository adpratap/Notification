package com.noreplypratap.notification.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.noreplypratap.notification.R
import com.noreplypratap.notification.notification.Notification
import com.noreplypratap.notification.permission.RequestPermission
import com.noreplypratap.notification.utils.UtilFunctions
import com.noreplypratap.notification.utils.UtilFunctions.onClick

class MainActivity : AppCompatActivity() {
    var notifyId = 0
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UtilFunctions.logMessage(message = "MainActivity ... onCreate")
        RequestPermission.requestPermissions(this)
        onClick(R.id.btnNotifyOnce) {
            Notification.createNotification(
                getNotificationId(),
                this,
                "Yours Title",
                "Message here ....."
            )
        }
    }

    private fun getNotificationId(): Int {
        return notifyId++
    }
}