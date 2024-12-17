package com.hongyu.zorelib.mvp.view

import com.hongyu.zorelib.bean.PaySuccessBean

/**
 * Created by isle on 2017/img7/28.
 */
interface BasePayUI : BaseUI {
    fun onPaySuccess(paySuccessBean: PaySuccessBean?)
    fun onPayError()
}