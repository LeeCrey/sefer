package com.social.sefer.ui.views

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.social.sefer.R
import com.social.sefer.databinding.ActivityMainBinding
import com.social.sefer.utilities.ViewUtils


class MainActivity : MyAppActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController
  private lateinit var bottomNav: BottomNavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val toolbar: Toolbar = findViewById(R.id.tool_bar)
    setSupportActionBar(toolbar)

    val drawerLayout: DrawerLayout = binding.drawerLayout
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

    ViewUtils.setupSystemBars(this, isDarkMode())
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }
}
