package com.hongyu.zorelib.utils

import android.text.InputFilter
import android.text.Spanned

/**
 * 限制特殊字符录入，允许自定义特殊字符列表
 */
class SpecialCharacterInputFilter(private val specialCharacters: String = "!@#$%^&*()-+=[]{}|\\:;\"'<>,.?/") : InputFilter {

    // 使用HashSet优化特殊字符的查找效率
    private val specialCharactersSet = HashSet<Char>().apply {
        specialCharacters.forEach { add(it) }
    }

    override fun filter(
        source: CharSequence, start: Int, end: Int,
        dest: Spanned, dstart: Int, dend: Int
    ): CharSequence {
        for (i in start until end) {
            if (source[i] in specialCharactersSet) {
                // 如果输入的是特殊字符，则不接受输入
                return ""
            }
        }
        // 如果没有找到特殊字符，允许输入
        return source
    }
}
