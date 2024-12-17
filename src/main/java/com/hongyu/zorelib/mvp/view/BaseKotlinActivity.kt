package com.hongyu.zorelib.mvp.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hongyu.zorelib.R
import com.hongyu.zorelib.bean.BaseEntity
import com.hongyu.zorelib.mvp.presenter.BasePresenterCml
import com.hongyu.zorelib.utils.ActivityManage
import com.hongyu.zorelib.utils.AppTools
import com.hongyu.zorelib.utils.DensityTools
import com.hongyu.zorelib.utils.MyLanguageUtil
import com.hongyu.zorelib.utils.StatusBarUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.Serializable
import java.lang.reflect.ParameterizedType

abstract class BaseKotlinActivity<VB : ViewBinding, P : BasePresenterCml<*>> : AppCompatActivity() {
    private var loadDialog: AlertDialog? = null
    private var isRegisterEventBus = false
    protected var presenter: P? = null
    protected lateinit var binding: VB

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBarColor(this, R.color.color_green)
        loadDialog = AlertDialog.Builder(this).create()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppTools.notRegisteredClick(this)
        ActivityManage.addActivity(this)
        logicBeforeInitView()
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as VB
        setContentView(binding.root)
        if (isRegisterEventBus) EventBus.getDefault().register(this)
        presenter = getViewPresenter()
        onViewClicked()
        logicAfterInitView()
    }

    protected fun registerEventBus() {
        isRegisterEventBus = true
    }


    /**
     * 初始化之前的逻辑
     */
    protected abstract fun logicBeforeInitView()

    protected abstract fun getViewPresenter(): P?

    /**
     * 初始化之后的逻辑
     */
    protected abstract fun logicAfterInitView()
    protected abstract fun onViewClicked()

    override fun onDestroy() {
        super.onDestroy()
        ActivityManage.finishSingleActivity(this)
        if (isRegisterEventBus) EventBus.getDefault().unregister(this)
        presenter?.clearSubscribe()
        dismissLoad()
    }

    protected fun toastShort(msg: String?) {
        if (!TextUtils.isEmpty(msg)) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toastShort(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("InflateParams")
    fun loading() {
        if (loadDialog?.isShowing == true) {
            return
        }
        loadDialog = AlertDialog.Builder(this).create()
        loadDialog?.window?.setDimAmount(0F)
        loadDialog?.setCanceledOnTouchOutside(false)
        loadDialog?.setCancelable(true)
        loadDialog?.show()
        loadDialog?.setContentView(layoutInflater.inflate(R.layout.dialog_loading, null))
        val params = loadDialog?.window?.attributes
        params?.width = DensityTools.dp2px(this, 200f)
        params?.height = DensityTools.dp2px(this, 200f)
        loadDialog?.window?.attributes = params
        loadDialog?.window?.setBackgroundDrawable(null)
    }

    fun dismissLoad() {
        if (loadDialog?.isShowing == true) {
            loadDialog?.dismiss()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onBaseEntityEvent(ignoredEvent: BaseEntity<*>) {
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(MyLanguageUtil.updateResources(newBase))
    }

    fun <T : Serializable> getSerializableExtra(intent: Intent?, key: String, clazz: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra(key, clazz)
        } else {
            @Suppress("DEPRECATION")
            val serializable = intent?.getSerializableExtra(key)
            if (clazz.isInstance(serializable)) {
                clazz.cast(serializable)
            } else {
                null
            }
        }
    }
}