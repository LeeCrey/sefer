package com.social.sefer.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.social.sefer.databinding.FListBinding

class ProfileFragment : Fragment() {
  private var _binding: FListBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
  ): View {
    _binding = FListBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}
