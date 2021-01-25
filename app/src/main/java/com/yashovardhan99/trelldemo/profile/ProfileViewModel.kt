package com.yashovardhan99.trelldemo.profile

import android.util.Log
import androidx.databinding.BindingBuildInfo
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.yashovardhan99.trelldemo.feed.FeedRepository
import com.yashovardhan99.trelldemo.feed.ImageServiceProvider
import kotlinx.coroutines.flow.flow
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*
import kotlin.concurrent.schedule

class ProfileViewModel @ViewModelInject constructor(repository: ProfileRepository) : ViewModel() {

    var imageList : List<ImageInfo> = emptyList();
    fun getAllImages(){

    }
}
