package com.carey.myapplication.utils

import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.carey.myapplication.App


/**
 * 根据资源id读取资源内容的工具类
 * @author Carey
 * @date 2018/11/15
 */
object ResourceUtils {
    /**
     * 获取颜色值
     *
     * @param color 颜色id
     * @return ARGB格式的颜色值
     */
    fun getColor(@ColorRes color: Int): Int {
        return App.getContext().resources.getColor(color)
    }

    /**
     * 获取尺寸值
     *
     * @param dimen 尺寸id
     * @return 整数型的尺寸
     */
    fun getIntDimen(@DimenRes dimen: Int): Int {
        return App.getContext().resources.getDimensionPixelOffset(dimen)
    }

    /**
     * 获取文本
     * @param string 文本id
     * @return 文本内容
     */
    fun getString(@StringRes string: Int): String {
        return App.getContext().getString(string)
    }

    /**
     * 获取文本
     * @param drawable 文本id
     * @return 文本内容
     */
    fun getDrawable(@DrawableRes drawable: Int): Drawable {
        return App.getContext().resources.getDrawable(drawable)
    }

    /**
     * 获取文本
     *
     * @param msg  固定文本ID
     * @param text 可变内容
     * @return 文本内容
     */
    fun getString(@StringRes msg: Int, vararg text: Any): String {
        return App.getContext().getString(msg, text)
    }
}
