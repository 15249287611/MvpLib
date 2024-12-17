package com.hongyu.zorelib.exception

import com.hongyu.zorelib.mvp.view.BaseUploadUI
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Sink
import okio.buffer
import java.io.IOException

class CountingRequestBody(
    private val mRequestBody: RequestBody,
    private val mUploadListener: BaseUploadUI?,
) : RequestBody() {
    override fun contentType(): MediaType? {
        return mRequestBody.contentType()
    }

    override fun contentLength(): Long {
        try {
            return mRequestBody.contentLength()
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        if (sink is Buffer) {
            mRequestBody.writeTo(sink)
            return
        }
        val bufferedSink: BufferedSink
        val mCountingSink: CountingSink = CountingSink(sink)
        bufferedSink = mCountingSink.buffer()

        mRequestBody.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    internal inner class CountingSink(delegate: Sink?) : ForwardingSink(delegate!!) {
        private var bytesWritten: Long = 0

        @Throws(IOException::class)
        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)
            bytesWritten += byteCount
            mUploadListener?.onRequestProgress(bytesWritten, contentLength())
        }
    }
}
