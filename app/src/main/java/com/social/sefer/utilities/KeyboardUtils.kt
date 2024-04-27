package com.social.sefer.utilities

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.social.sefer.utilities.KeyboardUtils.hide
import com.social.sefer.utilities.KeyboardUtils.showKeyboard

/**
 * Helper object that abstracts the keyboard functionality
 * @see showKeyboard Forcefully shows the keyboard
 * @see hide Forcefully hides the keyboard
 */
object KeyboardUtils {
  @JvmStatic
  fun showKeyboard(view: View) {
    showKeyboard(view, true)
  }

  @JvmStatic
  fun showKeyboard(view: View, useWindowInsetsController: Boolean) {
    if (useWindowInsetsController) {
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        view.windowInsetsController?.show(WindowInsets.Type.ime())
      } else {
        val imm =
          view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
      }
    }
    getInputMethodManager(view)!!.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
  }

  @JvmStatic
  // when target input is undetermined
  fun closeKeyboard(activity: Activity) {
    val imm =
      activity.application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
      view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
  }

  @JvmStatic
  // when you already know the input.
  fun hide(view: View) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
      view.windowInsetsController?.hide(WindowInsets.Type.ime())
    } else {
      val imm =
        view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
  }

  @JvmStatic
  fun requestFocusAndShowKeyboard(view: View, useWindowInsetsController: Boolean) {
    view.requestFocus()
    view.post { showKeyboard(view, useWindowInsetsController) }
  }

  @JvmStatic
  private fun getInputMethodManager(view: View): InputMethodManager? {
    return ContextCompat.getSystemService(view.context, InputMethodManager::class.java)
  }
}
