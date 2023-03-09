package com.shcoding.notediary

import android.app.Application
import com.shcoding.notediary.di.noteDiaryAppModule
import com.shcoding.notediary.presentation.screens.authentication.di.authenticationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteDiaryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NoteDiaryApplication)
            modules(noteDiaryAppModule)
        }
    }
}