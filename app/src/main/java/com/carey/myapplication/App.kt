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
import com.carey.myapplication.App.Variable.mActivities
import com.carey.myapplication.App.Variable.sApp
import com.carey.myapplication.App.Variable.sContext
import com.carey.myapplication.data.net.NetCore
import com.carey.myapplication.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

/**
 * @author Carey
 * @date 2019/7/19
 */
class App : Application() {
    @SuppressLint("StaticFieldLeak")
    object Variable {
        var sApp: App? = null
        var sContext: Context? = null
        var mActivities = ArrayList<Activity>()
    }

    override fun onCreate() {
        super.onCreate()
        sApp = this
        sContext = this
        initGlobalTimeZone()
        SdkManager.init(this)
        NetCore.init()
        LogUtils.init()
    }

    companion object {

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
    }

    /**
     * 设置当前APP时区
     */
    private fun initGlobalTimeZone() {
        val chinaTimeZone = TimeZone.getTimeZone("GMT+8")
        TimeZone.setDefault(chinaTimeZone)
    }

}