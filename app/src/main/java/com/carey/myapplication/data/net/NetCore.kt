package com.carey.myapplication.data.net

import com.carey.myapplication.BuildConfig
import com.carey.myapplication.SdkManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 网络连接核心类
 * @author Carey
 * @date 2019/7/22
 */
object NetCore {
    private var sOkHttpClient: OkHttpClient? = null
    private var sCore: Retrofit? = null
    fun init() {
        sOkHttpClient = SdkManager.getHttpClient()
    }

    /**
     * 初始化Retrofit
     */
    private fun getCore(): Retrofit {
        if (sCore == null) {
            if (sOkHttpClient != null) {
                sCore = getCore(sOkHttpClient as OkHttpClient)
            } else {
                throw NullPointerException("u should init NetCore first")
            }
        }
        return sCore as Retrofit
    }

    private fun getCore(client: OkHttpClient): Retrofit {
        return getCore(BuildConfig.BASE_ANDROID_URL, client)
    }

    private fun getCore(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}