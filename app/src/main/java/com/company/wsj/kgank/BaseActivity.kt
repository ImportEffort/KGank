package com.company.wsj.kgank

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.company.wsj.kgank.net.client.HttpClient
import io.reactivex.disposables.Disposable

/**
 * Created by wangshijia on 2017/8/30.
 *
 */
open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBar()
    }

    open fun setStatusBar() {
//        if (this is HomeActivity)
//            StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, drawer_layout, ContextCompat.getColor(this, R.color.colorTheme))
//        else
//            StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorTheme))

    }

    override fun onDestroy() {
        super.onDestroy()
        val key: String = this.packageName + "." + this.javaClass.simpleName
        HttpClient.clearNetRequest(key)
    }

    protected open fun addDisposable(disposable: Disposable) {
        val key: String = this.packageName + "." + this.javaClass.simpleName
        HttpClient.addNetRequest(key, disposable)
    }
}