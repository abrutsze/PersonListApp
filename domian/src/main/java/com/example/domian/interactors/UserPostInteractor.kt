package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.UserPost
import org.json.JSONObject

interface UserPostInteractor {
    suspend fun getPostResponse(userId: Int): Result<List<UserPost>>
    suspend fun deletePostResponse(postId: Int): Result<Unit>
}