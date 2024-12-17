package com.hongyu.zorelib.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.appsflyer.AFInAppEventParameterName
import com.appsflyer.AFInAppEventType
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.hongyu.zorelib.bean.PaySuccessBean
import com.hongyu.zorelib.enums.EventItemType


/**
 * 统计埋点工具类
 */
object MyEventUtils {
    //注册事件
    fun registerEvent(context: Context) {
        putEvent(context, AFInAppEventType.COMPLETE_REGISTRATION, hashMapOf())
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT, "android")
        val mAnalytics = FirebaseAnalytics.getInstance(context)
        mAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle)
    }

    //登陆事件
    fun loginEvent(context: Context) {
        putEvent(context, AFInAppEventType.LOGIN, hashMapOf())
    }

    //成功开通会员
    fun svipEvent(context: Context) {
        putEvent(context, "fba_membership", hashMapOf())
    }

    //成功充值球币
    fun coinsEvent(context: Context) {
        putEvent(context, "fba_deposit", hashMapOf())
    }

    //成功验证邮箱
    fun verifyEmailEvent(context: Context, typeName: EventItemType) {
        val eventValues = HashMap<String, Any>()
        eventValues["type"] = typeName.name
        putEvent(context, "fba_verify_email", eventValues)
    }

    //解锁预测
    fun unlockPredictionsEvent(context: Context, typeName: EventItemType) {
        val eventValues = HashMap<String, Any>()
        eventValues["type"] = typeName.name
        putEvent(context, "fba_unlock_predictions", eventValues)
    }

    fun playEvent(context: Context, paySuccessBean: PaySuccessBean) {
        val eventValues = HashMap<String, Any>()
        eventValues[AFInAppEventParameterName.CONTENT_ID] = paySuccessBean.sku
        eventValues[AFInAppEventParameterName.CONTENT_TYPE] = paySuccessBean.production_name  //产品名称
        eventValues[AFInAppEventParameterName.CURRENCY] = paySuccessBean.currency    //货币类型
        eventValues[AFInAppEventParameterName.REVENUE] = paySuccessBean.revenue   //收入

        AppsFlyerLib.getInstance().logEvent(
            context,
            AFInAppEventType.PURCHASE, eventValues
        )
    }

    private fun putEvent(context: Context, eventName: String, eventValues: HashMap<String, Any>) {
        AppsFlyerLib.getInstance().logEvent(
            context,
            eventName, eventValues, object : AppsFlyerRequestListener {
                override fun onSuccess() {
//                    Log.e("ssssss", "event_success")
                }

                override fun onError(p0: Int, p1: String) {
//                    Log.e("ssssss", "event_onError: $p1")
                }

            }
        )
        val bundle = Bundle()
        eventValues.forEach { (key, value) ->
            when (value) {
                is Int -> bundle.putInt(key, value)
                is String -> bundle.putString(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                else -> throw IllegalArgumentException("Unsupported bundle component ($key, $value)")
            }
        }
        FirebaseAnalytics.getInstance(context).logEvent(eventName, bundle)
    }

    /**
     * 分享
     */
    fun putShareEvent(context: Context, type: EventItemType) {
        val bundle = Bundle()
        bundle.putString("type", type.name)
        putFireBaseEvent(context, "fba_share", bundle)
    }


    fun putFollowEvent(context: Context, type: EventItemType) {
        val bundle = Bundle()
        bundle.putString("type", type.name)
        putFireBaseEvent(context, "fba_follow", bundle)
    }


    /**
     * 签到页面展示
     */
    fun putCheckInOpenEvent(context: Context) {
        putFireBaseEvent(context, "fba_open_check_in", Bundle())
    }

    fun putSearchOpenEvent(context: Context, type: EventItemType) {
        val bundle = Bundle()
        bundle.putString("type", type.name)
        putFireBaseEvent(context, "fba_open_search", bundle)
    }

    fun putSvipOpenEvent(context: Context, eventBundle: Bundle) {
        putFireBaseEvent(context, "fba_open_svip", eventBundle)
    }

    fun putClickPaySvipEvent(context: Context, eventValue: String) {
        val bundle = Bundle()
        bundle.putString("channel", eventValue)
        putFireBaseEvent(context, "fba_click_pay_svip", bundle)
    }

    fun putCoinsOpenEvent(context: Context, eventValue: String) {
        val bundle = Bundle()
        bundle.putString("channel", eventValue)
        putFireBaseEvent(context, "fba_open_coins", Bundle())
    }

    fun putClickPayCoinsEvent(context: Context, channel: String, paymentMethod: String) {
        val bundle = Bundle()
        bundle.putString("channel", channel)
        bundle.putString("payment_method", paymentMethod)
        putFireBaseEvent(context, "fba_click_pay_coins", bundle)
    }


    private fun putFireBaseEvent(context: Context, eventName: String, eventBundle: Bundle) {
        val mAnalytics = FirebaseAnalytics.getInstance(context)
        mAnalytics.logEvent(eventName, eventBundle)
    }

}