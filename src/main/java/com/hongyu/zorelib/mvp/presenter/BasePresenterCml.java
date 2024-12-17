package com.hongyu.zorelib.mvp.presenter;

import android.accounts.NetworkErrorException;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.hongyu.zorelib.bean.BaseEntity;
import com.hongyu.zorelib.exception.WineChainException;
import com.hongyu.zorelib.mvp.view.BaseUI;
import com.hongyu.zorelib.utils.AppTools;
import com.hongyu.zorelib.utils.MyLanguageUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BasePresenterCml<V extends BaseUI> {
    public CompositeDisposable disposables = new CompositeDisposable();
    public V ui;

    public BasePresenterCml(@NonNull V ui) {
        this.ui = ui;
    }

    protected RequestBody getRequestBody(Map<String, Object> mapData) {
        return RequestBody.create(g.toJson(mapData), MediaType.get("application/json;charset=UTF-8"));
    }

    protected Gson g = new Gson();

    protected Context getContext() {
        if (ui != null) {
            if (ui instanceof AppCompatActivity) {
                return (AppCompatActivity) ui;
            }
            if (ui instanceof Fragment) {
                return ((Fragment) ui).getActivity();
            }
        }
        return null;
    }

    protected Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("device", 1); //1 android  2 ios
        map.put("packageName", "com.poxiao.soccer");
        if (getContext() != null) {
            map.put("device_id", AppTools.getAndroidId(getContext()));
            map.put("lang", MyLanguageUtil.getAppLanguage(getContext()));
        }
        return map;
    }

    protected <T> Observable<T> transform(Observable<BaseEntity<T>> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap((baseBean) -> {
            if (baseBean == null) {
                return Observable.error(new NetworkErrorException());
            } else if (baseBean.code == 0 && baseBean.data != null) {
                return Observable.just(baseBean.data);
            } else {
                return Observable.error(new WineChainException(baseBean.code, baseBean.msg));
            }
        });
    }

    /**
     * 页面关闭时取消订阅
     */
    public void clearSubscribe() {
        disposables.clear();
        ui = null;
    }
}
