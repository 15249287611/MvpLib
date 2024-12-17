package com.hongyu.zorelib.utils

/**
 * 应用常量
 */
interface AppCons {
    companion object {

        const val BI_INVITE = "invite"//主页邀请好友
        const val BI_FREE_TIPSTERS = "free_tipsters"//主页免费专家方案
        const val FAV_TIPSTER = "fav_tipster"//主页免费专家方案
        const val BI_FAV_GAMERS = "fav_gamers"//主页免费专家方案
        const val BI_FAV_FANS = "fav_fans"//主页免费专家方案
        const val BI_FAV_CLUB = "fav_club"//主页免费专家方案
        const val BI_GAME = "games"//主页免费专家方案
        const val BI_NOTIFICATION = "notification"// 通知

        //        const val BI_SEARCH_HOT_GAMES = "search_hot_games" // 搜索中热门比赛列表
        const val BI_TIPSTER_TIPS_DETAIL = "tipster_tips_detail" // 专家方案详情
        const val BI_AI_PREDICTION = "ai_prediction"//主页ai预测
        const val BI_HOME_HOT_GAMES = "home_hot_games" // 首页热门比赛列表
        const val BI_HOT_MATCHES = "hot_matches" // 比赛预测列表

        //        const val BI_LEAGUE_INDEX = "league_index" // 联赛主页
//        const val BI_CLUB_INDEX = "club_index" // 球队主页
        const val BI_GAME_DETAIL = "game_detail"// 比赛详情

        //        const val BI_PLAYER_INDEX = "player_index"// 玩家主页列表
//        const val BI_HIGH_ACCURACY = "high_accuracy" // 擅长联赛列表
//        const val BI_DAIL_REPORT = "dail_report" // 战报
//        const val BI_EXPERT_TEAM_TIPS = "expert_team_tips" // 专家团推单列表
//        const val BI_EXPERT_TEAM_MEMBER = "expert_team_member" // 专家团团员详情页
//        const val BI_POINTS_MALL = "points_mall" //积分交易记录
//        const val BI_PUBLISH_TIPS = "publish_tips" //玩家推单成功跳转
        const val BI_OTHER = "Other"
        const val BI_ACCOUNT_FAV_TIPSTER = "account_fav_tipster"
        const val BI_TIPSTER_LIST = "tipster_list"    //专家列表页(多个页面)
        const val BI_HOME_TIPSTER = "home_tipster"    //专家列表页(多个页面)
        const val BI_TIPSTERS = "tipsters"    //明星专家
        const val BI_MATCH_DETAILS_TIPS = "match_detail_tips" //比赛详情-预测页面
        const val BI_USER_FOLLOW = "user_follow" //用户关注页面
        const val BI_HOME = "home" //首页
        const val BI_PLAYER_RANK_LIST = "player_rank_list" //玩家榜单
        const val BI_HOME_TAB = "home_tab" //首页
        const val BI_ACCOUNT_PAGE = "account_page" //个人中心
        const val BI_VIP_INVITE = "vip_invite" //vip页面
        const val BI_ACCOUNT_INFO = "account_info" //个人资料
        const val BI_INVITE_LINK = "invite_link" // "链接
        const val BI_INVITE_CODE = "invite_code" // 邀请码
        const val BI_INVITE_PIC = "invite_pic" //图片直接分享
        const val BI_INVITE_FB = "invite_fb" //Facebook
        const val BI_INVITE_X = "invite_x" //Twitter
        const val BI_INVITE_INS = "invite_ins" //instagram

        const val BI_NEW_TASK = "new_task" // 新手任务
        const val BI_FREE_TIPSTERS_PAGE = "free_tipsters_page" // 免费解锁专家方案页
        const val BI_GAME_PAGE = "game" // 比分页
        const val BI_HOME_ACTIVE_BUTTON = "home_active_button" // 首页下方SVIP权益版块
        const val BI_SIGN_IN = "sign_in" // 签到页

        // ai_prediction细分来源
        const val BI_AI_PREDICTION_ALERT = "ai_prediction_alert" // AI预警
        const val BI_AI_PREDICTION_LM = "ai_prediction_lm" // 绝杀预测
        const val BI_AI_PREDICTION_SCORE = "ai_prediction_score" // 比分预测
        const val BI_AI_PREDICTION_GOAL = "ai_prediction_goal" // 进球数预测

        // 比分页-game细分来源
        const val BI_GAME_LAST_MINUTE = "game_last_minute" // 比分页-进行中 绝杀模型
        const val BI_GAME_OVER_GOAL = "game_over_goal" // 比分页-进行中 大球预测模型

        const val BI_TIPSTER = "tipster" // 专家
        const val BI_PLAYER = "player" // 玩家
        const val BI_ACCOUNT = "account" // 个人中心

        const val BI_TIPSTER_HOME = "tipster_home" // 专家主页

        const val BI_PLAYER_HOME = "player_home" // 玩家主页
        const val BI_MATCH_DETAIL_TIPS_PLAYER = "match_detail_tips_player" // 比赛详情-玩家方案
        const val BI_HOT_MATCH_PAGE = "hot_match_page" // 热门比赛预测页

        const val BI_ACCOUNT_HOME = "account_home" // 个人中心主页
        const val BI_ACCOUNT_COINS = "account_coins" // 个人中心-球币主页



        const val FROM = "from"
        const val FROM_SUB = "from_sub"
        const val SVIP_GAME = "svip_game"
        const val SVIP_PLAN = "svip_plan"


        const val FACEBOOK = "facebook"
        const val BASE_ERROR_CODE = -10000
        const val ERROR_401_CODE = 401
        const val COMPANY_365_ID = "8" //bet365公司ID
        const val APPSFLYER_DEV_KEY = "MFp3ftBYWBaYf7VMfEAjqN" //appsFlyer_Key
        const val APP_ACCESS_KEY = "AKIAQXAC4WLGU2Q4YWMA"
        const val APP_SECRET_KEY = "OMWRc8BLIJRZV6lRcKd4OgKKJBhKDY"
        const val BUCKET_NAME = "24h-soccer-win" //阿里oss空间名称地址
        const val EXPERT_GROUP_AVATAR = "ExpertGroupAvatar/" //阿里oss专家团头像地址
        const val SHARE_FACEBOOK_IMAGE = "ShareFacebookImage/" //阿里oss facebook分享图片地址
        const val USER_AVATAR = "images/avatar/user/" //阿里oss用户头像地址
        const val SEARCH_RECORDS = "search_records"
        const val DATE_SEARCH_RECORDS = "date_search_records"
        const val TIME_TYPE = "yyyy-MM-dd HH:mm:ss"
        const val TIME_TYPE_000 = "yyyy-MM-dd 00:00:00"
        const val TIME_YYYYMMDD = "yyyy/MM/dd"
        const val TIME_YYYYMM = "yyyy/MM"
        const val TIME_YYYYMMDD_HHMM = "yyyy/MM/dd HH:mm"
        const val TIME_MMDD_HHMM = "MM/dd HH:mm"
        const val TIME_HH_MM = "HH:mm"
        const val GOOGLE = "google"    // 渠道名称
        const val SAMSUNG = "samsung"
        const val APP_INFO_JSON = "app_info_json"
        const val OPEN_NUM = "open_num" //打开app次数
        const val LOOK_NUM = "look_num" //打开新页面次数
        const val PRECISE_USER_OPEN_PAGE_NUM = "precise_user_open_page_num" //APP内点击新页面次数超过N次
        const val PRECISE_USER_OPEN_APP_NUM = "precise_user_open_app_num" //打开APP次数
        const val PRECISE_USER_VIEW_TIME = "precise_user_view_time" //使用APP时长
        const val STR_DEFAULT = " - "
        const val EXPERT_ID = "ExpertId"
        const val MATCH_ID = "matchId"
        const val MATCH_STATE = "matchState"
        const val ROUND_ID = "RoundId"
        const val RECOMMEND_ID = "RecommendId"
        const val STATUS = "Status"
        const val PUBLISH_TIPS_TIME = "publish_tips_time"
        const val LANG = "lang" //语言
        const val MATCHES_DETAILS_PREDICTION_OPENSVIP =
            "matches_details_prediction_opensvip" //比赛详情(AI预测)
        const val AI_ALERT_HOME_OPENSVIP = "ai_alert_home_opensvip" //Ai预警
        const val ACCOUNT_OPENSVIP = "account_opensvip" //个人中心
        const val REGISTER_TRY_FREE_OPENSVIP = "register_try_free_opensvip" //注册跳转svip
        const val MATCHES_LIST_INPLAY_LORE_OPENSVIP = "matches_list_inplay_lore_opensvip"//比赛列表绝杀
        const val MATCHES_LIST_INPLAY_GOALS_OPENSVIP = "matches_list_inplay_goals_opensvip"//比赛列表绝杀
        const val SIGN_IN_OPENSVIP = "sign_in_opensvip" //签到
        const val DAILY_TIPSTER_0_OPENSVIP = "daily_tipster_0_opensvip" //svip0元购
        const val NEW_TASK_OPENSVIP = "new_task_opensvip" //新手任务
        const val HOME_BANNER_OPENSVIP = "home_banner_opensvip"  //首页banner图
        const val HOME_OPENSVIP = "home_opensvip"   //首页开通svip
        const val AD_SLOTS_LIST = "ad_Slots_list" //sptools 中广告位列表key
        const val AD_ID_SPLASH = "ad-1-000001" //启动页广告位
        const val AD_ID_IN_PLAY = "ad-1-000003" //比分列表广告位
        const val AD_ID_WILL = "ad-1-000004" //未开赛列表广告位
        const val AD_ID_MATCH_DETAILS = "ad-1-000005" //比赛详情广告位
        const val AD_ID_TIPSTERS_BANNER = "ad-1-000006" //专家栏目主页banner广告位
        const val MATICOO_APPID = "12083"
        const val MATICOO_SERVER_URL = "https://deapi.funsdata.com/v1/poxiao/report"

        const val ENGLISH_ID = "en_us" //英语
        const val ENGLISH = "English"
        const val CHINESE_ID = "zh_cn" //中文
        const val CHINESE = "中文"
        const val CHINESE_FAN_ID = "zh_tw" //繁體中文
        const val CHINESE_FAN = "繁體中文"
        const val PORTUGUESE_ID = "pt_pt" //葡萄牙语
        const val PORTUGUESE = "Português"
        const val FRANCE_ID = "fr_fr" //法语
        const val FRANCE = "Français"
        const val ITALIAN_ID = "it_it" //意大利语
        const val ITALIAN = "Italian"
        const val GERMAN_ID = "de" //德语
        const val GERMAN = "Deutsch"
        const val SPANISH_ID = "es" //西班牙语
        const val SPANISH = "Español"
        const val DENMARK_ID = "da" //丹麦语    en-DK
        const val DANSK = "Dansk"
        const val INDONESIA_ID = "in" //印尼    ---id
        const val INDONESIA = "Indonesia"
        const val SWEDISH_ID = "sv" //瑞典
        const val SVENSKA = "Svenska"
        const val JAPANESE_ID = "ja" //日语
        const val JAPANESE = "日本語"
        const val KOREAN_ID = "ko" //韩语
        const val KOREAN = "한국어"
        const val TURKISH_ID = "tr" //土耳其语
        const val TURKISH = "Türkçe"
        const val NEDERLANDS_ID = "nl" //荷兰语
        const val NEDERLANDS = "Nederlands"
        const val ARABIC_ID = "ar" //阿拉伯语
        const val ARABIC = "اَلْعَرَبِيَّةُ"
        const val CZECH_ID = "cs" //捷克语
        const val CZECH = "Čeština"
        const val POLISH_ID = "pl" //波兰语
        const val POLISH = "Polski"
        const val RUSSIAN_ID = "ru" //俄语
        const val RUSSIAN = "Россия"
        const val GREEK_ID = "el" //希腊语
        const val GREEK = "Ελληνικά"
        const val VIETNAMESE_ID = "vi" //越南语
        const val VIETNAMESE = "Tiếng Việt"

        //微信id
        const val WECHAT_ID = "wx0a1c642c7dcf24dc"

        //stripe key 正式
        const val PUBLISHABLE_KEY =
            "pk_live_51Hs44IBvmGCiPCdeIDXK1uOfNivG19FumTaZKURcnllkutrK54JrIKthH3hElSti8ik5s4ucue2uYApIrSyVp8fJ006kQoDW6G"
        //stripe key 测试
//        const val PUBLISHABLE_KEY = "pk_test_51Hs44IBvmGCiPCdeA6IGR45nj7vWxReFSlKfOwkwrnxVNXTKKVLwvLsiGQEHKN5olpBCB5ikBJC74sLT13mz0T0Q00Haa3vy8H";

    }
}