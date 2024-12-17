package com.hongyu.zorelib.bean;

import java.math.BigDecimal;

public class AdDataBean {
    private int offer_id;
    private int redirect_type; //跳转方式：1-inApp，2-webview，3-浏览器
    private String redirect_location;
    private String material;
    private BigDecimal bid_price; //出价
    private String advertisers; //广告主
    private String ad_type; //广告类型
    private String ad_style; //广告样式
    private int is_additional_traffic = 0; //是否补充流量

    // Getters and Setters
    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public int getRedirect_type() {
        return redirect_type;
    }

    public void setRedirect_type(int redirect_type) {
        this.redirect_type = redirect_type;
    }

    public String getRedirect_location() {
        return redirect_location;
    }

    public void setRedirect_location(String redirect_location) {
        this.redirect_location = redirect_location;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getBid_price() {
        return bid_price;
    }

    public void setBid_price(BigDecimal bid_price) {
        this.bid_price = bid_price;
    }

    public String getAdvertisers() {
        return advertisers;
    }

    public void setAdvertisers(String advertisers) {
        this.advertisers = advertisers;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_style() {
        return ad_style;
    }

    public void setAd_style(String ad_style) {
        this.ad_style = ad_style;
    }

    public int getIs_additional_traffic() {
        return is_additional_traffic;
    }

    public void setIs_additional_traffic(int is_additional_traffic) {
        this.is_additional_traffic = is_additional_traffic;
    }
}
