package com.hongyu.zorelib.utils;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.core.app.NotificationCompat;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.hongyu.zorelib.bean.VersionBean;

import org.json.JSONArray;
import org.json.JSONObject;

public class AppTools {

    /**
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /***
     * 去设置界面
     */
    public static void goToSetting(Context context, String str) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setMessage(str);
        normalDialog.setPositiveButton(context.getString(android.R.string.ok),
                (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                    intent.setData(uri);
                    context.startActivity(intent);
                });
        normalDialog.setNegativeButton(context.getString(android.R.string.cancel),
                (dialog, which) -> dialog.dismiss());
        normalDialog.show();
    }

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public static void showInput(Activity activity, final EditText et) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (activity == null || et == null) return;
            et.setFocusable(true);
            et.setFocusableInTouchMode(true);
            et.requestFocus();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
        }, 300);
    }

    /**
     * 显示键盘
     */
    public static void showInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        View v = activity.getWindow().peekDecorView();
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     */
    public static void hideInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = activity.getWindow().peekDecorView();
        if (v != null && imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 判断app是否安装
     */
    public static boolean isPackageExisted(Context c, String targetPackage) {
        PackageManager pm = c.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * @return 是否Google版本
     */
    public static boolean isGoogleChannel(Context context) {
        return TextUtils.equals(getChannelName(context), AppCons.GOOGLE);
    }

    /**
     * @return 是否三星版本
     */
    public static boolean isSamsungChannel(Context context) {
        return TextUtils.equals(getChannelName(context), AppCons.SAMSUNG);
    }

    /**
     *
     * @return 是否Google手机
     */
    public static boolean isGoogleDevice(){
        return Build.MANUFACTURER.equalsIgnoreCase(AppCons.GOOGLE);
    }

    /**
     * 获取渠道
     */
    public static String getChannelName(Context context) {
        String channelName = null;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                channelName = String.valueOf(applicationInfo.metaData.get("CHANNEL_ID"));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return channelName;
    }


    /***
     * 浏览屏幕N次
     */
    public static void notRegisteredClick(Context context) {
        //复合统计浏览屏幕次数
        SPTools.put(context, AppCons.PRECISE_USER_OPEN_PAGE_NUM,
                SPTools.getInteger(context, AppCons.PRECISE_USER_OPEN_PAGE_NUM, 0) + 1);
        preciseUser(context);

        String json = SPTools.getString(context, AppCons.APP_INFO_JSON, "");
        int maxLookNum = 28;
        if (!TextUtils.isEmpty(json)) {
            VersionBean versionBean = new Gson().fromJson(json, VersionBean.class);
            maxLookNum = Integer.parseInt(versionBean.getNoRegisteredNumber());
        }
        int lookNum = SPTools.getInteger(context, AppCons.LOOK_NUM, 0);
        lookNum++;
        if (lookNum >= maxLookNum) {
            lookNum = 0;
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT, "android");
            FirebaseAnalytics mAnalytics = FirebaseAnalytics.getInstance(context);
            mAnalytics.logEvent("not_registered_click", bundle);
        }
        SPTools.put(context, AppCons.LOOK_NUM, lookNum);
    }

    /***
     * 打开N次
     */
    public static void appOpen(Context context) {
        //复合统计app打开次数
        SPTools.put(context, AppCons.PRECISE_USER_OPEN_APP_NUM, SPTools.getInteger(context, AppCons.PRECISE_USER_OPEN_APP_NUM, 0) + 1);
        preciseUser(context);

        String json = SPTools.getString(context, AppCons.APP_INFO_JSON, "");
        int maxNum = 4;
        if (!TextUtils.isEmpty(json)) {
            VersionBean versionBean = new Gson().fromJson(json, VersionBean.class);
            maxNum = versionBean.getAppOpenNumber();
        }
        int openNum = SPTools.getInteger(context, AppCons.OPEN_NUM, 0);
        openNum++;
        if (openNum >= maxNum) {
            openNum = 0;
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT, "android");
            FirebaseAnalytics mAnalytics = FirebaseAnalytics.getInstance(context);
            mAnalytics.logEvent("app_open", bundle);
        }
        SPTools.put(context, AppCons.OPEN_NUM, openNum);
    }

    /***
     * 复合统计
     * 1、APP内点击新页面次数超过N次（后台填写）
     * 2、打开APP次数（后台填写）
     * 3、使用APP时长（后台填写）
     */
    public static void preciseUser(Context context) {
        String json = SPTools.getString(context, AppCons.APP_INFO_JSON, "");
        int appClick = 4;
        int appTimes = 240;
        int clickNumber = 28;
        if (!TextUtils.isEmpty(json)) {
            VersionBean versionBean = new Gson().fromJson(json, VersionBean.class);
            VersionBean.PreciseUser preciseUser = versionBean.getPreciseUser();
            if (preciseUser != null) {
                appClick = preciseUser.getAppClick();
                appTimes = preciseUser.getAppTimes();
                clickNumber = preciseUser.getClickNumber();
            }
        }
        int openPageNum = SPTools.getInteger(context, AppCons.PRECISE_USER_OPEN_PAGE_NUM, 0);
        int openAppNum = SPTools.getInteger(context, AppCons.PRECISE_USER_OPEN_APP_NUM, 0);
        int viewTime = SPTools.getInteger(context, AppCons.PRECISE_USER_VIEW_TIME, 0);
        if (openPageNum >= clickNumber && openAppNum >= appClick && viewTime >= appTimes) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT, "android");
            FirebaseAnalytics mAnalytics = FirebaseAnalytics.getInstance(context);
            mAnalytics.logEvent("precise_user", bundle);
            SPTools.put(context, AppCons.PRECISE_USER_OPEN_PAGE_NUM, 0);
            SPTools.put(context, AppCons.PRECISE_USER_OPEN_APP_NUM, 0);
            SPTools.put(context, AppCons.PRECISE_USER_VIEW_TIME, 0);
        }
    }

    /**
     * @return 是否是json
     */
    public static Boolean isValidJson(String str) {
        if (TextUtils.isEmpty(str)) return false;
        try {
            new JSONObject(str);
        } catch (Exception e) {
            try {
                new JSONArray(str);
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取设备id
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static int[] convertStringToIntArray(String str) {
        int[] intArray = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            intArray[i] = Character.getNumericValue(str.charAt(i));
        }

        return intArray;
    }

    // 计算多个整数的最小公倍数
    public static int lcm(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }

    // 计算两个整数的最小公倍数
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    // 计算两个整数的最大公约数
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static String transformNumber(String input) {
        StringBuilder result = new StringBuilder();

        // 遍历输入的每个字符
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int number = Character.getNumericValue(ch); // 获取字符对应的整数值

            // 将字符重复其整数值那么多次，并添加到结果字符串中
            for (int j = 0; j < number; j++) {
                result.append(ch);
            }
        }

        return result.toString();
    }

}
