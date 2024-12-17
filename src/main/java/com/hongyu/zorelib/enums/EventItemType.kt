package com.hongyu.zorelib.enums

/**
 * 事件类型
 */
enum class EventItemType {
    OAUTH_REGISTER,  //第三方注册
    REGISTER,     //注册
    UNLOCK_PLAYER,      //解锁玩家方案
    UNLOCK_EXPERT,      //解锁专家方案
    AD_SPLASH,      //开屏页广告
    AD_MATCH_LIST   ,   //比赛列表广告
    DATA_SEARCH_PAGE,  //数据搜索页面
    EXPERT_SEARCH_PAGE,  //专家搜索页面
    FOLLOW_MATCH,  //关注比赛
    FOLLOW_EXPERT,  //关注专家
    FOLLOW_PLAYER,  //关注玩家
    FOLLOW_TEAM,  //关注球队
    SHARE_MATCH,    //分享比赛
    SHARE_EXPERT,   //分享专家
    SHARE_EXPERT_TIPS,   //分享专家方案
    SHARE_PLAYER,   //分享玩家
    SHARE_PLAYER_TIPS,   //分享玩家方案
    SHARE_TEAM,     //分享球队
    SHARE_LEAGUE,     //分享联赛
    SHARE_INVITE,     //分享邀请好友

}