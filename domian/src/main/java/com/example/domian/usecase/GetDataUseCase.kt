package com.example.domian.usecase

import com.example.data.datastore.GetDataRepository
import com.example.domian.interactors.GetDataInteractor
import com.example.domian.utils.toLocalUserData
import com.example.entity.CallException
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.localmodels.User
import com.example.entity.Result

class GetDataUseCase(private val getDataRepository: GetDataRepository) :
    GetDataInteractor {

    override suspend fun getDataResponse(): Result<List<User>>{
        val apiData = getDataRepository.getDataListResponse()

        return when (apiData) {
            is Result.Success -> {
                val mappingData = apiData.data?.toLocalUserData()
                Result.Success(mappingData)
            }
            else -> {
                Result.Error(CallException(ERROR_NULL_DATA, null, "Can't load User into server"))
            }
        }
    }

}