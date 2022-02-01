package com.example.sportresults.core.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class PermissionManager () {
    companion object{
        fun IsLocationPermissionGranted(activity: Activity): Boolean {
            return if (ActivityCompat.checkSelfPermission(
                    activity.applicationContext,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    activity.applicationContext,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    0
                )
                false
            } else {
                true
            }
        }
    }
}