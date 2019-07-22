package com.carey.myapplication

import android.content.Context
import com.facebook.stetho.Stetho


/**
 * @author Carey
 * @date 2019/7/19
 */
object SdkManager {
    fun init(context: Context) {
        Stetho.initializeWithDefaults(context)
    }
}