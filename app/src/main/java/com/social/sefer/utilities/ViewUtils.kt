package com.social.sefer.utilities

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


object ViewUtils {
  enum class SystemBarColors { WHITE, DARK }

  private const val DARK_ICON = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
  private const val WHITE_NAV_ICON = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
  private const val WHITE_ICON = 0

  @JvmStatic
  fun setUpStatusBarAndNavigationBar(activity: Activity) {
    with(activity) {
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }
  }

  // STATUS BAR
  @JvmStatic
  fun changeStatusBarIcons(activity: Activity, isWhiteIcons: Boolean) {
    setUpStatusBarAndNavigationBar(activity)
    activity.window?.decorView?.systemUiVisibility =
      if (isWhiteIcons) WHITE_ICON else DARK_ICON
  }

  @JvmStatic
  fun setupSystemBars(activity: Activity, isDarkMode: Boolean) {
    val decorView = activity.window.decorView

    decorView.systemUiVisibility = if (isDarkMode) {
      WHITE_ICON
    } else {
      DARK_ICON xor WHITE_NAV_ICON
    }
  }

  @JvmStatic
  fun changeStatusBarColor(activity: Activity, @ColorRes colorRes: Int) {
    setUpStatusBarAndNavigationBar(activity)
    activity.window?.statusBarColor = ContextCompat.getColor(activity, colorRes)
  }

  // SYSTEM Bar
  @JvmStatic
  fun changeSystemNavigationBarColor(activity: Activity, @ColorRes colorRes: Int) {
    activity.window?.navigationBarColor = ContextCompat.getColor(activity, colorRes)
  }
}