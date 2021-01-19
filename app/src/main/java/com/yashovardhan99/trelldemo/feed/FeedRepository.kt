package com.yashovardhan99.trelldemo.feed

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepository @Inject constructor(private val service: ImageService) {
    fun getImages(): Flow<PagingData<Image>> {
        return Pager(config = PagingConfig(pageSize = ImagesPagingSource.PAGE_SIZE),
            pagingSourceFactory = { ImagesPagingSource(service) }
        ).flow
    }
}