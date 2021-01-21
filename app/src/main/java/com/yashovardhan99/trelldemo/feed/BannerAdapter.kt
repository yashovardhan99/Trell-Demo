package com.yashovardhan99.trelldemo.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yashovardhan99.trelldemo.databinding.CardBannerBinding

class BannerAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    class BannerViewHolder(private val binding: CardBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: FragmentActivity) {
            binding.viewPager.adapter = BannerPageAdapter(activity)
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

class BannerPageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return BannerFragment()
    }
}