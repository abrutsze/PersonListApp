package com.example.data.datastore

import com.example.entity.Result
import com.example.entity.responcemodel.UserPostResponse


interface GetPostDataRepository {
    suspend fun getPostResponse(userId: Int): Result<List<UserPostResponse>>
    suspend fun deletePostItem(postId: Int): Result<Unit>
}