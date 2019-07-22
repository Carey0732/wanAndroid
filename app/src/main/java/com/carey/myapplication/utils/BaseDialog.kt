package com.carey.myapplication.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.carey.myapplication.R


/**
 * 自定义Dialog基类
 *
 * @author Carey
 */
class BaseDialog private constructor(context: Context) : Dialog(context, R.style.MyDialog) {

    private var tvTitle: TextView? = null
    private var btnPositive: TextView? = null
    private var btnNegative: TextView? = null
    private var tvMsg: TextView? = null
    private var flCustom: FrameLayout? = null
    private val onDefaultClickListener: View.OnClickListener = View.OnClickListener { cancel() }
    private var onPositiveListener = onDefaultClickListener
    private var onNegativeListener = onDefaultClickListener
    private var mTitle: String? = null
    private var mMessage: String? = null
    private var positiveText: String? = null
    private var negativeText: String? = null
    private var isNegativeBtnShow = true
    private var mView: View? = null
    private var viewLine: View? = null

    public override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_base)
        flCustom = findViewById(R.id.fl_dialog_content)
        tvTitle = findViewById(R.id.tv_title)
        btnPositive = findViewById(R.id.btn_positive)
        btnNegative = findViewById(R.id.btn_negative)
        tvMsg = findViewById(R.id.tv_msg)
        viewLine = findViewById(R.id.view)
    }

    /**
     * 调用完Builder类的create()方法后显示该对话框的方法
     */
    override fun show() {
        super.show()
        show(this)
    }

    private fun show(mDialog: BaseDialog) {
        if (!TextUtils.isEmpty(mDialog.mTitle)) {
            mDialog.tvTitle!!.text = mDialog.mTitle
        }
        if (mDialog.mView != null) {
            mDialog.flCustom!!.removeAllViews()
            mDialog.flCustom!!.addView(mDialog.mView)
            mDialog.tvMsg!!.visibility = View.GONE
        } else {
            if (!TextUtils.isEmpty(mDialog.mMessage)) {
                mDialog.tvMsg!!.text = mDialog.mMessage
                mDialog.tvMsg!!.visibility = View.VISIBLE
            }
        }
        if (!mDialog.isNegativeBtnShow) {
            mDialog.btnNegative!!.visibility = View.GONE
            viewLine!!.visibility = View.GONE
            val layoutParams = mDialog.btnPositive!!
                .layoutParams as LinearLayout.LayoutParams
            layoutParams.setMargins(150, layoutParams.topMargin, 150, layoutParams.bottomMargin)
            mDialog.btnPositive!!.layoutParams = layoutParams
        } else {
            mDialog.btnNegative!!.setOnClickListener(mDialog.onNegativeListener)
            if (!TextUtils.isEmpty(mDialog.negativeText)) {
                mDialog.btnNegative!!.text = mDialog.negativeText
            }
        }
        mDialog.btnPositive!!.setOnClickListener(mDialog.onPositiveListener)
        if (!TextUtils.isEmpty(mDialog.positiveText)) {
            mDialog.btnPositive!!.text = mDialog.positiveText
        }
    }

    class Builder(context: Context) {

        private val mDialog: BaseDialog = BaseDialog(context)

        /**
         * 设置对话框标题
         *
         * @param title
         */
        fun setTitle(title: String): Builder {
            mDialog.mTitle = title
            return this
        }

        /**
         * 设置对话框文本内容,如果调用了setView()方法，该项失效
         *
         * @param msg
         */
        fun setMessage(msg: String): Builder {
            mDialog.mMessage = msg
            return this
        }

        /**
         * 设置确认按钮的回调
         *
         * @param onClickListener
         */
        fun setPositiveButton(onClickListener: View.OnClickListener): Builder {
            mDialog.onPositiveListener = onClickListener
            return this
        }

        /**
         * 设置确认按钮的回调
         *
         * @param btnText,onClickListener
         */
        fun setPositiveButton(btnText: String, onClickListener: View.OnClickListener): Builder {
            mDialog.positiveText = btnText
            mDialog.onPositiveListener = onClickListener
            return this
        }

        /**
         * 设置确认按钮的回调
         *
         * @param btnText,onClickListener
         */
        fun setPositiveButton(btnText: String): Builder {
            mDialog.positiveText = btnText
            return this
        }

        /**
         * 设置取消按钮的回掉
         *
         * @param onClickListener
         */
        fun setNegativeButton(onClickListener: View.OnClickListener): Builder {
            mDialog.onNegativeListener = onClickListener
            return this
        }

        /**
         * 设置取消按钮的回调
         *
         * @param btnText,onClickListener
         */
        fun setNegativeButton(btnText: String, onClickListener: View.OnClickListener): Builder {
            mDialog.negativeText = btnText
            mDialog.onNegativeListener = onClickListener
            return this
        }

        /**
         * 设置取消按钮的文字
         * 回调默认关闭dialog
         *
         * @param btnText
         * @return
         */
        fun setNegativeButton(btnText: String): Builder {
            mDialog.negativeText = btnText
            return this
        }

        /**
         * 设置是否显示取消按钮，默认显示
         *
         * @param isNegativeBtnShow
         */
        fun setNegativeBtnShow(isNegativeBtnShow: Boolean): Builder {
            mDialog.isNegativeBtnShow = isNegativeBtnShow
            return this
        }

        /**
         * 设置自定义内容View
         *
         * @param view
         */
        fun setView(view: View): Builder {
            mDialog.mView = view
            return this
        }

        /**
         * 设置该对话框能否被Cancel掉，默认可以
         *
         * @param cancelable
         */
        fun setCancelable(cancelable: Boolean): Builder {
            mDialog.setCancelable(cancelable)
            return this
        }

        /**
         * 设置对话框被cancel对应的回调接口，cancel()方法被调用时才会回调该接口
         *
         * @param onCancelListener
         */
        fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): Builder {
            mDialog.setOnCancelListener(onCancelListener)
            return this
        }

        /**
         * 设置对话框消失对应的回调接口，一切对话框消失都会回调该接口
         *
         * @param onDismissListener
         */
        fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): Builder {
            mDialog.setOnDismissListener(onDismissListener)
            return this
        }

        /**
         * 通过Builder类设置完属性后构造对话框的方法
         */
        fun create(): BaseDialog {
            return mDialog
        }
    }
}