package com.company.wsj.kgank

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.company.wsj.kgank.fragment.DayFragment
import com.company.wsj.kgank.fragment.HomeFragment
import com.company.wsj.kgank.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*




class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        initStatusView()

        home_radio_group.setOnCheckedChangeListener { radioGroup, id ->
            changeFragment(id)
        }
        initTabFragment()
    }

    private fun initStatusView() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, drawer_layout, ContextCompat.getColor(this,R.color.colorTheme))
    }


    private fun changeFragment(indexId: Int) {
        val supportFragmentManager = supportFragmentManager
        when (indexId) {
            R.id.home_icon1 -> {
                val instance = HomeFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_content, instance, "1")
                transaction.commit()
            }
            R.id.home_icon2 -> {
                val instance = DayFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_content, instance, "2")
                transaction.commit()
            }
            R.id.home_icon3 -> {
                val instance = DayFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_content, instance, "3")
                transaction.commit()
            }
        }
    }

    private fun initTabFragment() {
        val supportFragmentManager = supportFragmentManager
        val instance = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_content, instance, "1")
        transaction.commit()
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
