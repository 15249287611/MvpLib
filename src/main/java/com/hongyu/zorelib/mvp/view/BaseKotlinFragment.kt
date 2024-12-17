package com.hongyu.zorelib.mvp.view

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.hongyu.zorelib.R
import com.hongyu.zorelib.bean.BaseEntity
import com.hongyu.zorelib.mvp.presenter.BasePresenterCml
import com.hongyu.zorelib.utils.AppTools
import com.hongyu.zorelib.utils.DensityTools
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.Serializable
import java.lang.reflect.ParameterizedType

abstract class BaseKotlinFragment<VB : ViewBinding, P : BasePresenterCml<*>> : Fragment() {
    private var loadDialog: AlertDialog? = null
    private var isRegisterEventBus = false
    lateinit var binding: VB
    protected var presenter: P? = null
    private var firebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        logicBeforeInitView()
    }

    protected abstract fun logicBeforeInitView()
    protected fun registerEventBus() {
        isRegisterEventBus = true
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        binding = method.invoke(null, layoutInflater, container, false) as VB
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isRegisterEventBus) EventBus.getDefault().register(this)
        AppTools.notRegisteredClick(activity)
        presenter = getViewPresenter()
        onViewClicked()
        logicAfterInitView()
    }

    /**
     * 初始化之后的逻辑
     */
    protected abstract fun getViewPresenter(): P?
    protected abstract fun logicAfterInitView()
    protected abstract fun onViewClicked()

    override fun onDestroyView() {
        super.onDestroyView()
        if (isRegisterEventBus) EventBus.getDefault().unregister(this)
        presenter?.clearSubscribe()
        dismissLoad()
    }

    protected fun toastShort(msg: String?) {
        if (!TextUtils.isEmpty(msg)) {
            val toast = Toast.makeText(
                context,
                msg, Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    protected fun loading() {
        loadDialog = AlertDialog.Builder(activity).create()
        loadDialog?.window?.setDimAmount(0f) //设置昏暗度为0
        loadDialog?.setCanceledOnTouchOutside(false)
        loadDialog?.setCancelable(true)
        loadDialog?.show()
        loadDialog?.setContentView(View.inflate(activity, R.layout.dialog_loading, null))
        val params = loadDialog?.window?.attributes
        params?.width = DensityTools.dp2px(requireContext(), 120f)
        params?.height = DensityTools.dp2px(requireContext(), 120f)
        loadDialog?.window?.attributes = params
        loadDialog?.window?.setBackgroundDrawable(null)
    }

    fun dismissLoad() {
        if (loadDialog != null && loadDialog?.isShowing == true) {
            loadDialog?.dismiss()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: BaseEntity<*>) {
    }

    override fun onResume() {
        super.onResume()
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, javaClass.simpleName)
        firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    fun <T : Serializable> getSerializableExtra(bundle: Bundle?, key: String, clazz: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getSerializable(key, clazz)
        } else {
            @Suppress("DEPRECATION")
            val serializable = bundle?.getSerializable(key)
            if (clazz.isInstance(serializable)) {
                clazz.cast(serializable)
            } else {
                null
            }
        }
    }

}