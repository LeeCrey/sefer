package com.social.sefer.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.social.sefer.R

class LauncherActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    val screen: SplashScreen = installSplashScreen()

    super.onCreate(savedInstanceState)

    screen.setOnExitAnimationListener {
      openTargetActivity(MainActivity::class.java)
    }
  }

  private fun openTargetActivity(t: Class<*>) {
    startActivity(Intent(this, t))

    overridePendingTransition(R.anim.fade_in_zero, R.anim.fade_out_zero)
  }
}