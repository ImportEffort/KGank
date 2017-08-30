package com.company.wsj.kgank.net.client

import com.company.wsj.kgank.entity.Gank
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by wangshijia on 2017/8/30.
 *
 */
const val API_SERVER_URL:String ="http://gank.io/api/"
interface ApiStores{
    @GET("data/福利/10/1")
    fun loadPic() :Observable<Gank>
}