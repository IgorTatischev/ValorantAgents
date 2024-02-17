package com.agents.valorantagents.di

import android.app.Application
import com.agents.main.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            androidLogger()
            modules(
                listOf(
                    MainModule.dataModule,
                    MainModule.domainModule,
                    MainModule.viewModelModule,
                )
            )
        }
    }
}