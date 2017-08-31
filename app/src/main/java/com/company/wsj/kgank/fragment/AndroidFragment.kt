package com.company.wsj.kgank.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.wsj.kgank.R
import com.company.wsj.kgank.adapter.AndroidAdapter
import com.company.wsj.kgank.entity.GankTypeData
import com.company.wsj.kgank.net.client.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_android.*

/**
 * Created by wangshijia on 2017/8/31.
 *
 */
class AndroidFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    private var listItems = arrayListOf<GankTypeData>()
    private val androidAdapter: AndroidAdapter = AndroidAdapter(listItems)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_android, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        refresh_android.setOnRefreshListener(this)
        recyclerView_android.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView_android.adapter = androidAdapter
        loadNetWork()
    }

    override fun onRefresh() {
        loadNetWork()
    }

    private fun loadNetWork() {
        HttpClient.getClient().getGankIoData("Android", 1, 10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    androidAdapter.addData(it.results)
                    refresh_android.isRefreshing = false
                }
    }
}