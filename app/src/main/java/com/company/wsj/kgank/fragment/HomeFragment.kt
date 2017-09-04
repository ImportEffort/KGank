package com.company.wsj.kgank.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.wsj.kgank.*
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
        home_page_tab_layout.setupWithViewPager(home_page_vp, true)
    }


    class HomePagersAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

        private val titles: ArrayList<String> = arrayListOf("Android", "IOS", "前端","干货","福利")
        private val fragments = arrayListOf<Fragment>()


        init {

            val androidFragment = AndroidFragment()
            val bundle = Bundle()
            bundle.putString(HOME_PAGE_KEY, TYPE_ANDROID)
            androidFragment.arguments = bundle
            fragments.add(androidFragment)

            val iOSFragment = AndroidFragment()
            val bundleIOS = Bundle()
            bundleIOS.putString(HOME_PAGE_KEY, TYPE_IOS)
            iOSFragment.arguments = bundleIOS
            fragments.add(iOSFragment)

            val h5Fragment = AndroidFragment()
            val bundleH5 = Bundle()
            bundleH5.putString(HOME_PAGE_KEY, TYPE_H5)
            h5Fragment.arguments = bundleH5
            fragments.add(h5Fragment)


            val allFragment = AndroidFragment()
            val bundleAll = Bundle()
            bundleH5.putString(HOME_PAGE_KEY, TYPE_ALL)
            allFragment.arguments = bundleAll
            fragments.add(allFragment)

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

