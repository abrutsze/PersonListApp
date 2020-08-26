package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.GetDataRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import com.example.entity.responcemodel.UserResponse
import retrofit2.Response
import com.example.entity.Result

class GetDataRepositoryImpl(private val allApiService: AllApiService) :
    GetDataRepository {

    override suspend fun getDataListResponse(): Result<List<UserResponse>> =
        makeApiCall({
            getUserData(
                allApiService.getUserData()
            )
        })

    private fun getUserData(popularMove: Response<List<UserResponse>>): Result<List<UserResponse>> =
        analyzeResponse(popularMove)
}