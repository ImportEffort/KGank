package com.company.wsj.kgank.net.interceptor

import com.company.wsj.kgank.GankApplication
import com.company.wsj.kgank.utils.NetWorkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
* Created by wangshijia on 2017/8/30.
*/

class CacheIntercetor : Interceptor{

    override fun intercept(chain: Interceptor.Chain?): Response {
        var request : Request? = chain?.request()// ?. 为 if not null
        if (!NetWorkUtils.isNetWorkAvailable(GankApplication.instance) ){
            request = request?.newBuilder()?.cacheControl(CacheControl.FORCE_CACHE)?.build()
        }
        val response = chain?.proceed(request)

        if (NetWorkUtils.isNetWorkAvailable(GankApplication.instance)){
            val maxAge =0
            val cacheControl = request?.cacheControl().toString()
            if (cacheControl.isBlank())
            response?.newBuilder()?.header("Cache-Control", "public, max-age=" + maxAge)?.removeHeader("Pragma")?.build()
            else
                response?.newBuilder()?.header("Cache-Control", cacheControl)?.build()
        }else{
            val maxStale =60 * 60 * 24 * 28
            response?.newBuilder()?.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)?.removeHeader("Pragma")?.build()
        }
        return response!!// !! 如何有 response 为空 则跑出 NPE 异常 如果不为空正常返回
    }
}