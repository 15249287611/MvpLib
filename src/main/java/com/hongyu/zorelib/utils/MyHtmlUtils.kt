package com.hongyu.zorelib.utils

import android.text.Spanned
import androidx.core.text.HtmlCompat

object MyHtmlUtils {
    fun fromHtml(text: String?): Spanned {
        if (text.isNullOrEmpty()) return HtmlCompat.fromHtml("", HtmlCompat.FROM_HTML_MODE_LEGACY)
        val processedText = text.replace("\n", "<br>")
        return HtmlCompat.fromHtml(processedText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}