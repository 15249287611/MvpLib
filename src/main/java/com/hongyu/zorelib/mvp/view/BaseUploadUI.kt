package com.hongyu.zorelib.mvp.view

/**
 * Created by isle on 2017/img7/28.
 */
interface BaseUploadUI : BaseUI {
    fun onRequestProgress(bytesWritten: Long, contentLength: Long)
}