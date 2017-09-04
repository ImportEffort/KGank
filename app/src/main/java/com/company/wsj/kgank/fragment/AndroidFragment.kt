package com.company.wsj.kgank.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.wsj.kgank.HOME_PAGE_KEY
import com.company.wsj.kgank.R
import com.company.wsj.kgank.adapter.AndroidAdapter
import com.company.wsj.kgank.entity.GankTypeData
import com.company.wsj.kgank.net.client.HttpClient
import com.company.wsj.kgank.tencent_web.BrowserActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_android.*


/**
 * Created by wangshijia on 2017/8/31.
 *
 */


class AndroidFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var page_type: String = "Android"
    private var listItems = arrayListOf<GankTypeData>()
    private var androidAdapter: AndroidAdapter = AndroidAdapter(listItems)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_android, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments
        page_type = arguments?.getString(HOME_PAGE_KEY) ?: "all"
        initView()
    }

    private fun initView() {
        androidAdapter.setOnItemClickListener { adapter, view, position ->
            val item :GankTypeData = adapter.getItem(position) as GankTypeData
            Log.e("TAG",item.url)
            startBrowserActivity(1,item.url,item.desc)
        }
        androidAdapter.setEnableLoadMore(true)
        androidAdapter.setOnLoadMoreListener({
            isLoadMore = true
            currentPage++
            loadNetWork()
        }, recyclerView_android)
        refresh_android.setOnRefreshListener(this)
        recyclerView_android.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView_android.adapter = androidAdapter
        loadNetWork()
    }

    override fun onRefresh() {
        isLoadMore = false
        currentPage = 1
        loadNetWork()
    }

    private var isLoadMore: Boolean = false
    private var currentPage: Int = 1
    private fun loadNetWork() {
        HttpClient.getClient().getGankIoData(page_type, currentPage, 10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    if (isLoadMore) {
                        androidAdapter.addData(it.results)
                        androidAdapter.loadMoreComplete()
                    } else {
                        androidAdapter.setNewData(it.results)
                        refresh_android.isRefreshing = false
                    }
                }
    }

    private fun startBrowserActivity(mode: Int,url : String,title:String) {
        val intent = Intent(activity, BrowserActivity::class.java)
        intent.putExtra(BrowserActivity.PARAM_URL, url)
        intent.putExtra(BrowserActivity.PARAM_MODE, mode)
        intent.putExtra(BrowserActivity.TITLE_TEXT,title)
        startActivityForResult(intent, -1)
    }
}