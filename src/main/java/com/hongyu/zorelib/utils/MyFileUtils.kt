package com.hongyu.zorelib.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object MyFileUtils {

    // 将Bitmap保存到内部存储并返回File对象
    @JvmStatic
    fun bitmap2File(context: Context?, bitmap: Bitmap?): File? {
        if (context == null || bitmap == null) return null
        val dir = File(context.filesDir, "images")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val file = File(dir, "image_${System.currentTimeMillis()}.png")
        try {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val inputStream: InputStream = ByteArrayInputStream(baos.toByteArray())
            val fos = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } != -1) {
                fos.write(buffer, 0, length)
            }
            fos.flush()
            fos.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }


    // 获取文件的Uri
    @JvmStatic
    fun getFileUri(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }
}