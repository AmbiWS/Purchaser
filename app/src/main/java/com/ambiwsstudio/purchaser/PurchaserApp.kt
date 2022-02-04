package com.ambiwsstudio.purchaser

import android.app.Application
import com.ambiwsstudio.purchaser.di.applicationModules
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/* TODO add credentials
    <a href="https://www.flaticon.com/free-icons/sell" title="sell icons">Sell icons created by Freepik - Flaticon</a>
 */

class PurchaserApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@PurchaserApp)
            modules(applicationModules)
        }
    }

    companion object {
        const val TAG = "PUR"
    }
}
