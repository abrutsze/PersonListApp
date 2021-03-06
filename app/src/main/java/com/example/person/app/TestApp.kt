package com.example.person.app

import android.app.Application
import com.example.data.di.apiModule
import com.example.data.di.repositoryModule
import com.example.domian.di.interactorModule
import com.example.person.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TestApp)
            modules(modules)
        }
    }

    val modules = listOf(
        apiModule,
        interactorModule,
        repositoryModule,
        viewModelModule
    )
}