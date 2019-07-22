package com.carey.myapplication

/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            佛祖保佑       永无BUG
*/
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import kotlin.system.exitProcess
import com.carey.myapplication.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Carey
 * @date 2019/7/19
 */
class App : Application() {
    @SuppressLint("StaticFieldLeak")
    private var sApp: App? = null
    @SuppressLint("StaticFieldLeak")
    private var sContext: Context? = null

    private var mActivities = ArrayList<Activity>()

    override fun onCreate() {
        super.onCreate()
        sApp = this
        sContext = this
        SdkManager.init(this)
        LogUtils.init()
    }

    /**
     * 获取Application
     *
     * @return Application
     */
    fun getApplication(): App {
        if (sApp != null) {
            return sApp as App
        }
        throw NullPointerException("u should init first")

    }

    /**
     * 获取Context实例
     *
     * @return Context
     */
    fun getContext(): Context {
        if (sContext != null) {
            return sContext as Context
        }
        throw NullPointerException("u should init first")
    }

    /**
     * 添加Activity
     */
    fun addActivity(activity: Activity) {
        if (!mActivities.contains(activity)) {
            mActivities.add(activity)
        }
    }

    /**
     * 销毁单个Activity
     */
    fun removeActivity(activity: Activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity)
            activity.finish()
        }
    }

    /**
     * 销毁所有的Activity
     */
    fun removeALLActivity() {
        for (activity in mActivities) {
            activity.finish()
        }
        exitProcess(0)
    }

    /**
     * 获取activity堆栈
     */
    fun getActivities(): List<Activity> {
        return mActivities
    }

    /**
     * 设置当前APP时区
     */
    fun initGlobalTimeZone() {
        val chinaTimeZone = TimeZone.getTimeZone("GMT+8")
        TimeZone.setDefault(chinaTimeZone)
    }

}