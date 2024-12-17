package com.hongyu.zorelib.utils

import android.text.InputFilter
import android.text.Spanned

/**
 * 数字+大写字母的 筛选器
 */
class AlphaNumericInputFilter : InputFilter {
    override fun filter(
        source: CharSequence, start: Int, end: Int,
        dest: Spanned, dstart: Int, dend: Int
    ): CharSequence {
        val filteredStringBuilder = StringBuilder()
        for (i in start until end) {
            val currentChar = source[i]
            if (Character.isLetterOrDigit(currentChar)) {
                // 如果是字母或数字，添加到过滤后的字符串中
                filteredStringBuilder.append(currentChar.uppercaseChar())
            }
        }
        return filteredStringBuilder.toString()
    }
}