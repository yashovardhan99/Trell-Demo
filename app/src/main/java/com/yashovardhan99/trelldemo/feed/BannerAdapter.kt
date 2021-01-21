package com.yashovardhan99.trelldemo.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.yashovardhan99.trelldemo.databinding.CardBannerBinding
import kotlin.math.abs

class BannerAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    class BannerViewHolder(private val binding: CardBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: FragmentActivity) {
            binding.viewPager.adapter = BannerPageAdapter(activity)
            binding.viewPager.clipToPadding = false
            binding.viewPager.offscreenPageLimit = 1
            binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BannerViewHolder(CardBannerBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(activity)
    }

    override fun getItemCount() = 1
}

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }

    override fun transformPage(page: View, position: Float) {
        page.apply {
            when {
                position < -1 -> alpha = 0f
                position <= 1 -> {
                    val scaleFactor = MIN_SCALE.coerceAtLeast(1 - abs(position))
                    val vertMargin = height * (1 - scaleFactor) / 2
                    val horzMargin = width * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> alpha = 0f
            }
        }
    }
}

class BannerPageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return BannerFragment()
    }
}