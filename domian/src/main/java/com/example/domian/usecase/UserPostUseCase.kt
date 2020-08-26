package com.example.domian.usecase

import com.example.data.datastore.GetPostDataRepository
import com.example.domian.interactors.UserPostInteractor
import com.example.domian.utils.toLocalUserPostData
import com.example.entity.CallException
import com.example.entity.Constants
import com.example.entity.Result
import com.example.entity.localmodels.UserPost
import org.json.JSONObject

class UserPostUseCase(private val getDataRepository: GetPostDataRepository) :
    UserPostInteractor {
    override suspend fun getPostResponse(userId: Int): Result<List<UserPost>> {
        return when (val apiData = getDataRepository.getPostResponse(userId)) {
            is Result.Success -> {
                val mappingData = apiData.data?.toLocalUserPostData()
                Result.Success(mappingData)
            }
            else -> {
                Result.Error(
                    CallException(
                        Constants.ERROR_NULL_DATA,
                        null,
                        "Can't load User into server"
                    )
                )
            }
        }
    }

    override suspend fun deletePostResponse(postId: Int): Result<Unit> {
        return when (val apiData = getDataRepository.deletePostItem(postId)) {
            is Result.Success -> {
                Result.Success(apiData.data)
            }
            else -> {
                Result.Error(
                    CallException(
                        Constants.ERROR_NULL_DATA,
                        null,
                        "Can't delete post into server"
                    )
                )
            }
        }
    }
}