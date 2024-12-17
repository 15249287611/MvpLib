package com.hongyu.zorelib.utils;

import android.content.Context;
import android.widget.Toast;

public class MyToastUtils {
    private Context mContext; // 上下文对象

    private MyToastUtils() {
    } // 私有化构造

    private static final class Helper { // 内部帮助类，实现单例
        static final MyToastUtils INSTANCE = new MyToastUtils();
    }

    public static MyToastUtils getInstance() { // 获取单例对象
        return Helper.INSTANCE;
    }

    public static void init(Context context) { // 初始化Context
        Helper.INSTANCE.mContext = context;
    }

    public void toastShort(String  msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
