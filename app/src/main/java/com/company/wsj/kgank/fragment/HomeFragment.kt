package com.company.wsj.kgank.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.wsj.kgank.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by wangshijia on 2017/8/31.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        home_page_vp.adapter = HomePagersAdapter(activity.supportFragmentManager)
        home_page_tab_layout.setupWithViewPager(home_page_vp,true)
    }



    class HomePagersAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

        private val titles: ArrayList<String> = arrayListOf("Android", "IOS", "前端", "福利")
        private val fragments = arrayListOf<Fragment>()

        init {
            fragments.add(AndroidFragment())
            fragments.add(DayFragment())
            fragments.add(DayFragment())
            fragments.add(MeiZiFragment())
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}

