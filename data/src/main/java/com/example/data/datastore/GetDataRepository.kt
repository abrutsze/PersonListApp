package com.example.data.datastore

import com.example.entity.Result
import com.example.entity.responcemodel.UserResponse

interface GetDataRepository {
     suspend fun getDataListResponse(): Result<List<UserResponse>>
}