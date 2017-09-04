package com.company.wsj.kgank.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.company.wsj.kgank.R
import com.company.wsj.kgank.TEXT
import com.company.wsj.kgank.TEXT_IMAGE
import com.company.wsj.kgank.entity.GankTypeData

/**
 * Created by wangshijia on 2017/8/30.
 *
 */


class AndroidAdapter(data: MutableList<GankTypeData>) : BaseMultiItemQuickAdapter<GankTypeData, BaseViewHolder>(data) {

    init {
        addItemType(TEXT, R.layout.layout_text_item)
        addItemType(TEXT_IMAGE, R.layout.layout_image_item)
    }

    override fun convert(helper: BaseViewHolder?, item: GankTypeData?) {
        when (helper?.itemViewType) {
            TEXT -> {
                helper.setText(R.id.gank_type_item_title, item?.desc)
                helper.setText(R.id.gank_type_item_author,item?.who)
                helper.setText(R.id.gank_type_item_time,item?.publishedAt?.substringBefore("T"))
            }

            TEXT_IMAGE -> {
                helper.setText(R.id.gank_type_image_item_title, item?.desc)
                val imageView = helper.getView<ImageView>(R.id.gank_type_image_item_image)
                Glide.with(imageView.context).load(item?.images?.get(0)).into(imageView)
                helper.setText(R.id.gank_type_item_author,item?.who)
                helper.setText(R.id.gank_type_item_time,item?.publishedAt?.substringBefore("T"))
            }
        }
    }
}