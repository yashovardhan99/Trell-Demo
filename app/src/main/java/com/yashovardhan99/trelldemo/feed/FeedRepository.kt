package com.yashovardhan99.trelldemo.feed

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepository @Inject constructor(
    private val imageService: ImageService,
    private val usersService: UsersService
) {
    fun getImages(): Flow<PagingData<Image>> {
        return Pager(config = PagingConfig(pageSize = ImagesPagingSource.PAGE_SIZE),
            pagingSourceFactory = { ImagesPagingSource(imageService) }
        ).flow
    }

    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            Timber.d("Calling userService")
            val response = usersService.getUsers()
            Timber.d("$response")
            val userResponse = response.body()
            userResponse?.results?.map { result ->
                Timber.d("UserResult = $result")
                User("${result.name.first} ${result.name.last}", result.picture.large)
            } ?: emptyList()
        }
    }
}