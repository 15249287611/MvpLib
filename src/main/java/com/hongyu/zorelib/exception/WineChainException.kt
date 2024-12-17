package com.hongyu.zorelib.exception

class WineChainException(@JvmField val code: Int, message: String?) : RuntimeException(message)
