# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/isle/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class com.mob.**{*;}
-keep class cn.smssdk.**{*;}
-dontwarn com.mob.**


# 保留 androidx.core 包下的所有类
-keep class androidx.core.** { *; }
-keep interface androidx.core.** { *; }


#Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*

-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn okio.**
-dontwarn org.apache.commons.codec.binary.**


#eventbus 添加混淆
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# And if you use AsyncExecutor:
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#PictureSelector 3.0
-keep class com.luck.picture.lib.** { *; }
#Ucrop库
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

#微信支付混淆
-keep class com.tencent.mm.opensdk.** { *; }

-keep class com.tencent.wxop.** { *; }

-keep class com.tencent.mm.sdk.** { *; }

#appsflyer混淆
-keep class com.appsflyer.** { *; }
#googleplay 使用appsflyer是添加混淆
-keep public class com.android.installreferrer.** { *; }

-keep public class com.google.android.gms.** { public protected *; }


# 保留 Google Play 服务相关类，避免在混淆过程中被重命名或删除
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# 保留 AndroidX 和 Android 核心系统类，确保不会影响应用功能
-keep class androidx.core.** { *; }
-keep class android.content.pm.** { *; }
-keep class android.app.ApplicationPackageManager { *; }


