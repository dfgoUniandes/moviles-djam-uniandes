package com.example.vinilosdjam.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilosdjam.database.VinilosRoomDatabase
import com.example.vinilosdjam.models.User
import com.example.vinilosdjam.network.NetworkServiceAdapter
import com.example.vinilosdjam.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserViewModel(application: Application) :  AndroidViewModel(application) {

    private val _user = MutableLiveData<List<User>>()
//    private val userRepository = UserRepository(application)
    private val userRepository = UserRepository(application, VinilosRoomDatabase.getDatabase(application.applicationContext).userDao())


    val user: LiveData<List<User>>
    get() = _user

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
    get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
    get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = userRepository.refreshData()
                    _user.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}