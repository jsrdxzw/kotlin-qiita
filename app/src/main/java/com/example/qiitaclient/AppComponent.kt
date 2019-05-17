package com.example.qiitaclient

import dagger.Component
import javax.inject.Singleton

@Component(modules = [ClientModule::class])
@Singleton
interface AppComponent{
    fun inject(mainActivity: MainActivity)
}