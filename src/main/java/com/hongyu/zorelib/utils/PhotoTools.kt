package com.hongyu.zorelib.utils

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.engine.CompressFileEngine
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.utils.ActivityCompatHelper
import top.zibin.luban.Luban
import top.zibin.luban.OnNewCompressListener
import java.io.File

/**
 * 图片处理工具类
 */
object PhotoTools {

    fun selectPhoto(activity: AppCompatActivity, listener: OnPhotoSelectListener) {
        openSelectPhoto(PictureSelector.create(activity), listener)
    }

    private fun openSelectPhoto(picture: PictureSelector, listener: OnPhotoSelectListener) {
        picture.openGallery(SelectMimeType.ofImage())
            .setSelectionMode(SelectModeConfig.SINGLE)
            .setImageEngine(GlideEngine)
            .isGif(false)
            .isPreviewImage(true) //是否预览
            .setCompressEngine(CompressEngin)
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: ArrayList<LocalMedia>) {
                    val localMedia = result[0]
                    var path = localMedia.compressPath
                    if (TextUtils.isEmpty(path)) {
                        path =
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                                localMedia.realPath
                            } else {
                                localMedia.path
                            }
                    }
                    if (!TextUtils.isEmpty(path)) {
                        listener.onPhotoSelect(path)
                    }
                }

                override fun onCancel() {}
            })
    }

    fun interface OnPhotoSelectListener {
        fun onPhotoSelect(path: String)
    }

    /**
     * 图片压缩引擎
     */
    object CompressEngin : CompressFileEngine {
        override fun onStartCompress(
            context: Context,
            source: ArrayList<Uri>,
            call: OnKeyValueResultCallbackListener,
        ) {
            Luban.with(context)
                .load(source)
                .ignoreBy(100)
                .setCompressListener(object : OnNewCompressListener {
                    override fun onStart() {
                        // 在此处理开始压缩的逻辑
                    }

                    override fun onSuccess(source: String, compressFile: File) {
                        call.onCallback(source, compressFile.absolutePath)
                    }

                    override fun onError(source: String, e: Throwable) {
                        call.onCallback(source, null)
                    }
                })
                .launch()
        }
    }

    /**
     *图片加载引擎
     */
    object GlideEngine : ImageEngine {
        /**
         * 加载图片
         *
         * @param context   上下文
         * @param url       资源url
         * @param imageView 图片承载控件
         */
        override fun loadImage(context: Context, url: String, imageView: ImageView) {
            if (!ActivityCompatHelper.assertValidRequest(context)) {
                return
            }
            Glide.with(context)
                .load(url)
                .into(imageView)
        }

        override fun loadImage(
            context: Context,
            imageView: ImageView,
            url: String,
            maxWidth: Int,
            maxHeight: Int,
        ) {
            if (!ActivityCompatHelper.assertValidRequest(context)) {
                return
            }
            Glide.with(context)
                .load(url)
                .override(maxWidth, maxHeight)
                .into(imageView)
        }

        /**
         * 加载相册目录封面
         *
         * @param context   上下文
         * @param url       图片路径
         * @param imageView 承载图片ImageView
         */
        override fun loadAlbumCover(context: Context, url: String, imageView: ImageView) {
            if (!ActivityCompatHelper.assertValidRequest(context)) {
                return
            }
            Glide.with(context)
                .asBitmap()
                .load(url)
                .override(180, 180)
                .sizeMultiplier(0.5f)
                .transform(CenterCrop(), RoundedCorners(8))
                .placeholder(com.luck.picture.lib.R.drawable.ps_image_placeholder)
                .placeholder(com.luck.picture.lib.R.drawable.ps_image_placeholder)
                .into(imageView)
        }

        /**
         * 加载图片列表图片
         *
         * @param context   上下文
         * @param url       图片路径
         * @param imageView 承载图片ImageView
         */
        override fun loadGridImage(context: Context, url: String, imageView: ImageView) {
            if (!ActivityCompatHelper.assertValidRequest(context)) {
                return
            }
            Glide.with(context)
                .load(url)
                .override(200, 200)
                .centerCrop()
                .placeholder(com.luck.picture.lib.R.drawable.ps_image_placeholder)
                .into(imageView)
        }

        override fun pauseRequests(context: Context) {
            if (!ActivityCompatHelper.assertValidRequest(context)) {
                return
            }
            Glide.with(context).pauseRequests()
        }

        override fun resumeRequests(context: Context) {
            if (!ActivityCompatHelper.assertValidRequest(context)) {
                return
            }
            Glide.with(context).resumeRequests()
        }
    }
}


