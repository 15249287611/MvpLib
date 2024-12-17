package com.hongyu.zorelib.utils

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * <pre>
 * author : 宇
 * time   : 2021/01/25
 * desc   : 验证工具类
</pre> *
 */
object MyVerifyUtils {
    /**
     * 验证邮箱输入是否合法
     */
    @JvmStatic
    fun isEmail(email: String): Boolean {
        val str =
            "^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$"
        val p = Pattern.compile(str)
        val m = p.matcher(email)
        return m.matches()
    }

    /**
     * 验证是否是手机号码
     */
    fun isMobile(str: String): Boolean {
        val pattern = Pattern.compile("1[0-9]{10}")
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    /**
     * 验证验证码格式
     */
    @JvmStatic
    fun isCode(str: String): Boolean {
        return !TextUtils.isEmpty(str) && str.length == 6
    }

    /**
     * 验证密码格式
     */
    @JvmStatic
    fun isPassword(str: String): Boolean {
        return !TextUtils.isEmpty(str) && str.length >= 6
    }
}