package com.yashovardhan99.trelldemo.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class FeedViewModel @ViewModelInject constructor(repository: FeedRepository) : ViewModel() {
    val images = repository.getImages()
    val users = flow {
        Timber.d("Flow executing")
        emit(repository.getUsers())
    }
}