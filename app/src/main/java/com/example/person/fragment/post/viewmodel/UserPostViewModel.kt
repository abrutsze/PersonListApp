package com.example.person.fragment.post.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.UserPostInteractor
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.entity.Result
import com.example.entity.localmodels.UserPost


class UserPostViewModel(private val userPostInteractor: UserPostInteractor) : ViewModel() {

    private val _getTopCastMoveData by lazy { MutableLiveData<List<UserPost>>() }
    val getTopCastMoveData get() = _getTopCastMoveData
    private val _loadingData by lazy { MutableLiveData<Unit>() }
    val loadingData get() = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData
    private val _deletePostItem by lazy { MutableLiveData<Int>() }
    val deletePostItem get() = _deletePostItem

    fun getPostData(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = userPostInteractor.getPostResponse(userId)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getTopCastMoveData.value = userData.data
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
    fun deletePostData(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = userPostInteractor.deletePostResponse(userId)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _deletePostItem.value = userId

                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    if (userData.errors.errorCode == ERROR_NULL_DATA) {
                        userData.errors.errorMessage?.apply {
                            _errorNullData.value = userData.errors.errorMessage

                        }
                    }
                }
            }
        }
    }
}

