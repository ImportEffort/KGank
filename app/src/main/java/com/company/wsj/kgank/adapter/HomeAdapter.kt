package com.company.wsj.kgank.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.company.wsj.kgank.GankApplication
import com.company.wsj.kgank.R
import com.company.wsj.kgank.entity.GankGirl

/**
 * Created by wangshijia on 2017/8/30.
 */

class HomeAdapter(public var dates:List<GankGirl>) : BaseQuickAdapter<GankGirl,BaseViewHolder>(R.layout.layout_home_item,dates){
    override fun convert(helper: BaseViewHolder?, item: GankGirl?) {
        val imageView = helper?.getView<ImageView>(R.id.item_image)
        Glide.with(GankApplication.instance).load(item?.url).into(imageView)
    }
}