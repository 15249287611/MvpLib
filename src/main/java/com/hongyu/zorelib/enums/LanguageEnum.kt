package com.poxiao.soccer.enums

enum class LanguageEnum(val id: String, val displayName: String) {
    /** 英语 */
    ENGLISH("en_us", "English"),
    /** 中文 */
    CHINESE("zh_cn", "中文"),
    /** 繁體中文 */
    CHINESE_FAN("zh_tw", "繁體中文"),
    /** 葡萄牙语 */
    PORTUGUESE("pt_pt", "Português"),
    /** 法语 */
    FRANCE("fr_fr", "Français"),
    /** 意大利语 */
    ITALIAN("it_it", "Italian"),
    /** 德语 */
    GERMAN("de", "Deutsch"),
    /** 西班牙语 */
    SPANISH("es", "Español"),
    /** 丹麦语 &&&&&&&&&&& en-DK*/
    DENMARK("da", "Dansk"),
    /** 印尼  &&&&&&&&&&&&  id*/
    INDONESIA("in", "Indonesia"),
    /** 瑞典 */
    SWEDISH("sv", "Svenska"),
    /** 日语 */
    JAPANESE("ja", "日本語"),
    /** 韩语 */
    KOREAN("ko", "한국어"),
    /** 土耳其语 */
    TURKISH("tr", "Türkçe"),
    /** 荷兰语 */
    NEDERLANDS("nl", "Nederlands"),
    /** 阿拉伯语 */
    ARABIC("ar", "اَلْعَرَبِيَّةُ"),
    /** 捷克语 */
    CZECH("cs", "Čeština"),
    /** 波兰语 */
    POLISH("pl", "Polski"),
    /** 俄语 */
    RUSSIAN("ru", "Россия"),
    /** 希腊语 */
    GREEK("el", "Ελληνικά"),
    /** 越南语 */
    VIETNAMESE("vi", "Tiếng Việt"),
    /** 默认语言 */
    DEFAULT_LANGUAGE("en_us", "English"),
}
