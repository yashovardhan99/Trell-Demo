package com.yashovardhan99.trelldemo.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.yashovardhan99.trelldemo.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private val viewModel: FeedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val imagesAdapter = ImagesAdapter()
        val usersAdapter = UserAdapter {
            Toast.makeText(context, "Followed: ${it.name}", Toast.LENGTH_LONG).show()
        }
        val userHolderAdapter = UserHolderAdapter(usersAdapter)
        binding.feedRecycler.adapter = ConcatAdapter(userHolderAdapter, imagesAdapter)
        binding.feedRecycler.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == 0) 2
                        else 1
                    }
                }
            }
        lifecycleScope.launchWhenStarted {
            viewModel.images.collect { pagedData ->
                Timber.d("Data = $pagedData")
                imagesAdapter.submitData(pagedData)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.users.collect { users ->
                Timber.d("Users = $users")
                usersAdapter.submitList(users)
                userHolderAdapter.notifyDataSetChanged()
            }
        }
        return binding.root
    }
}