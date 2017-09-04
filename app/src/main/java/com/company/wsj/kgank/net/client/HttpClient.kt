package com.company.wsj.kgank.net.client

import com.company.wsj.kgank.BuildConfig
import com.company.wsj.kgank.GankApplication
import com.company.wsj.kgank.net.interceptor.CacheIntercetor
import com.company.wsj.kgank.net.interceptor.LoggingInterceptor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object HttpClient {

    var netManager: HashMap<String, CompositeDisposable> = HashMap()

    private val CONNECT_TIME_OUT = 3000L
    private val READ_TIME_OUT = 5000L
    private val WRITE_TIME_OUT = 5000L

    fun getClient(): ApiStores {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        //设置 OKHttp 缓存
//        setCacheConfig(builder)
        //https 设置
        setHttpsConfig(builder)
        //设置超时和重连
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
        builder.connectTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
        builder.connectTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)

        builder.retryOnConnectionFailure(true)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = LoggingInterceptor()
            loggingInterceptor.setLevel(LoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        val okHttpClient = builder.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(API_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        //ApiStores::class.java取得对象的 Java 类
        return retrofit.create(ApiStores ::class.java)
    }

    private fun setHttpsConfig(builder: OkHttpClient.Builder) {
        val ssl: HttpsUtils.SSLParams = HttpsUtils.getSslSocketFactory(null, null, null)
        builder.sslSocketFactory(ssl.sSLSocketFactory, ssl.trustManager)
    }

    private fun setCacheConfig(builder: OkHttpClient.Builder) {
        val cacheFile: File = File(GankApplication.instance.externalCacheDir, "GankNetCache")
        val cache: Cache = Cache(cacheFile, 1024 * 1024 * 50)
        builder.cache(cache).addInterceptor(CacheIntercetor())
    }

    /**
     * key 采用 Activity 的全名 而不是 SimpleName
     */
    fun addNetRequest(key:String,disposable: Disposable){
        if (netManager.containsKey(key)){
            netManager[key]?.add(disposable)
        }else{
            val compositeDisposable = CompositeDisposable()
            compositeDisposable.add(disposable)
            netManager.put(key,compositeDisposable)
        }
    }

    fun clearNetRequest(key:String){
        if (netManager.containsKey(key)){
            val compositeDisposable = netManager[key]
            compositeDisposable?.clear()
        }
    }
}