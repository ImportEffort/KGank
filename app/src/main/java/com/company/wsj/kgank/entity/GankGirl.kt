package com.company.wsj.kgank.entity

/**
 * Created by wangshijia on 2017/8/30.
 */
data class GankGirl(val _id: String? = "",
                    val createdAt: String? = "",
                    val desc: String? = "",
                    val publishedAt: String? = "",
                    val source: String? = "",
                    val type: String? = "",
                    val url: String? = "",
                    val used: Boolean = false,
                    val who: String? = "")

data class Gank ( var error: Boolean = false, var results: List<GankGirl>? = null)