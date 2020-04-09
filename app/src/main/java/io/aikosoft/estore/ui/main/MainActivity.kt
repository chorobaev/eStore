package io.aikosoft.estore.ui.main

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import io.aikosoft.estore.R
import io.aikosoft.estore.base.BaseActivity
import io.aikosoft.estore.ui.cart.CartActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override val layoutRes: Int get() = R.layout.activity_main

    override fun onInitUI(firstInit: Boolean) {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onSetOnClickListeners() {
        tv_search.setOnClickListener {
            // TODO: open search activity
        }

        iv_checkout.setOnClickListener {
            Intent(this, CartActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> {
                drawer_layout.closeDrawer(GravityCompat.START)
            }

            else -> super.onBackPressed()
        }
    }
}
