package com.company.wsj.kgank

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by wangshijia on 2017/8/29.
 *
 */
class GankApplication : Application() {

    companion object {
        //by 是一个为委托属性 。它会含有一个可null的变量并会在我们设置这个 属性的时候分配一个真实的值。
        //如果这个值在被获取之前没有被分配，它就会抛出 一个异常。
        var instance : GankApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this//在这之后 instance 被赋值了
    }

}
