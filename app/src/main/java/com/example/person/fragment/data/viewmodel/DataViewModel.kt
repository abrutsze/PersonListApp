package com.example.person.fragment.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.GetDataInteractor
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.localmodels.User
import com.example.entity.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel(
    private val getDataInteractor: GetDataInteractor
) :
    ViewModel() {

    private val _getUserDataList by lazy { MutableLiveData<List<User>>() }
    val getUserDataList: LiveData<List<User>> = _getUserDataList
    private val _loadingData by lazy { MutableLiveData<Unit>() }
    val loadingData: LiveData<Unit> = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData
    init {
        getDataList()
    }

    fun getDataList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = getDataInteractor.getDataResponse()) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getUserDataList.value = userData.data
                    _loadingData.value=Unit
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    if (userData.errors.errorCode == ERROR_NULL_DATA) {
                        userData.errors.errorMessage?.apply {
                            _errorNullData.value = userData.errors.errorMessage
                            _loadingData.value=Unit
                        }
                    }
                }
            }
        }
    }


}