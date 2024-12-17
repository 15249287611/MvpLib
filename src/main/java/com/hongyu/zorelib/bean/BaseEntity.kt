package com.hongyu.zorelib.bean


class BaseEntity<T> {
    @JvmField
    var code: Int = 0
    @JvmField
    var msg: String = ""
    @JvmField
    var data: T? = null

    override fun toString(): String {
        return "BaseEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + data +
                '}'
    }
}
