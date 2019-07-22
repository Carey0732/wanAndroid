package com.carey.myapplication

import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * @author Carey
 * @date 2019/7/19
 */
object SdkManager {
    private const val CONNECT_TIMEOUT = 10L
    private const val IO_TIMEOUT = 10L
    private const val NET_CACHE_SIZE = 1024L * 1024L * 10L

    fun init(context: Context) {
        Stetho.initializeWithDefaults(context)
    }

    fun getHttpClient(): OkHttpClient {
        Stetho.initializeWithDefaults(App.getContext())
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .cache(Cache(App.getContext().cacheDir, NET_CACHE_SIZE))
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }
}