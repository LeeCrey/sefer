package com.social.sefer.ui.views

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.social.sefer.utilities.ApplicationHelper
import com.social.sefer.utilities.LocaleHelper
import com.social.sefer.utilities.PrefUtility

open class MyAppActivity : AppCompatActivity() {
  override fun attachBaseContext(newBase: Context) {
    val existValue = PrefUtility.themeMode(newBase)
    ApplicationHelper.toggleDayNight(existValue)

    super.attachBaseContext(LocaleHelper.setLocale(newBase, PrefUtility.selectedLangCode(newBase)))
  }

  protected fun isDarkMode(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      resources.configuration.isNightModeActive
    } else {
      resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==
          Configuration.UI_MODE_NIGHT_YES
    }
  }
}