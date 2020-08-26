package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.User


interface GetDataInteractor {
  suspend  fun getDataResponse(): Result<List<User>>
}