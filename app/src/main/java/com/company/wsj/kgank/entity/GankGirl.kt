package com.company.wsj.kgank.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by wangshijia on 2017/8/30.
 *
 */

data class ResponseWrapper<out T>(val error: Boolean, val results: List<T>)

data class GankGirl(val _id: String? = "",
                    val createdAt: String? = "",
                    val desc: String? = "",
                    val publishedAt: String? = "",
                    val source: String? = "",
                    val type: String? = "",
                    val url: String? = "",
                    val used: Boolean = false,
                    val who: String? = "")


data class GankTypeData(var _id: String,
                        var createdAt: String,
                        var desc: String,
                        var publishedAt: String,
                        var source: String,
                        var `type`: String,
                        var url: String,
                        var used: Boolean,
                        var who: String,
                        var images: List<String>?) : MultiItemEntity {
    override fun getItemType(): Int {
        if (images== null) return 0
        else return images!!.size
    }
}