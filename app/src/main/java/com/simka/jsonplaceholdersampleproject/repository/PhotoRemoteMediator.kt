package com.simka.jsonplaceholdersampleproject.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.simka.jsonplaceholdersampleproject.api.ApiService
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.model.PhotoKey
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PhotoRemoteMediator(
    private val apiService: ApiService,
    private val database: JsonPlaceholderDatabase
) : RemoteMediator<Int, Photo>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Photo>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getPhotoKeys()
                }
            }

            val nextKey = loadKey?.after ?: 0
            val offset = nextKey * state.config.pageSize

            val photos = apiService.fetchPhotos(
                limit = state.config.pageSize,
                start = offset
            )

            database.withTransaction {
                database.photoKeysDao().savePhotoKeys(PhotoKey(0, nextKey.plus(1)))
                database.photoDao().savePhotos(photos)
            }

            MediatorResult.Success(endOfPaginationReached = photos.isEmpty())
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }


    }

    private suspend fun getPhotoKeys(): PhotoKey? {
        return database.photoKeysDao().getPhotoKeys().firstOrNull()
    }

}