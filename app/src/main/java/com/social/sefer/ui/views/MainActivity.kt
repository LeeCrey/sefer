package com.social.sefer.ui.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.social.sefer.R
import com.social.sefer.databinding.ActivityMainBinding
import com.social.sefer.utilities.ApplicationHelper
import com.social.sefer.utilities.ViewUtils


class MainActivity : MyAppActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController
  private lateinit var bottomNav: BottomNavigationView
  private lateinit var backPressCB: OnBackPressedCallback
  private lateinit var drawerLayout: DrawerLayout

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val toolbar: Toolbar = findViewById(R.id.tool_bar)
    setSupportActionBar(toolbar)

    drawerLayout = binding.drawerLayout
    val navView: NavigationView = binding.navView

    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
    navController = navHostFragment.navController
    bottomNav = findViewById(R.id.bottom_nav_view)

    appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.nav_home, R.id.nav_community, R.id.nav_profile, R.id.nav_shorts, R.id.nav_users
      ), drawerLayout
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
    setupWithNavController(bottomNav, navController)

    val surface = getColor(R.color.surface)
    navView.setBackgroundColor(surface)
    bottomNav.setBackgroundColor(surface)


    navView.setNavigationItemSelectedListener {
      when (val id = it.itemId) {
        R.id.menu_item_rate_app -> rateApp()
        R.id.menu_item_share -> shareApp()
        R.id.nav_settings -> {
          navController.navigate(id, null, ApplicationHelper.slideAnimation)
        }

        else -> onNavDestinationSelected(it, navController)
      }
      drawerLayout.close()

      false
    }

    drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
      override fun onDrawerOpened(drawerView: View) {
        super.onDrawerOpened(drawerView)

        backPressCB.isEnabled = true
      }

      override fun onDrawerClosed(drawerView: View) {
        super.onDrawerClosed(drawerView)

        backPressCB.isEnabled = false
      }
    })
    registerBackPress()
    ViewUtils.setupSystemBars(this, isDarkMode())
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  private fun registerBackPress() {
    backPressCB = object : OnBackPressedCallback(false) {
      override fun handleOnBackPressed() {
        drawerLayout.close()
      }
    }
    onBackPressedDispatcher.addCallback(backPressCB)
  }

  private fun rateApp() {
    try {
      startActivity(
        Intent(
          Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")
        )
      )
    } catch (_: ActivityNotFoundException) {
      startActivity(
        Intent(
          Intent.ACTION_VIEW,
          Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        )
      )
    }
  }

  private fun shareApp() {
    val appUri = "https://play.google.com/store/apps/details?id=$packageName"
    val sendIntent = Intent().apply {
      action = Intent.ACTION_SEND
      type = "text/plain"
      putExtra(Intent.EXTRA_TEXT, appUri)
    }
    startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))
  }
}
