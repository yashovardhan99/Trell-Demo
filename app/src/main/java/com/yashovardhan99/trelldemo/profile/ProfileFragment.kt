package com.yashovardhan99.trelldemo.profile

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.yashovardhan99.trelldemo.MainActivity
import com.yashovardhan99.trelldemo.R
import com.yashovardhan99.trelldemo.databinding.FragmentProfileBinding
import com.yashovardhan99.trelldemo.feed.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.cardView.load(R.drawable.avatar) {
            crossfade(true)
            placeholder(R.drawable.circle_image_placeholder)
            transformations(CircleCropTransformation())
        }

        binding.profileRecycler.layoutManager=
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        viewModel.showAll(binding)

        binding.allTrails.setOnClickListener(View.OnClickListener {
            viewModel.showAll(binding)
        })

        binding.savedTrails.setOnClickListener(View.OnClickListener {
            viewModel.showSaved(binding)
        })

        binding.constraintLayout.setOnClickListener(View.OnClickListener {
            viewModel.goToWebView(context)
        })

        binding.v1.setOnClickListener(View.OnClickListener {
            viewModel.goToWebView(context)
        })

        binding.cardView1.setOnClickListener(View.OnClickListener {
            viewModel.invite(activity as MainActivity)
        })

        return binding.root
    }
}