package com.hongyu.zorelib.exception

import com.hongyu.zorelib.utils.AppCons
import io.reactivex.rxjava3.core.Observer
import retrofit2.HttpException

abstract class DefaultSubscriber<T : Any> : Observer<T> {
    override fun onError(e: Throwable) {
        var reason = "Network exception, please try again."
        if (e is HttpException) { // http异常
            if (e.code() == AppCons.ERROR_401_CODE) {
                _onError(AppCons.ERROR_401_CODE, "please log in again")
            } else {
                _onError(AppCons.BASE_ERROR_CODE, reason)
            }
        } else if (e is WineChainException) {
            if (!e.message.isNullOrEmpty()) {
                reason = e.message.toString()
            }
            _onError(e.code, reason)
        } else {
            _onError(AppCons.BASE_ERROR_CODE, reason)
        }
        e.printStackTrace()
    }


    override fun onNext(entity: T) {
        _onNext(entity)
    }

    abstract fun _onNext(entity: T)

    abstract fun _onError(code: Int, e: String)

    override fun onComplete() {
    }
}
