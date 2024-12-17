package com.hongyu.zorelib.bean;

import java.util.Locale;

public class LanguageBean {
    private String name;
    private String id;
    private boolean isSelect;
    private Locale locale;

    public LanguageBean(String name, String id, Locale locale) {
        this.name = name;
        this.id = id;
        this.locale = locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
