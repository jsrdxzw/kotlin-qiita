package com.example.qiitaclient

import android.app.Application

class QiitaClientApp : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}