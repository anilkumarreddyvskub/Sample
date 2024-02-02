package apps.forever.sample

import android.app.Application
import apps.forever.sample.di.getAppKoinLayer

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */
class SampleApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        getAppKoinLayer(this)
    }
}