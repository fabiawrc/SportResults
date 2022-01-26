package com.example.sportresults

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SportResultsApplication: Application() {
    override fun onCreate() {
        if(BuildConfig.DEBUG){
            super.onCreate()
            Timber.plant(Timber.DebugTree())
        }
    }
}