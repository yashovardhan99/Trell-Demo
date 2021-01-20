package com.yashovardhan99.trelldemo.feed

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class FeedViewModel @ViewModelInject
constructor(
    @ApplicationContext context: Context,
    repository: FeedRepository
) : ViewModel() {
    val images = repository.getImages()
    val users = flow {
        Timber.d("Flow executing")
        emit(repository.getUsers())
    }
}