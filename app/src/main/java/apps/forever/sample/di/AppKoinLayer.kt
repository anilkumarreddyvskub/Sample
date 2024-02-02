package apps.forever.sample.di

import apps.forever.sample.SampleApplication
import apps.forever.sample.di.koin_modules.messagesModule
import apps.forever.sample.di.koin_modules.networkModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level
import org.koin.core.module.Module

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */

fun getAppKoinLayer(app: SampleApplication) = GlobalContext.startKoin {
    androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
    androidContext(app)
    modules(moduleList)
}

val moduleList = listOf<Module>(
    networkModule,
    messagesModule
)