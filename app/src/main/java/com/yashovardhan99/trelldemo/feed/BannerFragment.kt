package com.yashovardhan99.trelldemo.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.yashovardhan99.trelldemo.R
import com.yashovardhan99.trelldemo.databinding.ImageBannerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BannerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ImageBannerBinding.inflate(inflater, container, false)
        lifecycleScope.launchWhenStarted {
            binding.imageView.load(getString(R.string.random_image_url)) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
        }
        return binding.root
    }
}