package com.social.sefer.utilities

import android.content.Context
import com.social.sefer.Constants

object PrefUtility {
  fun putAuthToken(context: Context, value: String?) {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    val editor = pref.edit()
    editor.putString(Constants.AUTHORIZATION, value)
    editor.apply()
  }

  fun putUserId(context: Context, id: Long) {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    val editor = pref.edit()
    editor.putLong(Constants.USERID, id)
    editor.apply()
  }

  fun selectedLangCode(context: Context): String {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    return pref.getString(Constants.LANG, "en").toString()
  }

  fun wipeSomeData(context: Context) {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    val editor = pref.edit()
    editor.putString(Constants.AUTHORIZATION, null)
    editor.putLong(Constants.USERID, -1)
    editor.apply()
  }

  fun currentUserId(context: Context): Long {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    return pref.getLong(Constants.USERID, -1L)
  }

  fun authorizationToken(context: Context): String? {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    return pref.getString(Constants.AUTHORIZATION, null)
  }


  fun themeMode(context: Context): String {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    return pref.getString(Constants.THEME, Constants.SYSTEM)!!
  }

  fun setThemeMode(context: Context, value: String) {
    val pref = context.getSharedPreferences(Constants.PREFS_FILE, Constants.PREFS_MODE)
    val editor = pref.edit()
    editor.putString(Constants.THEME, value)
    editor.apply()
  }
}
