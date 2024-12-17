package com.hongyu.zorelib.bean;

import android.text.TextUtils;

import com.hongyu.zorelib.utils.MyTimeUtils;

public class VersionBean {
    private String versionName;
    private int versionCode;
    private String versionDes;
    private String versionUrl;
    private int isForcedUpdate; //0 非强制   1强制更新
    private boolean interfaceTimeStatisticsIsEnable; //是否收集 接口信息
    private String noRegisteredNumber;
    private String appOpenNumber;
    private String viewTimes;  //单位s
    private PreciseUser preciseUser;
    private int isScreenAd;// 1 开启   0 关闭
    private boolean isEnableComment;// 是否开启评论
    private int closeCommentTime;//关闭评论的时间分钟

    public long getCloseCommentTime() {
        return MyTimeUtils.get1Minute() * closeCommentTime;
    }

    public void setCloseCommentTime(int closeCommentTime) {
        this.closeCommentTime = closeCommentTime;
    }

    public boolean isEnableComment() {
        return isEnableComment;
    }

    public void setEnableComment(boolean enableComment) {
        isEnableComment = enableComment;
    }

    public int getIsScreenAd() {
        return isScreenAd;
    }

    public void setIsScreenAd(int isScreenAd) {
        this.isScreenAd = isScreenAd;
    }

    public PreciseUser getPreciseUser() {
        return preciseUser;
    }

    public int getAppOpenNumber() {
        return TextUtils.isEmpty(appOpenNumber) ? 4 : Integer.parseInt(appOpenNumber);
    }

    public void setAppOpenNumber(String appOpenNumber) {
        this.appOpenNumber = appOpenNumber;
    }

    public int getViewTimes() {
        return TextUtils.isEmpty(viewTimes) ? 240 : Integer.parseInt(viewTimes);
    }

    public void setViewTimes(String viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String getNoRegisteredNumber() {
        return TextUtils.isEmpty(noRegisteredNumber) ? "28" : noRegisteredNumber;
    }

    public void setNoRegisteredNumber(String noRegisteredNumber) {
        this.noRegisteredNumber = noRegisteredNumber;
    }

    public boolean isInterfaceTimeStatisticsIsEnable() {
        return interfaceTimeStatisticsIsEnable;
    }

    public void setInterfaceTimeStatisticsIsEnable(boolean interfaceTimeStatisticsIsEnable) {
        this.interfaceTimeStatisticsIsEnable = interfaceTimeStatisticsIsEnable;
    }

    public boolean isForcedUpdate() {
        return isForcedUpdate == 1;
    }

    public void setForcedUpdate(int forcedUpdate) {
        isForcedUpdate = forcedUpdate;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionDes() {
        return versionDes;
    }

    public void setVersionDes(String versionDes) {
        this.versionDes = versionDes;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public static class PreciseUser {
        private int ClickNumber;//APP内点击新页面次数超过N次
        private int appClick;//打开APP次数
        private int appTimes;//使用APP时长

        public int getClickNumber() {
            return ClickNumber == 0 ? 28 : ClickNumber;
        }

        public int getAppClick() {
            return appClick == 0 ? 4 : appClick;
        }

        public int getAppTimes() {
            return appTimes == 0 ? 240 : appTimes;
        }
    }
}
