package com.yashovardhan99.trelldemo.feed

import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val STARTING_PAGE = 1

class ImagesPagingSource(private val service: ImageService) : PagingSource<Int, Image>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val position = params.key ?: STARTING_PAGE
        return try {
            val response =
                withContext(Dispatchers.IO) {
                    service.getImages(position, params.loadSize)
                }
            Timber.d("Response = $response")
            val images = response.body()
            val nextKey =
                if (images.isNullOrEmpty()) null else position + params.loadSize / PAGE_SIZE
            LoadResult.Page(
                data = images ?: emptyList(),
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Timber.e(e, "Error paging")
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}