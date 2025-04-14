package com.pmaita.mov.core

import android.app.Application
import com.pmaita.mov.core.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

@Suppress("unused")
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApplication)
            androidFileProperties()
            fragmentFactory()
            modules(allModules)
        }

    }

}