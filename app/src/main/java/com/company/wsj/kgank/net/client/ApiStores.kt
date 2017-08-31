package com.company.wsj.kgank.net.client

import com.company.wsj.kgank.entity.GankGirl
import com.company.wsj.kgank.entity.GankTypeData
import com.company.wsj.kgank.entity.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by wangshijia on 2017/8/30.
 *
 */
const val API_SERVER_URL: String = "http://gank.io/api/"

interface ApiStores {


    @GET("data/福利/{count}/{pageNum}")
    fun loadPic(@Path("count") count: Int, @Path("pageNum") pageNum: Int): Observable<ResponseWrapper<GankGirl>>

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    fun getGankIoData(@Path("type") id: String, @Path("page") page: Int, @Path("pre_page") pre_page: Int): Observable<ResponseWrapper<GankTypeData>>
}