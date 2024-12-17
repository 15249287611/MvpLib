package com.hongyu.zorelib.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.text.TextUtils;

import com.hongyu.zorelib.bean.LanguageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 系统语言工具类
 */
public class MyLanguageUtil {

    public static void setLang(Context context, String myLangId) {
        SPTools.put(context, AppCons.LANG, myLangId);
    }

    /**
     * 中国 zh-CN
     * 美国 en-US
     *
     * @return 当前app语言
     */
    public static String getAppLanguage(Context context) {
        if (context == null) return AppCons.ENGLISH_ID;
        String myLangId = SPTools.getString(context, AppCons.LANG, "");
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        Locale locale = config.getLocales().get(0);
        if (TextUtils.isEmpty(myLangId)) {
            switch (locale.getLanguage()) {
                case "zh": //中文
                    if ("CN".equalsIgnoreCase(locale.getCountry())) {//简体中文
                        myLangId = AppCons.CHINESE_ID;
                    } else {//繁体中文
                        myLangId = AppCons.CHINESE_FAN_ID;
                    }
                    break;
                case "fr": //法语
                    myLangId = AppCons.FRANCE_ID;
                    break;
                case "pt": //葡萄牙
                    myLangId = AppCons.PORTUGUESE_ID;
                    break;
                case "it": //意大利语
                    myLangId = AppCons.ITALIAN_ID;
                    break;
                case AppCons.GERMAN_ID: //德语
                case AppCons.SPANISH_ID: //葡萄牙语
                case AppCons.DENMARK_ID: //丹麦语
                case AppCons.INDONESIA_ID: //印尼语
                case AppCons.SWEDISH_ID: //瑞典语
                case AppCons.JAPANESE_ID: //日语
                case AppCons.KOREAN_ID: //韩语
                case AppCons.TURKISH_ID: //土耳其语
                case AppCons.NEDERLANDS_ID: //荷兰语
                case AppCons.ARABIC_ID: //阿拉伯语
                case AppCons.CZECH_ID: //捷克语
                case AppCons.POLISH_ID: //波兰语
                case AppCons.RUSSIAN_ID: //俄语
                case AppCons.GREEK_ID: //希腊语
                case AppCons.VIETNAMESE_ID: //越南语
                    myLangId = locale.getLanguage();
                    break;
                default: //英语
                    myLangId = AppCons.ENGLISH_ID;
                    break;
            }
        }
        return myLangId;
    }

    /**
     * 是否是中文
     *
     * @return true 是中文
     */
    public static boolean isChinese(Context context) {
        return TextUtils.equals(getAppLanguage(context), AppCons.CHINESE_ID);
    }

    public static String getLanguageName(String id) {
        String name = AppCons.ENGLISH;
        for (LanguageBean bean : getLanguageData()) {
            if (TextUtils.equals(bean.getId(), id)) {
                name = bean.getName();
            }
        }
        return name;
    }


    /**
     * 更新app语言
     */
    public static Context updateResources(Context context) {
        //设置应用语言类型
        String langID = getAppLanguage(context);
        List<LanguageBean> languageData = getLanguageData();
        Locale locale = Locale.ENGLISH;
        for (LanguageBean bean : languageData) {
            if (TextUtils.equals(langID, bean.getId())) {
                locale = bean.getLocale();
            }
        }
        Locale.setDefault(locale);
        return updateResources(context, locale);
    }

    private static Context updateResources(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    /**
     * 支持的所有语言
     */
    public static List<LanguageBean> getLanguageData() {
        String[] languages = {
                AppCons.ENGLISH, AppCons.PORTUGUESE, AppCons.FRANCE, AppCons.ITALIAN,
                AppCons.CHINESE, AppCons.CHINESE_FAN, AppCons.GERMAN, AppCons.SPANISH,
                AppCons.DANSK, AppCons.INDONESIA, AppCons.SVENSKA, AppCons.JAPANESE,
                AppCons.KOREAN, AppCons.TURKISH, AppCons.NEDERLANDS, AppCons.ARABIC,
                AppCons.CZECH, AppCons.POLISH, AppCons.RUSSIAN, AppCons.GREEK, AppCons.VIETNAMESE
        };
        String[] languageIds = {
                AppCons.ENGLISH_ID, AppCons.PORTUGUESE_ID, AppCons.FRANCE_ID, AppCons.ITALIAN_ID,
                AppCons.CHINESE_ID, AppCons.CHINESE_FAN_ID, AppCons.GERMAN_ID, AppCons.SPANISH_ID,
                AppCons.DENMARK_ID, AppCons.INDONESIA_ID, AppCons.SWEDISH_ID, AppCons.JAPANESE_ID,
                AppCons.KOREAN_ID, AppCons.TURKISH_ID, AppCons.NEDERLANDS_ID, AppCons.ARABIC_ID,
                AppCons.CZECH_ID, AppCons.POLISH_ID, AppCons.RUSSIAN_ID, AppCons.GREEK_ID, AppCons.VIETNAMESE_ID
        };
        List<Locale> locales = new ArrayList<>();
        for (String langId : languageIds) {
            switch (langId) {
                case AppCons.ENGLISH_ID:
                    locales.add(Locale.ENGLISH);
                    break;
                case AppCons.CHINESE_ID: //简体中文
                    locales.add(Locale.SIMPLIFIED_CHINESE);
                    break;
                case AppCons.CHINESE_FAN_ID: //繁体
                    locales.add(Locale.TRADITIONAL_CHINESE);
                    break;
                case AppCons.FRANCE_ID: //法语
                    locales.add(Locale.FRENCH);
                    break;
                case AppCons.ITALIAN_ID: //意大利语
                    locales.add(Locale.ITALIAN);
                    break;
                case AppCons.PORTUGUESE_ID: //葡萄牙
                    locales.add(new Locale("pt", ""));
                    break;
                case AppCons.INDONESIA_ID: //印尼语
                    locales.add(new Locale("id", ""));
                    break;
                default:
                    locales.add(new Locale(langId, ""));
                    break;
            }
        }
        List<LanguageBean> languageBeans = new ArrayList<>();
        for (int i = 0; i < languages.length; i++) {
            languageBeans.add(new LanguageBean(languages[i], languageIds[i], locales.get(i)));
        }
        return languageBeans;
    }
}
