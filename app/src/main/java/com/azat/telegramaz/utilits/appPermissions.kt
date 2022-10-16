package com.azat.telegramaz.utilits

import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

/** Файл для работы с разрешениями */

const val READ_CONTACTS = android.Manifest.permission.READ_CONTACTS
const val RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO
const val PERMISSION_REQUEST = 200

fun checkPermission(permission: String): Boolean {
    /** Функция принимает разрешения и проверяет, если разрешение еще не было
     * предоставлено, запускает окно с запросом пользователю */
    return if (Build.VERSION.SDK_INT >= 23
        && ContextCompat.checkSelfPermission(APP_ACTIVITY, permission)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(APP_ACTIVITY, arrayOf(permission), PERMISSION_REQUEST)
            false
    } else true
}