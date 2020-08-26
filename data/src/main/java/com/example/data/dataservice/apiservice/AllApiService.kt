package com.example.data.dataservice.apiservice

import com.example.entity.responcemodel.UserPostResponse
import com.example.entity.responcemodel.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface AllApiService {

    @GET("users")
    suspend fun getUserData(): Response<List<UserResponse>>

    @GET("posts")
    suspend fun getPostData(
        @Query ("userId") userId:Int
    ): Response<List<UserPostResponse>>

    @DELETE("posts/{postId}")
    suspend fun deletePostData(
        @Path("postId") postId:Int
    ): Response<Unit>

}
