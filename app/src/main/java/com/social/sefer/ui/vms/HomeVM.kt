package com.social.sefer.ui.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeVM : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is home Fragment"
  }
  val text: LiveData<String> = _text
}