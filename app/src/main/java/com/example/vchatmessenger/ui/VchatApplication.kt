package com.example.vchatmessenger.ui

import android.app.Application
import com.example.vchatmessenger.data.AppContainer
import com.example.vchatmessenger.data.DefaultAppContainer

class VchatApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}