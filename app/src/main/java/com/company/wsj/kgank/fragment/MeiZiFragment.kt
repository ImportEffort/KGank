package com.company.wsj.kgank.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.wsj.kgank.R
import com.company.wsj.kgank.adapter.HomeAdapter
import com.company.wsj.kgank.net.client.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_meizi.*

/**
 * Created by wangshijia on 2017/8/31.
 *
 */
class MeiZiFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_meizi,container,false)
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeList()
    }

    private val homeAdapter = HomeAdapter(arrayListOf())
    private var currentPage: Int = 1

    private fun initHomeList() {
        homeAdapter.setEnableLoadMore(true)

        homeAdapter.setOnLoadMoreListener({
            loadNetWork(currentPage)
            currentPage++
        }, recyclerView_home)


        recyclerView_home.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        loadNetWork(currentPage)
        recyclerView_home.adapter = homeAdapter
    }

    private fun loadNetWork(pageNum:Int) {

        HttpClient.getClient().loadPic(10, pageNum)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({it-> homeAdapter.addData(it.results)
                    homeAdapter.loadMoreComplete()
                }, { it: Throwable -> it.printStackTrace() })
    }

}