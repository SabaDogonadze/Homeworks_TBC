package com.example.tbchomework18.app

import android.app.Application

class App:Application() {

        companion object{
            lateinit var application: Application
                private set
        }

    override fun onCreate() {
        super.onCreate()
        application = this  // this = application context
    }

}