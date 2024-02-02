package apps.forever.sample.di.koin_modules

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */

val networkModule = module {
    var domain = "https://jsonplaceholder.typicode.com/"
    single {
        if (BuildConfig.DEBUG) { //for testing logs enabled
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {  //for release disabled logs
            OkHttpClient
                .Builder()
                .build()
        }
    }
    factory<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(domain)
            .client(get())
            .build()
    }
}