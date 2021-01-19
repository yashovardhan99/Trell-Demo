package com.yashovardhan99.trelldemo.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.yashovardhan99.trelldemo.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class FeedFragment : Fragment() {
    val viewModel: FeedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val imagesAdapter = ImagesAdapter()
        binding.feedRecycler.adapter = imagesAdapter
        binding.feedRecycler.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        lifecycleScope.launchWhenStarted {
            viewModel.images.collect { pagedData ->
                Timber.d("Data = $pagedData")
                imagesAdapter.submitData(pagedData)
            }
        }
        return binding.root
    }
}