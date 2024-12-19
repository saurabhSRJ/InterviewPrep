package com.example.interviewprepsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JokesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}