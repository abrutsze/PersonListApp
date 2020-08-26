package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.GetPostDataRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import com.example.entity.Result
import com.example.entity.responcemodel.UserPostResponse
import retrofit2.Response

class GetPostDataRepositoryImpl(private val allApiService: AllApiService) :
    GetPostDataRepository {

    override suspend fun getPostResponse(userId: Int): Result<List<UserPostResponse>> =
        makeApiCall({
            getUserPostData(
                allApiService.getPostData(userId)
            )
        })


    private fun getUserPostData(popularMove: Response<List<UserPostResponse>>): Result<List<UserPostResponse>> =
        analyzeResponse(popularMove)

    override suspend fun deletePostItem(postId: Int): Result<Unit> =  makeApiCall({
        deleteUserPostData(
            allApiService.deletePostData(postId)
        )
    })

    private fun deleteUserPostData(popularMove: Response<Unit>): Result<Unit> =
        analyzeResponse(popularMove)

}