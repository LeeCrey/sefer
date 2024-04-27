package com.social.sefer.utilities

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.social.sefer.Constants
import com.social.sefer.R


object ApplicationHelper {
  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  @JvmStatic
  fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
      context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
  }

  @JvmStatic
  fun setUpToolBar(root: View, toolbarId: Int, fragment: Fragment): NavController {
    val navController = NavHostFragment.findNavController(fragment)
    val toolbar: Toolbar = root.findViewById(toolbarId)
    setupWithNavController(toolbar, navController)

    return navController
  }

  @JvmStatic
  val slideTopDown: NavOptions
    get() =
      NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_bottom)
        .setExitAnim(R.anim.wait)
        .build()

  @JvmStatic
  val slideAnimation: NavOptions
    get() =
      NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

  fun showError(root: View, msg: String) {
    Snackbar.make(root, msg, Snackbar.LENGTH_SHORT).show()
  }

  @JvmStatic
  fun switchToActivity(
    klass: Class<*>,
    context: FragmentActivity,
    shouldFinish: Boolean = false,
  ) {
    val animation =
      ActivityOptions.makeCustomAnimation(context, R.anim.fade_in_zero, R.anim.slide_out_left)
    context.startActivity(Intent(context, klass), animation.toBundle())

    if (shouldFinish) {
      context.finish()
    }
  }

  @JvmStatic
  fun toggleDayNight(value: String) {
    AppCompatDelegate.setDefaultNightMode(
      when (value) {
        Constants.SYSTEM -> {
          AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }

        Constants.DARK -> {
          AppCompatDelegate.MODE_NIGHT_YES
        }

        else -> {
          AppCompatDelegate.MODE_NIGHT_NO
        }
      }
    )
  }

  fun closeActivityAnimation(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
      activity.overrideActivityTransition(
        AppCompatActivity.OVERRIDE_TRANSITION_CLOSE,
        R.anim.slide_in_left,
        R.anim.slide_out_right
      )
    } else {
      activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
  }
}